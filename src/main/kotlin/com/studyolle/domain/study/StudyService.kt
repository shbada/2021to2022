package com.studyolle.domain.study

import com.studyolle.domain.Account
import com.studyolle.domain.Study
import org.slf4j.LoggerFactory
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class StudyService(
    private val studyStore: StudyStore,
) {
    private val log = LoggerFactory.getLogger(this::class.java)

    fun createNewStudy(registerForm: StudyCommand.RegisterForm) {
        val study: Study = registerForm.toEntity();

        /* 스터디 저장 */
        studyStore.createNewStudy(study)

        /* TODO 관리자 정보 저장 */
    }
}