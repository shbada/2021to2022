package com.group.libraryapp.service.user

import com.group.libraryapp.dto.user.request.UserCreateRequest
import com.group.libraryapp.domain.user.User
import com.group.libraryapp.domain.user.UserRepository
import com.group.libraryapp.dto.user.request.UserUpdateRequest
import com.group.libraryapp.dto.user.response.UserResponse
import com.group.libraryapp.util.fail
import com.group.libraryapp.util.findByIdOrThrow
import org.springframework.data.repository.findByIdOrNull
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
}