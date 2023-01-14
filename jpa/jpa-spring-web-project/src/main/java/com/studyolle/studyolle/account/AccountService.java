package com.studyolle.studyolle.account;

import com.studyolle.studyolle.account.form.SignUpForm;
import com.studyolle.studyolle.domain.Account;
import com.studyolle.studyolle.mail.EmailMessage;
import com.studyolle.studyolle.mail.EmailService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.Valid;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class AccountService {
    private final ModelMapper modelMapper;
    private final AccountRepository accountRepository;
    private final PasswordEncoder passwordEncoder;
    private final EmailService emailService;

    /**
     * 계정 등록 프로세스 실행
     * @param signUpForm
     * @return
     */
    public Account processNewAccount(SignUpForm signUpForm) {
        Account newAccount = saveNewAccount(signUpForm);
        sendSignUpConfirmEmail(newAccount);
        return newAccount;
    }

//    @Transactional /* JPA persist 유지를 위해 (트랜잭션이 종료될때 DB에 상태를 동기화한다) */
//    public Account processNewAccount(SignUpForm signUpForm) {
          // (newAccount : detached 상태, 트랜잭션에서 벗어났으므로)
          // @Transactional 어노테이션을 써줘야, 트랜잭션 범위에 들어온다.
//        Account newAccount = saveNewAccount(signUpForm);
//        newAccount.generateEmailCheckToken();
//        return newAccount;
//    }

    /**
     * 계정 등록
     * @param signUpForm
     * @return
     */
    private Account saveNewAccount(@Valid SignUpForm signUpForm) {
        /* 패스워드 encode */
        /**
         * Salt  솔트) 사용한 인코딩
         * 1234567 + salt (문자열 전체를 해싱) -> aaaaabbbbbggg123
         * Bcrypt: salt 는 매번 랜덤한 값을 사용한다.
         * 해싱된 결과가 매번 바뀐다 (salt 가 달라지기 때문에 salt 를 포함한 문자열을 인코딩하므로)
         * salt 값을 인코딩을 할때만 쓰고, 12345678 (평문)이랑 aaaaabbbbbggg123 (해쉬된 값)을 다시 또 해쉬를 하면
         * 원래 해쉬값이 나오게 되어있다.
         */
        signUpForm.setPassword(passwordEncoder.encode(signUpForm.getPassword()));

        Account account = modelMapper.map(signUpForm, Account.class);
        //account.generateEmailCheckToken();
        return accountRepository.save(account); // save 안에서 트랜잭션됨 (save 안에서 persist 상태)
    }

    /**
     * 이메일 발송
     * @param newAccount
     */
    public void sendSignUpConfirmEmail(Account newAccount) {
        String message = "test test";

        EmailMessage emailMessage = EmailMessage.builder()
                .to(newAccount.getEmail())
                .subject("스터디올래, 회원 가입 인증")
                .message(message)
                .build();

        emailService.sendEmail(emailMessage);
    }

    public void completeSignUp(Account account) {
        account.completeSignUp();
        login(account);
    }

    /**
     * 로그인
     * @param account
     */
    public void login(Account account) {
        /* 세션 토큰 생성 */
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
                new UserAccount(account),
                account.getPassword(),
                List.of(new SimpleGrantedAuthority("ROLE_USER")));

        SecurityContextHolder.getContext().setAuthentication(token);
    }
}
