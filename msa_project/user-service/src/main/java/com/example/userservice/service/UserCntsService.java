package com.example.userservice.service;

import com.example.userservice.entity.UserCnts;
import com.example.userservice.entity.Users;
import com.example.userservice.repository.UserCntsRepository;
import com.example.userservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserCntsService {
    private final UserCntsRepository userCntsRepository;

    @Transactional
    public Long createUserCnts(UserCnts userCnts) {
        userCntsRepository.save(userCnts);
        return userCnts.getUserCntsIdx(); // 항상 값이 있다는 보장이 있음
    }
}
