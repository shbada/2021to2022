package com.studyolle.modules.event.service;

import com.studyolle.entity.Account;
import com.studyolle.entity.Enrollment;
import com.studyolle.entity.Event;
import com.studyolle.modules.event.repository.EnrollmentRepository;
import com.studyolle.modules.event.event.EnrollmentAcceptedEvent;
import com.studyolle.modules.event.event.EnrollmentRejectedEvent;
import com.studyolle.entity.Study;
import com.studyolle.modules.event.form.EventForm;
import com.studyolle.modules.event.repository.EventRepository;
import com.studyolle.modules.study.event.StudyUpdateEvent;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@Transactional
@RequiredArgsConstructor
public class EventService {

    private final EventRepository eventRepository;
    private final ModelMapper modelMapper;
    private final EnrollmentRepository enrollmentRepository;
    private final ApplicationEventPublisher eventPublisher;

    /**
     * 모임 등록
     * @param event
     * @param study
     * @param account
     * @return
     */
    public Event createEvent(Event event, Study study, Account account) {
        event.setCreatedBy(account);
        event.setCreatedDateTime(LocalDateTime.now());
        event.setStudy(study);

        /* 모임 생성 알림 수행 */
        eventPublisher.publishEvent(new StudyUpdateEvent(event.getStudy(),
                "'" + event.getTitle() + "' 모임을 만들었습니다."));

        return eventRepository.save(event);
    }

    /**
     * 모임 수정
     * @param event
     * @param eventForm
     */
    public void updateEvent(Event event, EventForm eventForm) {
        modelMapper.map(eventForm, event);

        /* 선착순 모임에 인원 가입 가능 여부 체크  */
        event.acceptWaitingList();

        /* 모임 수정 알림 수행 */
        eventPublisher.publishEvent(new StudyUpdateEvent(event.getStudy(),
                "'" + event.getTitle() + "' 모임 정보를 수정했으니 확인하세요."));
    }

    /**
     * 모임 취소 (삭제)
     * @param event
     */
    public void deleteEvent(Event event) {
        /* 모임 삭제 */
        eventRepository.delete(event);

        /* 모임 삭제 알림 수행 */
        eventPublisher.publishEvent(new StudyUpdateEvent(event.getStudy(),
                "'" + event.getTitle() + "' 모임을 취소했습니다."));
    }

    /**
     * 참가 신청
     * @param event
     * @param account
     */
    public void newEnrollment(Event event, Account account) {
        /* 이미 가입된 모임인지 체크 */
        if (!enrollmentRepository.existsByEventAndAccount(event, account)) {
            Enrollment enrollment = new Enrollment();
            enrollment.setEnrolledAt(LocalDateTime.now());
            enrollment.setAccepted(event.isAbleToAcceptWaitingEnrollment());
            enrollment.setAccount(account);

            /* 참가 신청 */
            event.addEnrollment(enrollment);

            enrollmentRepository.save(enrollment);
        }
    }

    /**
     * 참가신청 취소
     * @param event
     * @param account
     */
    public void cancelEnrollment(Event event, Account account) {
        /* 모임 참가 정보 조회 */
        Enrollment enrollment = enrollmentRepository.findByEventAndAccount(event, account);

        /* 모임 출석여부 체크한 경우 */
        if (!enrollment.isAttended()) {
            /* 참가신청 취소 */
            event.removeEnrollment(enrollment);
            enrollmentRepository.delete(enrollment);

            /* 다음 대기자 승인 */
            event.acceptNextWaitingEnrollment();
        }
    }

    /**
     * 모임 승인
     * @param event
     * @param enrollment
     */
    public void acceptEnrollment(Event event, Enrollment enrollment) {
        /* 모임 승인 */
        event.accept(enrollment);

        /* 모임 승인 알림 수행 */
        eventPublisher.publishEvent(new EnrollmentAcceptedEvent(enrollment));
    }

    /**
     * 모임 반려
     * @param event
     * @param enrollment
     */
    public void rejectEnrollment(Event event, Enrollment enrollment) {
        /* 모임 반려 */
        event.reject(enrollment);

        /* 모임 반려 알림 수행 */
        eventPublisher.publishEvent(new EnrollmentRejectedEvent(enrollment));
    }

    /**
     * 모임 출석체크
     * @param enrollment
     */
    public void checkInEnrollment(Enrollment enrollment) {
        /* 모임 출석체크 */
        enrollment.setAttended(true);
    }

    /**
     * 모임 출석취소
     * @param enrollment
     */
    public void cancelCheckInEnrollment(Enrollment enrollment) {
        /* 모임 출석 취소 */
        enrollment.setAttended(false);
    }
}
