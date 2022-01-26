package com.redo.studyolle.modules.service;

import com.redo.studyolle.common.AppProperties;
import com.redo.studyolle.common.mail.EmailMessage;
import com.redo.studyolle.common.mail.EmailService;
import com.redo.studyolle.modules.domain.entity.Account;
import com.redo.studyolle.modules.domain.entity.Tag;
import com.redo.studyolle.modules.domain.entity.Zone;
import com.redo.studyolle.modules.domain.form.ProfileForm;
import com.redo.studyolle.modules.domain.form.SignUpForm;
import com.redo.studyolle.modules.domain.form.TagForm;
import com.redo.studyolle.modules.repository.AccountRepository;
import com.redo.studyolle.security.CurrentAccount;
import com.redo.studyolle.security.UserAccount;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class AccountService {
    private final AccountRepository accountRepository;
    private final PasswordEncoder passwordEncoder;
    private final ModelMapper modelMapper;

    private final TemplateEngine templateEngine;
    private final AppProperties appProperties;
    private final EmailService emailService;

    /**
     * 회원가입
     * @param signUpForm
     * @return
     */
    public Account processNewAccount(SignUpForm signUpForm) {
        /* 신규 회원 저장 */
        Account newAccount = saveNewAccount(signUpForm);

        /* 이메일 발송 */
        sendSignUpConfirmEmail(newAccount); // 위 saveNewAccount 에서 셋팅된 이메일 토큰 사용

        return newAccount;
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
     * 로그인
     * @param account
     */
    public void login(Account account) {
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
                new UserAccount(account),
                account.getPassword(),
                List.of(new SimpleGrantedAuthority("ROLE_USER")));
        SecurityContextHolder.getContext().setAuthentication(token);
    }

    /**
     * 이메일 토큰 인증 완료
     * @param account
     */
    public void completeSignUp(Account account) {
        account.completeSignUp();
        login(account);
    }

    /**
     * 로그인 링크 이메일 발송
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
     * 패스워드 업데이트
     * @param account
     * @param newPassword
     */
    public void updatePassword(Account account, String newPassword) {
        account.setPassword(passwordEncoder.encode(newPassword));
        accountRepository.save(account); // account 는 detach 상태이므로 save 호출 필요
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
     * 프로필 업데이트
     * @param account
     * @param profileForm
     */
    public void updateProfile(Account account, ProfileForm profileForm) {
        modelMapper.map(profileForm, account);
        accountRepository.save(account); // account 는 detach 상태이므로 save 호출 필요
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
     * 회원의 태그 등록
     * @param account
     * @param tag
     */
    public void addTag(Account account, Tag tag) {
        Optional<Account> byId = accountRepository.findById(account.getId());

        /* byId 가 존재할 경우 실행된다. account add tag */
        byId.ifPresent(a -> a.getTags().add(tag));
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
     * 회원의 지역 정보 조회
     * @param account
     * @return
     */
    public Set<Zone> getZones(Account account) {
        Optional<Account> byId = accountRepository.findById(account.getId());
        return byId.orElseThrow().getZones();
    }
}
