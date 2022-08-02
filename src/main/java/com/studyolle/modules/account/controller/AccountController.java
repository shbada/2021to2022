package com.studyolle.modules.account.controller;

import com.studyolle.modules.account.repository.AccountRepository;
import com.studyolle.modules.account.service.AccountService;
import com.studyolle.modules.account.security.CurrentAccount;
import com.studyolle.modules.account.form.SignUpForm;
import com.studyolle.modules.account.validator.SignUpFormValidator;
import com.studyolle.entity.Account;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
public class AccountController {

    private final SignUpFormValidator signUpFormValidator;
    private final AccountService accountService;
    private final AccountRepository accountRepository;

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
        /* validation check */
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
     * 이메일 토큰 유효성 체크
     * @param token
     * @param email
     * @param model
     * @return
     */
    @GetMapping("/check-email-token")
    public String checkEmailToken(String token, String email, Model model) {
        /* email 로 회원 조회 */
        Account account = accountRepository.findByEmail(email);

        String view = "account/checked-email";

        /* check1. 이메일 유효 확인 */
        if (account == null) {
            model.addAttribute("error", "wrong.email");
            return view;
        }

        /* check2. 토큰 유효 확인 */
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
     * 이메일 인증 재발송
     * @param account
     * @param model
     * @return
     */
    @GetMapping("/resend-confirm-email")
    public String resendConfirmEmail(@CurrentAccount Account account, Model model) {
        /* 시간 체크 */
        if (!account.canSendConfirmEmail()) {
            model.addAttribute("error", "인증 이메일은 1시간에 한번만 전송할 수 있습니다.");
            model.addAttribute("email", account.getEmail());
            return "account/check-email";
        }

        /* 이메일 인증 발송 */
        accountService.sendSignUpConfirmEmail(account);

        return "redirect:/";
    }

    /**
     * 프로필 조회 화면
     * @param nickname
     * @param model
     * @param account
     * @return
     */
    @GetMapping("/profile/{nickname}")
    public String viewProfile(@PathVariable String nickname, Model model, @CurrentAccount Account account) {
        /* 닉네임의 회원 조회 */
        Account accountToView = accountService.getAccount(nickname);

        model.addAttribute(accountToView);

        /* 자기 자신 여부 */
        model.addAttribute("isOwner", accountToView.equals(account));

        return "account/profile";
    }

    /**
     * 이메일 로그인하기 화면
     * @return
     */
    @GetMapping("/email-login")
    public String emailLoginForm() {
        return "account/email-login";
    }

    /**
     * 이메일 로그인
     * @param email
     * @param model
     * @param attributes
     * @return
     */
    @PostMapping("/email-login")
    public String sendEmailLoginLink(String email, Model model, RedirectAttributes attributes) {
        /* 회원 정보 조회 */
        Account account = accountRepository.findByEmail(email);

        if (account == null) {
            model.addAttribute("error", "유효한 이메일 주소가 아닙니다.");
            return "account/email-login";
        }

        if (!account.canSendConfirmEmail()) {
            model.addAttribute("error", "이메일 로그인은 1시간 뒤에 사용할 수 있습니다.");
            return "account/email-login";
        }

        /* 로그인 링크 발송 */
        accountService.sendLoginLink(account);

        attributes.addFlashAttribute("message", "이메일 인증 메일을 발송했습니다.");
        return "redirect:/email-login";
    }

    /**
     * 이메일로 로그인 수행
     * @param token
     * @param email
     * @param model
     * @return
     */
    @GetMapping("/login-by-email")
    public String loginByEmail(String token, String email, Model model) {
        /* 회원 정보 조회 */
        Account account = accountRepository.findByEmail(email);

        String view = "account/logged-in-by-email";

        if (account == null || !account.isValidToken(token)) {
            model.addAttribute("error", "로그인할 수 없습니다.");
            return view;
        }

        /* 로그인 */
        accountService.login(account);
        return view;
    }

}
