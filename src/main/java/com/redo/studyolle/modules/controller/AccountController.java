package com.redo.studyolle.modules.controller;

import com.redo.studyolle.modules.repository.AccountRepository;
import com.redo.studyolle.modules.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class AccountController {
    private final AccountService accountService;
    private final AccountRepository accountRepository;
}
