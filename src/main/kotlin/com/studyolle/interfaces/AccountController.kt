package com.studyolle.interfaces

import com.studyolle.application.AccountFacade
import com.studyolle.interfaces.dto.AccountDto
import com.studyolle.interfaces.mapper.AccountDtoMapper
import lombok.RequiredArgsConstructor
import lombok.extern.slf4j.Slf4j
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/account")
class AccountController(
    private val accountFacade: AccountFacade,
) {
    @PostMapping
    fun signUpSubmit(
        @RequestBody @Valid signUpForm: AccountDto.SignUpForm
    ) {
        accountFacade.processNewAccount(AccountDtoMapper.of(signUpForm))
    }
}