package com.redo.studyolle.modules.controller;

import com.redo.studyolle.modules.domain.entity.Account;
import com.redo.studyolle.modules.domain.form.NicknameForm;
import com.redo.studyolle.modules.domain.form.PasswordForm;
import com.redo.studyolle.modules.domain.form.ProfileForm;
import com.redo.studyolle.modules.domain.validator.NicknameValidator;
import com.redo.studyolle.modules.domain.validator.PasswordFormValidator;
import com.redo.studyolle.modules.service.AccountService;
import com.redo.studyolle.security.CurrentAccount;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
@RequestMapping("/settings")
public class SettingController {
    private final AccountService accountService;
    private final ModelMapper modelMapper;
    private final NicknameValidator nicknameValidator;

    /**
     * PasswordFormValidator
     * @param webDataBinder
     */
    @InitBinder("passwordForm")
    public void passwordFormInitBinder(WebDataBinder webDataBinder) {
        webDataBinder.addValidators(new PasswordFormValidator());
    }

    /**
     * 비밀번호 변경 화면
     * @param account
     * @param model
     * @return
     */
    @GetMapping("/password")
    public String updatePasswordForm(@CurrentAccount Account account, Model model) {
        model.addAttribute(account);
        model.addAttribute(new PasswordForm());

        return "/settings/password";
    }

    /**
     * 비밀번호 변경
     * @param account
     * @param passwordForm
     * @param errors
     * @param model
     * @param attributes
     * @return
     */
    @PostMapping("/password")
    public String updatePassword(@CurrentAccount Account account, @Valid PasswordForm passwordForm, Errors errors,
                                 Model model, RedirectAttributes attributes) {
        if (errors.hasErrors()) {
            model.addAttribute(account);
            return "settings/password";
        }

        /* 비밀번호 변경 */
        accountService.updatePassword(account, passwordForm.getNewPassword());

        attributes.addFlashAttribute("message", "패스워드를 변경했습니다.");

        return "redirect:/settings/password";
    }

    /**
     * 계정 정보 수정 화면
     * @param account
     * @param model
     * @return
     */
    @GetMapping("/account")
    public String updateAccountForm(@CurrentAccount Account account, Model model) {
        model.addAttribute(account);
        model.addAttribute(modelMapper.map(account, NicknameForm.class));
        return "settings/account";
    }

    /**
     * 계정 정보 수정
     * @param account
     * @param nicknameForm
     * @param errors
     * @param model
     * @param attributes
     * @return
     */
    @PostMapping("/account")
    public String updateAccount(@CurrentAccount Account account, @Valid NicknameForm nicknameForm, Errors errors,
                                Model model, RedirectAttributes attributes) {
        if (errors.hasErrors()) {
            model.addAttribute(account);
            return "settings/account";
        }

        /* 닉네임 업데이트 */
        accountService.updateNickname(account, nicknameForm.getNickname());

        attributes.addFlashAttribute("message", "닉네임을 수정했습니다.");

        return "redirect:/settings/account";
    }

    /**
     * 프로필 수정 화면
     * @param account
     * @param model
     * @return
     */
    @GetMapping("/profile")
    public String updateProfileForm(@CurrentAccount Account account, Model model) {
        model.addAttribute(account);
        model.addAttribute(modelMapper.map(account, ProfileForm.class));

        return "settings/profile";
    }

    /**
     * 프로필 저장
     * @param account
     * @param profileForm
     * @param errors
     * @param model
     * @param attributes
     * @return
     */
    @PostMapping("/profile")
    public String updateProfile(@CurrentAccount Account account, @Valid ProfileForm profileForm, Errors errors,
                                Model model, RedirectAttributes attributes) {
        /* error check */
        if (errors.hasErrors()) {
            model.addAttribute(account);
            return "settings/profile";
        }

        /* 프로필 저장 */
        accountService.updateProfile(account, profileForm);

        attributes.addFlashAttribute("message", "프로필을 수정했습니다.");

        return "redirect:/settings/profile";
    }
}
