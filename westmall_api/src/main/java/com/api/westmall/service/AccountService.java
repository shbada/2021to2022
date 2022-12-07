package com.api.westmall.service;

import com.api.westmall.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccountService {
    private final MemberRepository memberRepository;
}
