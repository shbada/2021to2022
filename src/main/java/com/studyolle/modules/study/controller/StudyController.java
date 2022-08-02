package com.studyolle.modules.study.controller;

import com.studyolle.modules.account.security.CurrentAccount;
import com.studyolle.entity.Account;
import com.studyolle.entity.Study;
import com.studyolle.modules.study.repository.StudyRepository;
import com.studyolle.modules.study.service.StudyService;
import com.studyolle.modules.study.form.StudyForm;
import com.studyolle.modules.study.validator.StudyFormValidator;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@Controller
@RequiredArgsConstructor
public class StudyController {
    private final StudyRepository studyRepository;
    private final StudyService studyService;
    private final ModelMapper modelMapper;
    private final StudyFormValidator studyFormValidator;

    /**
     * StudyFormValidator
     * @param webDataBinder
     */
    @InitBinder("studyForm")
    public void studyFormInitBinder(WebDataBinder webDataBinder) {
        webDataBinder.addValidators(studyFormValidator);
    }

    /**
     * 스터디 등록 화면
     * @param account
     * @param model
     * @return
     */
    @GetMapping("/new-study")
    public String newStudyForm(@CurrentAccount Account account, Model model) {
        model.addAttribute(account);
        model.addAttribute(new StudyForm());

        return "study/form";
    }

    /**
     * 스터디 등록
     * @param account
     * @param studyForm
     * @param errors
     * @param model
     * @return
     */
    @PostMapping("/new-study")
    public String newStudySubmit(@CurrentAccount Account account, @Valid StudyForm studyForm, Errors errors, Model model) {
        if (errors.hasErrors()) {
            model.addAttribute(account);
            return "study/form";
        }

        /* 스터디 등록 */
        Study newStudy = studyService.createNewStudy(modelMapper.map(studyForm, Study.class), account);

        return "redirect:/study/" + URLEncoder.encode(newStudy.getPath(), StandardCharsets.UTF_8);
    }

    /**
     * 스터디 상세 화면
     * @param account
     * @param path
     * @param model
     * @return
     */
    @GetMapping("/study/{path}")
    public String viewStudy(@CurrentAccount Account account, @PathVariable String path, Model model) {
        /* 스터디 정보 조회 */
        Study study = studyService.getStudy(path);

        model.addAttribute(account);
        model.addAttribute(study);
        return "study/view";
    }

    /**
     * 스터디 가입 회원 정보 조회 화면
     * @param account
     * @param path
     * @param model
     * @return
     */
    @GetMapping("/study/{path}/members")
    public String viewStudyMembers(@CurrentAccount Account account, @PathVariable String path, Model model) {
        /* 스터디 정보 조회 */
        Study study = studyService.getStudy(path);

        model.addAttribute(account);
        model.addAttribute(study);
        return "study/members";
    }

    /**
     * 스터디 가입
     * @param account
     * @param path
     * @return
     */
    @GetMapping("/study/{path}/join")
    public String joinStudy(@CurrentAccount Account account, @PathVariable String path) {
        /* 스터디 가입 */
        Study study = studyRepository.findStudyWithMembersByPath(path);
        studyService.addMember(study, account);

        return "redirect:/study/" + study.getEncodedPath() + "/members";
    }

    /**
     * 스터디 탈퇴
     * @param account
     * @param path
     * @return
     */
    @GetMapping("/study/{path}/leave")
    public String leaveStudy(@CurrentAccount Account account, @PathVariable String path) {
        /* 스터디 탈퇴 */
        Study study = studyRepository.findStudyWithMembersByPath(path);
        studyService.removeMember(study, account);

        return "redirect:/study/" + study.getEncodedPath() + "/members";
    }

}
