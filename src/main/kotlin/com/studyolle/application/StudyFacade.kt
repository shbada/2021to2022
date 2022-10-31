package com.studyolle.application

import com.studyolle.domain.account.AccountService
import com.studyolle.domain.account.AccountCommand
import com.studyolle.domain.study.StudyCommand
import com.studyolle.domain.study.StudyService
import com.studyolle.domain.study.StudyStore
import org.springframework.stereotype.Service

@Service
class StudyFacade(
    private val studyService: StudyService
) {
    fun createNewStudy(registerForm: StudyCommand.RegisterForm) {
        studyService.createNewStudy(registerForm)
    }

}