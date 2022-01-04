package com.studyolle.modules.study;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.studyolle.modules.account.CurrentAccount;
import com.studyolle.modules.account.Account;
import com.studyolle.modules.tag.Tag;
import com.studyolle.modules.zone.Zone;
import com.studyolle.modules.study.form.StudyDescriptionForm;
import com.studyolle.modules.tag.TagForm;
import com.studyolle.modules.tag.TagRepository;
import com.studyolle.modules.tag.TagService;
import com.studyolle.modules.zone.ZoneForm;
import com.studyolle.modules.zone.ZoneRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/study/{path}/settings")
@RequiredArgsConstructor
public class StudySettingsController {

    private final StudyRepository studyRepository;
    private final StudyService studyService;
    private final ModelMapper modelMapper;
    private final TagService tagService;
    private final TagRepository tagRepository;
    private final ZoneRepository zoneRepository;
    private final ObjectMapper objectMapper;

    /**
     * 스터디 소개글 조회
     * @param account
     * @param path
     * @param model
     * @return
     */
    @GetMapping("/description")
    public String viewStudySetting(@CurrentAccount Account account, @PathVariable String path, Model model) {
        /* 스터디 정보 조회 */
        Study study = studyService.getStudyToUpdate(account, path);

        model.addAttribute(account);
        model.addAttribute(study);
        model.addAttribute(modelMapper.map(study, StudyDescriptionForm.class));
        return "study/settings/description";
    }

    /**
     * 스터디 소개글 수정
     * @param account
     * @param path
     * @param studyDescriptionForm
     * @param errors
     * @param model
     * @param attributes
     * @return
     */
    @PostMapping("/description")
    public String updateStudyInfo(@CurrentAccount Account account, @PathVariable String path,
                                  @Valid StudyDescriptionForm studyDescriptionForm, Errors errors,
                                  Model model, RedirectAttributes attributes) {
        /* 스터디 정보 조회 */
        Study study = studyService.getStudyToUpdate(account, path);

        if (errors.hasErrors()) {
            model.addAttribute(account);
            model.addAttribute(study);
            return "study/settings/description";
        }

        /* 스터디 정보 수정 */
        studyService.updateStudyDescription(study, studyDescriptionForm);

        attributes.addFlashAttribute("message", "스터디 소개를 수정했습니다.");
        return "redirect:/study/" + study.getEncodedPath() + "/settings/description";
    }

    /**
     * banner 정보 조회
     * @param account
     * @param path
     * @param model
     * @return
     */
    @GetMapping("/banner")
    public String studyImageForm(@CurrentAccount Account account, @PathVariable String path, Model model) {
        /* 스터디 정보 조회 */
        Study study = studyService.getStudyToUpdate(account, path);

        model.addAttribute(account);
        model.addAttribute(study);
        return "study/settings/banner";
    }

    /**
     * banner 수정
     * @param account
     * @param path
     * @param image
     * @param attributes
     * @return
     */
    @PostMapping("/banner")
    public String studyImageSubmit(@CurrentAccount Account account, @PathVariable String path,
                                   String image, RedirectAttributes attributes) {
        /* 스터디 정보 조회 */
        Study study = studyService.getStudyToUpdate(account, path);

        /* banner image update */
        studyService.updateStudyImage(study, image);

        attributes.addFlashAttribute("message", "스터디 이미지를 수정했습니다.");
        return "redirect:/study/" + study.getEncodedPath() + "/settings/banner";
    }

    /**
     * 스터디 배너 사용으로 변경
     * @param account
     * @param path
     * @return
     */
    @PostMapping("/banner/enable")
    public String enableStudyBanner(@CurrentAccount Account account, @PathVariable String path) {
        Study study = studyService.getStudyToUpdate(account, path);
        studyService.enableStudyBanner(study);

        return "redirect:/study/" + study.getEncodedPath() + "/settings/banner";
    }

    /**
     * 스터디 배너 사용안함으로 변경
     * @param account
     * @param path
     * @return
     */
    @PostMapping("/banner/disable")
    public String disableStudyBanner(@CurrentAccount Account account, @PathVariable String path) {
        Study study = studyService.getStudyToUpdate(account, path);
        studyService.disableStudyBanner(study);

        return "redirect:/study/" + study.getEncodedPath() + "/settings/banner";
    }

    /**
     * 스터디 태그 정보 조회
     * @param account
     * @param path
     * @param model
     * @return
     * @throws JsonProcessingException
     */
    @GetMapping("/tags")
    public String studyTagsForm(@CurrentAccount Account account, @PathVariable String path, Model model)
            throws JsonProcessingException {
        /* 스터디 정보 조회 */
        Study study = studyService.getStudyToUpdate(account, path);
        model.addAttribute(account);
        model.addAttribute(study);

        model.addAttribute("tags", study.getTags().stream()
                .map(Tag::getTitle).collect(Collectors.toList()));

        List<String> allTagTitles = tagRepository.findAll().stream()
                .map(Tag::getTitle).collect(Collectors.toList());

        model.addAttribute("whitelist", objectMapper.writeValueAsString(allTagTitles));
        return "study/settings/tags";
    }

    /**
     * 스터디 태그 저장
     * @param account
     * @param path
     * @param tagForm
     * @return
     */
    @PostMapping("/tags/add")
    @ResponseBody
    public ResponseEntity addTag(@CurrentAccount Account account, @PathVariable String path,
                                 @RequestBody TagForm tagForm) {
        /* 스터디 정보 조회 */
        Study study = studyService.getStudyToUpdateTag(account, path);

        /* 스터디 태그 정보 조회 */
        Tag tag = tagService.findOrCreateNew(tagForm.getTagTitle());

        /* 스터디 태그 저장 */
        studyService.addTag(study, tag);

        return ResponseEntity.ok().build();
    }

    /**
     * 스터디 태그 삭제
     * @param account
     * @param path
     * @param tagForm
     * @return
     */
    @PostMapping("/tags/remove")
    @ResponseBody
    public ResponseEntity removeTag(@CurrentAccount Account account, @PathVariable String path,
                                    @RequestBody TagForm tagForm) {
        /* 스터디 정보 조회 */
        Study study = studyService.getStudyToUpdateTag(account, path);

        /* 스터디 태그 정보 조회 */
        Tag tag = tagRepository.findByTitle(tagForm.getTagTitle());
        if (tag == null) {
            return ResponseEntity.badRequest().build();
        }

        /* 태그 삭제 수행 */
        studyService.removeTag(study, tag);
        return ResponseEntity.ok().build();
    }

    /**
     * 스터디 지역 정보 조회
     * @param account
     * @param path
     * @param model
     * @return
     * @throws JsonProcessingException
     */
    @GetMapping("/zones")
    public String studyZonesForm(@CurrentAccount Account account, @PathVariable String path, Model model)
            throws JsonProcessingException {
        /* 스터디 정보 조회 */
        Study study = studyService.getStudyToUpdate(account, path);
        model.addAttribute(account);
        model.addAttribute(study);

        /* 지역 조회 */
        model.addAttribute("zones", study.getZones().stream().map(Zone::toString).collect(Collectors.toList()));

        /* 모든 지역 조회 */
        List<String> allZones = zoneRepository.findAll().stream().map(Zone::toString).collect(Collectors.toList());

        model.addAttribute("whitelist", objectMapper.writeValueAsString(allZones));
        return "study/settings/zones";
    }

    /**
     * 스터디 지역 저장
     * @param account
     * @param path
     * @param zoneForm
     * @return
     */
    @PostMapping("/zones/add")
    @ResponseBody
    public ResponseEntity addZone(@CurrentAccount Account account, @PathVariable String path,
                                  @RequestBody ZoneForm zoneForm) {
        Study study = studyService.getStudyToUpdateZone(account, path);
        Zone zone = zoneRepository.findByCityAndProvince(zoneForm.getCityName(), zoneForm.getProvinceName());
        if (zone == null) {
            return ResponseEntity.badRequest().build();
        }

        /* 스터디 지역 저장 */
        studyService.addZone(study, zone);

        return ResponseEntity.ok().build();
    }

    /**
     * 스터디 지역 정보 삭제
     * @param account
     * @param path
     * @param zoneForm
     * @return
     */
    @PostMapping("/zones/remove")
    @ResponseBody
    public ResponseEntity removeZone(@CurrentAccount Account account, @PathVariable String path,
                                     @RequestBody ZoneForm zoneForm) {
        /* 스터디 정보 조회 */
        Study study = studyService.getStudyToUpdateZone(account, path);

        /* 초기 데이터에 존재하는지 지역 정보 조회 */
        Zone zone = zoneRepository.findByCityAndProvince(zoneForm.getCityName(), zoneForm.getProvinceName());
        if (zone == null) {
            return ResponseEntity.badRequest().build();
        }

        /* 지역 정보 삭제 */
        studyService.removeZone(study, zone);
        return ResponseEntity.ok().build();
    }

    /**
     * 지역 정보 조회
     * @param account
     * @param path
     * @param model
     * @return
     */
    @GetMapping("/study")
    public String studySettingForm(@CurrentAccount Account account, @PathVariable String path, Model model) {
        /* 스터디 정보 조회 */
        Study study = studyService.getStudyToUpdate(account, path);

        model.addAttribute(account);
        model.addAttribute(study);

        return "study/settings/study";
    }

    /**
     * 스터디 오픈
     * @param account
     * @param path
     * @param attributes
     * @return
     */
    @PostMapping("/study/publish")
    public String publishStudy(@CurrentAccount Account account, @PathVariable String path,
                               RedirectAttributes attributes) {
        /* 스터디 정보 조회 */
        Study study = studyService.getStudyToUpdateStatus(account, path);

        /* open */
        studyService.publish(study);

        attributes.addFlashAttribute("message", "스터디를 공개했습니다.");
        return "redirect:/study/" + study.getEncodedPath() + "/settings/study";
    }

    /**
     * 스터디 종료
     * @param account
     * @param path
     * @param attributes
     * @return
     */
    @PostMapping("/study/close")
    public String closeStudy(@CurrentAccount Account account, @PathVariable String path,
                             RedirectAttributes attributes) {
        /* 스터디 정보 조회 */
        Study study = studyService.getStudyToUpdateStatus(account, path);

        /* close */
        studyService.close(study);

        attributes.addFlashAttribute("message", "스터디를 종료했습니다.");
        return "redirect:/study/" + study.getEncodedPath() + "/settings/study";
    }

    /**
     * 스터디 모집 시작으로 변경
     * @param account
     * @param path
     * @param model
     * @param attributes
     * @return
     */
    @PostMapping("/recruit/start")
    public String startRecruit(@CurrentAccount Account account, @PathVariable String path, Model model,
                               RedirectAttributes attributes) {
        Study study = studyService.getStudyToUpdateStatus(account, path);

        if (!study.canUpdateRecruiting()) {
            attributes.addFlashAttribute("message", "1시간 안에 인원 모집 설정을 여러번 변경할 수 없습니다.");
            return "redirect:/study/" + study.getEncodedPath() + "/settings/study";
        }

        studyService.startRecruit(study);
        attributes.addFlashAttribute("message", "인원 모집을 시작합니다.");
        return "redirect:/study/" + study.getEncodedPath() + "/settings/study";
    }

    /**
     * 팀원 모집 종료
     */
    @PostMapping("/recruit/stop")
    public String stopRecruit(@CurrentAccount Account account, @PathVariable String path, Model model,
                              RedirectAttributes attributes) {
        Study study = studyService.getStudyToUpdate(account, path);
        if (!study.canUpdateRecruiting()) {
            attributes.addFlashAttribute("message", "1시간 안에 인원 모집 설정을 여러번 변경할 수 없습니다.");
            return "redirect:/study/" + study.getEncodedPath() + "/settings/study";
        }

        studyService.stopRecruit(study);
        attributes.addFlashAttribute("message", "인원 모집을 종료합니다.");
        return "redirect:/study/" + study.getEncodedPath() + "/settings/study";
    }

    /**
     * 스터디 path 변경
     * @param account
     * @param path
     * @param newPath
     * @param model
     * @param attributes
     * @return
     */
    @PostMapping("/study/path")
    public String updateStudyPath(@CurrentAccount Account account, @PathVariable String path, String newPath,
                                  Model model, RedirectAttributes attributes) {
        Study study = studyService.getStudyToUpdateStatus(account, path);

        if (!studyService.isValidPath(newPath)) {
            model.addAttribute(account);
            model.addAttribute(study);
            model.addAttribute("studyPathError", "해당 스터디 경로는 사용할 수 없습니다. 다른 값을 입력하세요.");
            return "study/settings/study";
        }

        studyService.updateStudyPath(study, newPath);

        attributes.addFlashAttribute("message", "스터디 경로를 수정했습니다.");
        return "redirect:/study/" + study.getEncodedPath() + "/settings/study";
    }

    /**
     * 스터디명 변경
     * @param account
     * @param path
     * @param newTitle
     * @param model
     * @param attributes
     * @return
     */
    @PostMapping("/study/title")
    public String updateStudyTitle(@CurrentAccount Account account, @PathVariable String path, String newTitle,
                                   Model model, RedirectAttributes attributes) {
        Study study = studyService.getStudyToUpdateStatus(account, path);

        if (!studyService.isValidTitle(newTitle)) { // title validation check
            model.addAttribute(account);
            model.addAttribute(study);
            model.addAttribute("studyTitleError", "스터디 이름을 다시 입력하세요.");
            return "study/settings/study";
        }

        studyService.updateStudyTitle(study, newTitle);

        attributes.addFlashAttribute("message", "스터디 이름을 수정했습니다.");
        return "redirect:/study/" + study.getEncodedPath() + "/settings/study";
    }

    /**
     * 스터디 삭제
     * @param account
     * @param path
     * @param model
     * @return
     */
    @PostMapping("/study/remove")
    public String removeStudy(@CurrentAccount Account account, @PathVariable String path, Model model) {
        Study study = studyService.getStudyToUpdateStatus(account, path);
        studyService.remove(study);
        return "redirect:/";
    }

}
