package com.studyolle.domain.study

import com.studyolle.domain.Account
import com.studyolle.domain.Study
import com.studyolle.domain.account.AccountStore
import org.slf4j.LoggerFactory
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class StudyService(
    private val studyStore: StudyStore,
    private val accountStore: AccountStore
) {
    private val log = LoggerFactory.getLogger(this::class.java)

    fun createNewStudy(registerForm: StudyCommand.RegisterForm) {
        /* get manager */
        val manager: Account? = accountStore.getAccountByEmail(registerForm.email)

        if (manager == null) {
            // error
        }

        val study: Study = registerForm.toEntity()
        study.addManager(manager);

        /* 스터디 저장 */
        studyStore.createNewStudy(study)
    }
}