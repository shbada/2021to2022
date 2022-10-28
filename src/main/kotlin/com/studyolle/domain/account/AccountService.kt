package com.studyolle.domain.account

import com.studyolle.domain.Account
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class AccountService(
    private val accountStore: AccountStore,
    private val passwordEncoder: PasswordEncoder,
    private val emailService: EmailService
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

        /* 이메일 토큰 발송 */
        this.sendSignUpConfirmEmail(account) // token 은 위 saveNewAccount 에서 저장

        accountStore.saveNewAccount(account)
    }

    fun sendSignUpConfirmEmail(newAccount: Account) {
        /* email content */
        val contentMap = mutableMapOf<String, String>();

        contentMap["link"] = "/check-email-token?token=" + newAccount.emailCheckToken +
                "&email=" + newAccount.email
        contentMap["nickname"] = newAccount.nickname
        contentMap["linkName"] = "이메일 인증하기"
        contentMap["message"] = "스터디올래 서비스를 사용하려면 링크를 클릭하세요."

        /* send */
        emailService.sendEmail(contentMap.entries.joinToString())
    }
}