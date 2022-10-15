package com.group.libraryapp.service.user

import com.group.libraryapp.domain.user.User
import com.group.libraryapp.domain.user.UserRepository
import com.group.libraryapp.dto.user.request.UserCreateRequest
import com.group.libraryapp.dto.user.request.UserUpdateRequest
import com.group.libraryapp.dto.user.response.BookHistoryResponse
import com.group.libraryapp.dto.user.response.UserLoanHistoryResponse
import com.group.libraryapp.dto.user.response.UserResponse
import com.group.libraryapp.util.fail
import com.group.libraryapp.util.findByIdOrThrow
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class UserService( // 상속 가능하게 하려면 open (open 계속 써주는게 번거롭다. build.gradle - 'org.jetbrains.kotlin.plugin.spring' 추가)
    private val userRepository: UserRepository, // 생성자 주입
) {
    @Transactional // saveUser 함수가 오버라이드 될 수 있어야 사용 가능한 어노테이션
    fun saveUser(request: UserCreateRequest) { // 오버라이딩 가능하게 하려면 open
        val newUser = User(request.name, request.age)
        userRepository.save(newUser)
    }

    @Transactional
    fun getUsers() : List<UserResponse> {
        return userRepository.findAll()
            .map { user -> UserResponse.of(user) } // user -> UserResponse
    }

    @Transactional
    fun updateUserName(request: UserUpdateRequest) {
        // findById는 Optional<T>를 반환하고있다.
        // CrudRepository (JAVA 확장함수를 제공해준다.)
//        val user = userRepository.findByIdOrNull(request.id) ?: fail() // 생성자 호출 ::클래스명
        // extension함수로 만들어보자. (ExcpetionUtil)
        val user = userRepository.findByIdOrThrow(request.id)
        user.updateName(request.name)
    }

    @Transactional
    fun deleteUser(name: String) {
        val user = userRepository.findByName(name) ?: fail()
        userRepository.delete(user)
    }

    @Transactional(readOnly = true)
    fun getUserLoanHistories(): List<UserLoanHistoryResponse> {
//        return userRepository.findAllWithHistoryList().map { user -> // 1) select user
        // queryDsl method 호출로 변경
        return userRepository.findAllWithHistories().map { user -> // 1) select user
            // 여기서 N + 1 문제 발생
            // 위 1)번에서 조회된 유저 데이터 개수만큼 계속해서 select userLoanHistory 쿼리가 수행된다.
            // 때문에 유저가 100명이라면 총 101번의 쿼리가 발생하고, 유저가 1000명이라면 총 1001번의 쿼리가 발생한다.
            // 조회 한 번에 쿼리가 많이 발생하게 될 수록, DB에 부하가 심해져 성능적인 문제가 생길 수 밖에 없다.
            // 이런 문제를 N + 1 문제라고 부른다.

            /*
                User를 기준으로 해당 사용자의 List<UserLoanHistory>를 계속해서 가져오는 이유는,
                User 객체까지는 온전히 로딩이 잘 되지만 UserLoanHistory는 가짜 객체가 들어있기 때문이다.
                이 가짜객체에 실제 접근을 하는 타이밍에 Hibernate이 DB에 SQL을 보내 온전히 로딩하는 방식이다.
                이러한 방식을 Lazy Fetching이라 한다.
             */
            UserLoanHistoryResponse.of(user)
        }
    }
}