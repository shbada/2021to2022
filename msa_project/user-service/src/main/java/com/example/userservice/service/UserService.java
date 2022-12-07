package com.example.userservice.service;

import com.example.userservice.entity.Users;
import com.example.userservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    /**
     * 유저 생성
     * @param users
     * @return
     */
    @Transactional
    public Long createUser(Users users) {
        userRepository.save(users);
        return users.getUserIdx(); // 항상 값이 있다는 보장이 있음
    }

    /**
     * 유저 리스트 조회
     * @return
     */
    public List<Users> findUsers() {
        return (List<Users>) userRepository.findAll();
    }
}
