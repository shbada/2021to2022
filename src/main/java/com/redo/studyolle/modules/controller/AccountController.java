package com.redo.studyolle.modules.controller;

import com.redo.studyolle.modules.domain.entity.Account;
import com.redo.studyolle.modules.domain.form.SignUpForm;
import com.redo.studyolle.modules.domain.validator.SignUpFormValidator;
import com.redo.studyolle.modules.repository.AccountRepository;
import com.redo.studyolle.modules.service.AccountService;
import com.redo.studyolle.security.CurrentAccount;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
public class AccountController {
    private final AccountService accountService;
    private final AccountRepository accountRepository;
    private final SignUpFormValidator signUpFormValidator;

    /**
     * 회원가입 관련 initBinder
     * @param webDataBinder
     */
    @InitBinder("signUpForm")
    public void initBinder(WebDataBinder webDataBinder) {
        webDataBinder.addValidators(signUpFormValidator);
    }

    /**
     * 회원가입 화면
     * @param model
     * @return
     */
    @GetMapping("/sign-up")
    public String signUpForm(Model model) {
        model.addAttribute(new SignUpForm());
        return "account/sign-up";
    }

    /**
     * 회원가입
     * @param signUpForm
     * @param errors
     * @return
     */
    @PostMapping("/sign-up")
    public String signUpSubmit(@Valid SignUpForm signUpForm, Errors errors) {
        if (errors.hasErrors()) {
            return "account/sign-up";
        }

        /* 회원가입 */
        Account account = accountService.processNewAccount(signUpForm);

        /* 로그인 수행 */
        accountService.login(account);

        return "redirect:/";
    }

    /**
     * 로그인 화면
     * @return
     */
    @GetMapping("/login")
    public String login() {
        return "login";
    }

    /**
     * 이메일 인증 화면
     * @param account
     * @param model
     * @return
     */
    @GetMapping("/check-email")
    public String checkEmail(@CurrentAccount Account account, Model model) {
        model.addAttribute("email", account.getEmail());
        return "account/check-email";
    }

    /**
     * 이메일 토큰 유효성 체크
     * @param token
     * @param email
     * @param model
     * @return
     */
    @GetMapping("/check-email-token")
    public String checkEmailToken(String token, String email, Model model) {
        /* return view */
        String view = "account/checked-email";

        /* email 로 회원 조회 */
        Account account = accountRepository.findByEmail(email);

        if (account == null) {
            model.addAttribute("error", "wrong.email");
            return view;
        }

        if (!account.isValidToken(token)) {
            model.addAttribute("error", "wrong.token");
            return view;
        }

        /* 인증 완료 */
        accountService.completeSignUp(account);

        model.addAttribute("numberOfUser", accountRepository.count());
        model.addAttribute("nickname", account.getNickname());

        return view;
    }
}
