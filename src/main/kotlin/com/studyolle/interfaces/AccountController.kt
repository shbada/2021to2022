package com.studyolle.interfaces

import com.studyolle.application.AccountFacade
import lombok.RequiredArgsConstructor
import lombok.extern.slf4j.Slf4j
import org.springframework.web.bind.annotation.RestController

@RestController
@RequiredArgsConstructor
@Slf4j
class AccountController(
    private val accountFacade: AccountFacade,
) {
}