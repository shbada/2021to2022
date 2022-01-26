package com.redo.studyolle.modules.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.redo.studyolle.modules.domain.entity.Account;
import com.redo.studyolle.modules.domain.entity.Tag;
import com.redo.studyolle.modules.domain.form.NicknameForm;
import com.redo.studyolle.modules.domain.form.PasswordForm;
import com.redo.studyolle.modules.domain.form.ProfileForm;
import com.redo.studyolle.modules.domain.form.TagForm;
import com.redo.studyolle.modules.domain.validator.NicknameValidator;
import com.redo.studyolle.modules.domain.validator.PasswordFormValidator;
import com.redo.studyolle.modules.repository.TagRepository;
import com.redo.studyolle.modules.service.AccountService;
import com.redo.studyolle.modules.service.TagService;
import com.redo.studyolle.security.CurrentAccount;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
@RequestMapping("/settings")
public class SettingController {
    private final AccountService accountService;
    private final ModelMapper modelMapper;
    private final NicknameValidator nicknameValidator;
    private final ObjectMapper objectMapper;

    private final TagService tagService;
    private final TagRepository tagRepository;

    /**
     * PasswordFormValidator
     * @param webDataBinder
     */
    @InitBinder("passwordForm")
    public void passwordFormInitBinder(WebDataBinder webDataBinder) {
        webDataBinder.addValidators(new PasswordFormValidator());
    }

    /**
     * NicknameValidator
     * @param webDataBinder
     */
    @InitBinder("nicknameForm")
    public void nicknameFormInitBinder(WebDataBinder webDataBinder) {
        webDataBinder.addValidators(nicknameValidator);
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

    /**
     * 태그 수정 화면
     * @param account
     * @param model
     * @return
     * @throws JsonProcessingException
     */
    @GetMapping("/tags")
    public String updateTags(@CurrentAccount Account account, Model model) throws JsonProcessingException {
        model.addAttribute(account);

        /* tag 의 title String List */
        Set<Tag> tags = accountService.getTags(account);
        model.addAttribute("tags", tags.stream().map(Tag::getTitle).collect(Collectors.toList()));

        /* selectBox 리스트를 위한 allTags */
        List<String> allTags = tagRepository.findAll().stream()
                                        .map(Tag::getTitle)
                                        .collect(Collectors.toList());
        model.addAttribute("whitelist", objectMapper.writeValueAsString(allTags));

        return "settings/tags";
    }

    /**
     * 태그 등록
     * @param account
     * @param tagForm
     * @return
     */
    @PostMapping("/tags/add")
    @ResponseBody
    public ResponseEntity addTag(@CurrentAccount Account account, @RequestBody TagForm tagForm) {
        /* 전체 태그 찾기 및 등록 */
        Tag tag = tagService.findOrCreateNew(tagForm.getTagTitle());

        /* 회원의 태그 등록 */
        accountService.addTag(account, tag);

        return ResponseEntity.ok().build();
    }

    /**
     * 태그 삭제
     * @param account
     * @param tagForm
     * @return
     */
    @PostMapping("/tags/remove")
    @ResponseBody
    public ResponseEntity removeTag(@CurrentAccount Account account, @RequestBody TagForm tagForm) {
        /* 태그 조회 */
        String title = tagForm.getTagTitle();
        Tag tag = tagRepository.findByTitle(title);

        if (tag == null) {
            return ResponseEntity.badRequest().build();
        }

        /* 태그 삭제 */
        accountService.removeTag(account, tag);

        return ResponseEntity.ok().build();
    }
}
