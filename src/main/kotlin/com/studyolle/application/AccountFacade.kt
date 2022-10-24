package com.studyolle.application

import com.studyolle.domain.account.AccountService
import com.studyolle.domain.account.AccountCommand
import org.springframework.stereotype.Service

@Service
class AccountFacade(
    private val accountService: AccountService,
) {
    fun processNewAccount(signUpForm: AccountCommand.SignUpForm) {
        accountService.processNewAccount(signUpForm);
    }

}