package com.studyolle.studyolle.account;

import com.studyolle.studyolle.account.form.SignUpForm;
import com.studyolle.studyolle.domain.Account;
import com.studyolle.studyolle.mail.EmailMessage;
import com.studyolle.studyolle.mail.EmailService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.Valid;

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

    /**
     * 계정 등록
     * @param signUpForm
     * @return
     */
    private Account saveNewAccount(@Valid SignUpForm signUpForm) {
        /* 패스워드 encode */
        /**
         * Salt (솔트) 사용한 인코딩
         * 1234567 + salt (문자열 전체를 해싱) -> aaaaabbbbbggg123
         * Bcrypt: salt 는 매번 랜덤한 값을 사용한다.
         * 해싱된 결과가 매번 바뀐다 (salt 가 달라지기 때문에 salt 를 포함한 문자열을 인코딩하므로)
         * salt 값을 인코딩을 할때만 쓰고, 12345678 (평문)이랑 aaaaabbbbbggg123 (해쉬된 값)을 다시 또 해쉬를 하면
         * 원래 해쉬값이 나오게 되어있다.
         */
        signUpForm.setPassword(passwordEncoder.encode(signUpForm.getPassword()));

        Account account = modelMapper.map(signUpForm, Account.class);
        //account.generateEmailCheckToken();
        return accountRepository.save(account);
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
}
