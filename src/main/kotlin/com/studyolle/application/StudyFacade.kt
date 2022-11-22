package com.studyolle.application

import com.studyolle.domain.Study
import com.studyolle.domain.study.StudyCommand
import com.studyolle.domain.study.StudyService
import org.springframework.stereotype.Service

@Service
class StudyFacade(
    private val studyService: StudyService,
) {
    fun createNewStudy(registerForm: StudyCommand.RegisterForm) {
        studyService.createNewStudy(registerForm)
    }

    fun getStudy(studyIdx: Long) {
        studyService.getStudy(studyIdx)
    }

    fun findStudyWithMembersByPath(path: String): Study {
        return studyService.findStudyWithMembersByPath(path);
    }

    fun removeMember(study: Study, email: String) {
        studyService.removeMember(study, email)
    }

}