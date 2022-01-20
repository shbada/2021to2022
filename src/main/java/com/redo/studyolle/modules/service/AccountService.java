package com.redo.studyolle.modules.service;

import com.redo.studyolle.modules.domain.entity.Account;
import com.redo.studyolle.modules.domain.form.SignUpForm;
import com.redo.studyolle.modules.repository.AccountRepository;
import com.redo.studyolle.security.UserAccount;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class AccountService {
    private final AccountRepository accountRepository;
    private final PasswordEncoder passwordEncoder;
    private final ModelMapper modelMapper;

    /**
     * 회원가입
     * @param signUpForm
     * @return
     */
    public Account processNewAccount(SignUpForm signUpForm) {
        /* 신규 회원 저장 */
        Account newAccount = saveNewAccount(signUpForm);

        /* TODO 이메일 발송 */
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

        /* TODO email token 생성 */

        return accountRepository.save(account);
    }

    public void login(Account account) {
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
                new UserAccount(account),
                account.getPassword(),
                List.of(new SimpleGrantedAuthority("ROLE_USER")));
        SecurityContextHolder.getContext().setAuthentication(token);
    }
}
