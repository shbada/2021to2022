package com.studyolle.modules.account.service;

import com.studyolle.modules.account.repository.AccountRepository;
import com.studyolle.modules.account.security.UserAccount;
import com.studyolle.modules.account.form.SignUpForm;
import com.studyolle.config.AppProperties;
import com.studyolle.entity.Account;
import com.studyolle.entity.Tag;
import com.studyolle.entity.Zone;
import com.studyolle.modules.mail.EmailMessage;
import com.studyolle.modules.mail.EmailService;
import com.studyolle.modules.account.form.Notifications;
import com.studyolle.modules.account.form.Profile;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class AccountService implements UserDetailsService {

    private final AccountRepository accountRepository;
    private final EmailService emailService;
    private final PasswordEncoder passwordEncoder;
    private final ModelMapper modelMapper;
    private final TemplateEngine templateEngine;
    private final AppProperties appProperties;

    /**
     * 회원가입
     * @param signUpForm
     * @return
     */
    public Account processNewAccount(SignUpForm signUpForm) {
        /* 신규 회원 저장 */
        Account newAccount = saveNewAccount(signUpForm);

        /* 이메일 토큰 발송 */
        sendSignUpConfirmEmail(newAccount); // token 은 위 saveNewAccount 에서 저장
        return newAccount;
    }

    /**
     * 신규 회원 저장
     * @param signUpForm
     * @return
     */
    private Account saveNewAccount(@Valid SignUpForm signUpForm) {
        /* password set */
        signUpForm.setPassword(passwordEncoder.encode(signUpForm.getPassword()));

        /* account create */
        Account account = modelMapper.map(signUpForm, Account.class);

        /* email token 생성 */
        account.generateEmailCheckToken();

        return accountRepository.save(account);
    }

    /**
     * 회원가입 인증확인 이메일 발송
     * @param newAccount
     */
    public void sendSignUpConfirmEmail(Account newAccount) {
        /* email content */
        Context context = new Context();
        context.setVariable("link", "/check-email-token?token=" + newAccount.getEmailCheckToken() +
                "&email=" + newAccount.getEmail());
        context.setVariable("nickname", newAccount.getNickname());
        context.setVariable("linkName", "이메일 인증하기");
        context.setVariable("message", "스터디올래 서비스를 사용하려면 링크를 클릭하세요.");
        context.setVariable("host", appProperties.getHost());

        String message = templateEngine.process("mail/simple-link", context);

        /* dto set */
        EmailMessage emailMessage = EmailMessage.builder()
                .to(newAccount.getEmail())
                .subject("스터디올래, 회원 가입 인증")
                .message(message)
                .build();

        /* send */
        emailService.sendEmail(emailMessage);
    }

    /**
     * SpringSecurity login (이런식으로 하진 않지만 예제일뿐)
     * @param account
     */
    public void login(Account account) {
        /* UsernamePasswordAuthenticationToken 수동 생성 */
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
                new UserAccount(account),
                account.getPassword(),
                List.of(new SimpleGrantedAuthority("ROLE_USER")));

        /* SecurityContextHolder Context 인증객체 수동 저장 */
        SecurityContextHolder.getContext().setAuthentication(token);
    }

    /**
     * SpringSecurity loadUserByUsername
     * @param emailOrNickname
     * @return
     * @throws UsernameNotFoundException
     */
    @Transactional(readOnly = true)
    @Override
    public UserDetails loadUserByUsername(String emailOrNickname) throws UsernameNotFoundException {
        Account account = accountRepository.findByEmail(emailOrNickname);
        if (account == null) {
            account = accountRepository.findByNickname(emailOrNickname);
        }

        if (account == null) {
            throw new UsernameNotFoundException(emailOrNickname);
        }

        return new UserAccount(account);
    }

    /**
     * 이메일 토큰 인증
     * @param account
     */
    public void completeSignUp(Account account) {
        /* 계정의 이메일 인증 처리 */
        account.completeSignUp();

        /* 로그인 */
        login(account);
    }

    /**
     * 프로필 업데이트
     * @param account
     * @param profile
     */
    public void updateProfile(Account account, Profile profile) {
        modelMapper.map(profile, account);
        accountRepository.save(account); // account 는 detach 상태이므로 save 호출 필요
    }

    /**
     * 패스워드 업데이트
     * @param account
     * @param newPassword
     */
    public void updatePassword(Account account, String newPassword) {
        account.setPassword(passwordEncoder.encode(newPassword));
        accountRepository.save(account); // account 는 detach 상태이므로 save 호출 필요
    }

    /**
     * 공지 관련 업데이트
     * @param account
     * @param notifications
     */
    public void updateNotifications(Account account, Notifications notifications) {
        modelMapper.map(notifications, account);
        accountRepository.save(account);
    }

    /**
     * 닉네임 업데이트
     * @param account
     * @param nickname
     */
    public void updateNickname(Account account, String nickname) {
        account.setNickname(nickname);
        accountRepository.save(account);

        /* 로그인 (변경된 닉네임으로 재로그인 필요) */
        login(account);
    }

    /**
     * 로그인링크 이메일 발송
     * @param account
     */
    public void sendLoginLink(Account account) {
        /* email content */
        Context context = new Context();
        context.setVariable("link", "/login-by-email?token=" + account.getEmailCheckToken() +
                "&email=" + account.getEmail());
        context.setVariable("nickname", account.getNickname());
        context.setVariable("linkName", "스터디올래 로그인하기");
        context.setVariable("message", "로그인 하려면 아래 링크를 클릭하세요.");
        context.setVariable("host", appProperties.getHost());
        String message = templateEngine.process("mail/simple-link", context);

        /* dto set */
        EmailMessage emailMessage = EmailMessage.builder()
                .to(account.getEmail())
                .subject("스터디올래, 로그인 링크")
                .message(message)
                .build();

        /* send email */
        emailService.sendEmail(emailMessage);
    }

    /**
     * 회원의 태그 등록
     * @param account
     * @param tag
     */
    public void addTag(Account account, Tag tag) {
        Optional<Account> byId = accountRepository.findById(account.getId());

        /* account add tag */
        byId.ifPresent(a -> a.getTags().add(tag));
    }

    /**
     * 회원의 태그 조회
     * @param account
     * @return
     */
    public Set<Tag> getTags(Account account) {
        Optional<Account> byId = accountRepository.findById(account.getId());
        return byId.orElseThrow().getTags();
    }

    /**
     * 태그 삭제
     * @param account
     * @param tag
     */
    public void removeTag(Account account, Tag tag) {
        Optional<Account> byId = accountRepository.findById(account.getId());
        byId.ifPresent(a -> a.getTags().remove(tag));
    }

    /**
     * 회원의 지역 정보 조회
     * @param account
     * @return
     */
    public Set<Zone> getZones(Account account) {
        Optional<Account> byId = accountRepository.findById(account.getId());
        return byId.orElseThrow().getZones();
    }

    /**
     * 지역 저장
     * @param account
     * @param zone
     */
    public void addZone(Account account, Zone zone) {
        Optional<Account> byId = accountRepository.findById(account.getId());
        byId.ifPresent(a -> a.getZones().add(zone));
    }

    /**
     * 지역 삭제
     * @param account
     * @param zone
     */
    public void removeZone(Account account, Zone zone) {
        Optional<Account> byId = accountRepository.findById(account.getId());
        byId.ifPresent(a -> a.getZones().remove(zone));
    }

    /**
     * 닉네임으로 회원 정보 찾기
     * @param nickname
     * @return
     */
    public Account getAccount(String nickname) {
        Account account = accountRepository.findByNickname(nickname);

        if (account == null) {
            throw new IllegalArgumentException(nickname + "에 해당하는 사용자가 없습니다.");
        }

        return account;
    }
}
