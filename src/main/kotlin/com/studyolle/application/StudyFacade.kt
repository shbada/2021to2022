package com.studyolle.application

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

}