package com.studyolle.domain

import org.springframework.stereotype.Service

@Service
class AccountService(
    private val accountStore: AccountStore,
) {

}