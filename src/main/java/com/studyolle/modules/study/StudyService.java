package com.studyolle.modules.study;

import com.studyolle.modules.account.Account;
import com.studyolle.modules.study.event.StudyCreatedEvent;
import com.studyolle.modules.study.event.StudyUpdateEvent;
import com.studyolle.modules.tag.Tag;
import com.studyolle.modules.zone.Zone;
import com.studyolle.modules.study.form.StudyDescriptionForm;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.studyolle.modules.study.form.StudyForm.VALID_PATH_PATTERN;

@Service
@Transactional /* 트랜잭션 서비스단위로 묶기 */
@RequiredArgsConstructor
public class StudyService {

    private final StudyRepository repository;
    private final ModelMapper modelMapper;
    private final ApplicationEventPublisher eventPublisher;

    /**
     * 신규 스터디 등록
     * @param study
     * @param account
     * @return
     */
    public Study createNewStudy(Study study, Account account) {
        Study newStudy = repository.save(study);
        newStudy.addManager(account);
        return newStudy;
    }

    public Study getStudyToUpdate(Account account, String path) {
        Study study = this.getStudy(path);
        checkIfManager(account, study);
        return study;
    }

    /**
     * 스터디 정보 조회
     * @param path
     * @return
     */
    public Study getStudy(String path) {
        Study study = this.repository.findByPath(path);
        checkIfExistingStudy(path, study);
        return study;
    }

    /**
     * 스터디 소개글 수정
     * @param study
     * @param studyDescriptionForm
     */
    public void updateStudyDescription(Study study, StudyDescriptionForm studyDescriptionForm) {
        modelMapper.map(studyDescriptionForm, study);
        eventPublisher.publishEvent(new StudyUpdateEvent(study, "스터디 소개를 수정했습니다."));
    }

    /**
     * 스터디 배너 이미지 수정
     * @param study
     * @param image
     */
    public void updateStudyImage(Study study, String image) {
        study.setImage(image);
    }

    /**
     * 배너 사용 여부 사용 저장
     * @param study
     */
    public void enableStudyBanner(Study study) {
        study.setUseBanner(true);
    }

    /**
     * 배너 사용 여부 사용안함 저장
     * @param study
     */
    public void disableStudyBanner(Study study) {
        study.setUseBanner(false);
    }

    /**
     * 태그 등록
     * @param study
     * @param tag
     */
    public void addTag(Study study, Tag tag) {
        study.getTags().add(tag);
    }

    /**
     * 태그 삭제
     * @param study
     * @param tag
     */
    public void removeTag(Study study, Tag tag) {
        study.getTags().remove(tag);
    }

    /**
     * 지역 등록
     * @param study
     * @param zone
     */
    public void addZone(Study study, Zone zone) {
        study.getZones().add(zone);
    }

    /**
     * 지역 삭제
     * @param study
     * @param zone
     */
    public void removeZone(Study study, Zone zone) {
        study.getZones().remove(zone);
    }

    /**
     * 태그 수정을 위한 스터디 정보 조회
     * @param account
     * @param path
     * @return
     */
    public Study getStudyToUpdateTag(Account account, String path) {
        Study study = repository.findStudyWithTagsByPath(path);
        checkIfExistingStudy(path, study);
        checkIfManager(account, study);
        return study;
    }

    /**
     * 지역 수정을 위한 스터디 정보 조회
     * @param account
     * @param path
     * @return
     */
    public Study getStudyToUpdateZone(Account account, String path) {
        Study study = repository.findStudyWithZonesByPath(path);
        checkIfExistingStudy(path, study);
        checkIfManager(account, study);
        return study;
    }

    /**
     * 스터디상태 수정을 위한 스터디 정보 조회
     * @param account
     * @param path
     * @return
     */
    public Study getStudyToUpdateStatus(Account account, String path) {
        Study study = repository.findStudyWithManagersByPath(path);
        checkIfExistingStudy(path, study);
        checkIfManager(account, study);
        return study;
    }

    /**
     * 권한체크
     * @param account
     * @param study
     */
    private void checkIfManager(Account account, Study study) {
        if (!study.isManagedBy(account)) {
            throw new AccessDeniedException("해당 기능을 사용할 수 없습니다.");
        }
    }

    /**
     * 스터디 존재 여부 체크
     * @param path
     * @param study
     */
    private void checkIfExistingStudy(String path, Study study) {
        if (study == null) {
            throw new IllegalArgumentException(path + "에 해당하는 스터디가 없습니다.");
        }
    }

    /**
     * 스터디 오픈
     * @param study
     */
    public void publish(Study study) {
        study.publish();
        this.eventPublisher.publishEvent(new StudyCreatedEvent(study));
    }

    /**
     * 스터디 종료
     * @param study
     */
    public void close(Study study) {
        study.close();
        eventPublisher.publishEvent(new StudyUpdateEvent(study, "스터디를 종료했습니다."));
    }

    /**
     * 팀원 모집 시작
     * @param study
     */
    public void startRecruit(Study study) {
        study.startRecruit();
        eventPublisher.publishEvent(new StudyUpdateEvent(study, "팀원 모집을 시작합니다."));
    }

    /**
     * 팀원 모집 종료
     * @param study
     */
    public void stopRecruit(Study study) {
        study.stopRecruit();
        eventPublisher.publishEvent(new StudyUpdateEvent(study, "팀원 모집을 중단했습니다."));
    }

    /**
     * 스터디 path validation
     * @param newPath
     * @return
     */
    public boolean isValidPath(String newPath) {
        if (!newPath.matches(VALID_PATH_PATTERN)) {
            return false;
        }

        return !repository.existsByPath(newPath);
    }

    /**
     * 스터디 path 변경
     * @param study
     * @param newPath
     */
    public void updateStudyPath(Study study, String newPath) {
        study.setPath(newPath);
    }

    /**
     * 스터디명 validation check
     * @param newTitle
     * @return
     */
    public boolean isValidTitle(String newTitle) {
        return newTitle.length() <= 50;
    }

    /**
     * 스터디명 변경
     * @param study
     * @param newTitle
     */
    public void updateStudyTitle(Study study, String newTitle) {
        study.setTitle(newTitle);
    }

    /**
     * 스터디 삭제
     * @param study
     */
    public void remove(Study study) {
        if (study.isRemovable()) { // 삭제 가능 여부
            repository.delete(study);
        } else {
            throw new IllegalArgumentException("스터디를 삭제할 수 없습니다.");
        }
    }

    /**
     * 스터디 멤버 등록
     * @param study
     * @param account
     */
    public void addMember(Study study, Account account) {
        study.addMember(account);
    }

    /**
     * 스터디 멤버 삭제
     * @param study
     * @param account
     */
    public void removeMember(Study study, Account account) {
        study.removeMember(account);
    }

    /**
     * 스터디 존재 여부 및 조회
     * @param path
     * @return
     */
    public Study getStudyToEnroll(String path) {
        Study study = repository.findStudyOnlyByPath(path);
        checkIfExistingStudy(path, study);
        return study;
    }
}
