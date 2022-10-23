package com.studyolle.infrastructure

import com.studyolle.domain.AccountStore
import org.springframework.stereotype.Component

@Component
class AccountStoreImpl(
    private val accountRepository: AccountRepository
): AccountStore {

}