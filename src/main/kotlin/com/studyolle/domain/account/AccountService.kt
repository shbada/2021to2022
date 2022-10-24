package com.studyolle.domain.account

import com.studyolle.domain.Account
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class AccountService(
    private val accountStore: AccountStore,
    private val passwordEncoder: PasswordEncoder
) {
    fun processNewAccount(signUpForm: AccountCommand.SignUpForm) {
        /* 신규 회원 저장 */
        saveNewAccount(signUpForm)
    }

    private fun saveNewAccount(signUpForm: AccountCommand.SignUpForm) {
        /* password set */
        signUpForm.password = passwordEncoder.encode(signUpForm.password)

        /* account entity */
        val account: Account = signUpForm.toEntity()

        /* email token 생성 */
        account.generateEmailCheckToken()

        accountStore.saveNewAccount(account)
    }

}