package com.studyolle.studyolle.account;

import com.studyolle.studyolle.account.form.SignUpForm;
import com.studyolle.studyolle.domain.Account;
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
    private final SignUpFormValidator signUpFormValidator;

    /**
     * 자동 검증 처리
     * @param webDataBinder
     */
    @InitBinder("signUpForm") /* @Valid 의 signUpForm 명칭 */
    public void initBinder(WebDataBinder webDataBinder) {
        webDataBinder.addValidators(signUpFormValidator);
    }

    /**
     *
     * 회원가입 화면
     * @param model
     * @return
     */
    @GetMapping("/sign-up")
    public String signUpForm(Model model) {
        model.addAttribute(new SignUpForm());
        // model.addAttribute("signUpForm", new SignUpForm());
        return "account/sign-up"; /* Thymeleaf 스프링부트가 자동 설정 제공 */
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

        Account account = accountService.processNewAccount(signUpForm);
        //accountService.login(account);
        return "redirect:/";
    }
}
