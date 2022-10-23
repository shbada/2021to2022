package com.studyolle.application

import com.studyolle.domain.AccountService
import org.springframework.stereotype.Service

@Service
class AccountFacade(
    private val accountService: AccountService,
) {
}