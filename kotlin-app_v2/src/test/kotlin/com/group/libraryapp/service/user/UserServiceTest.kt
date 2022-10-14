package com.group.libraryapp.service.user

import com.group.libraryapp.domain.user.User
import com.group.libraryapp.domain.user.UserRepository
import com.group.libraryapp.dto.user.request.UserCreateRequest
import com.group.libraryapp.dto.user.request.UserUpdateRequest
import org.assertj.core.api.AssertionsForInterfaceTypes.assertThat
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest


/**
 * 전체 테스트 할 경우,
 * 아래 @Test 메서드는 Spring Context를 공유한다.
 * h2 DB도 하나만 쓴다는 것이다.
 * saveUserTest() 에서 유저 1명이 추가되었고, getUsersTest() 가 수행될대도 2명을 추가하는데,
 * 결국 기대했던 2가 아닌 3이다.
 * 이런 문제를 해결하기 위해 테스트가 끝나면 공유 자원인 DB를 깨끗하게 비워야한다.
 * 각각 deleteAll()을 수행해도 되고, @AfterEach 로 중복 코드를 공통 코드로 활용할 수 있다.
 */
@SpringBootTest
internal class UserServiceTest @Autowired constructor(
    // 위 @Autowired 로 생략 가능
    private val userRepository: UserRepository,
    private val userService: UserService
) {
    @AfterEach
    fun clean() {
        userRepository.deleteAll()
    }

    @Test
    fun saveUserTest() {
        // given (테스트 준비)
        val request = UserCreateRequest("KIM", null)
        // Java의 DTO 필드 (null이 가능한지 판단이 불가)
        // -> NULL이 아닐거라고 가정한다. NULL 이면 그래서 에러가 발생한다. (플랫폼 타입)
        // UserCreateRequest.java 에 @Nullable 추가 (null 가능)

        // when (테스트 기능 호출)
        userService.saveUser(request)

        // then (호츨 결과 검증)
        val results = userRepository.findAll()
        assertThat(results).hasSize(1)
        assertThat(results[0].name).isEqualTo("KIM")
        assertThat(results[0].age).isNull()
    }

    @Test
    fun getUsersTest() {
        // given
        userRepository.saveAll(
            listOf(
                User("A", 20),
                User("B", null)
            )
        )

        // when
        val results = userService.getUsers()

        // then
        assertThat(results).hasSize(2) // [UserResponse(), UserResponse()]
        assertThat(results).extracting("name") // ["A", "B"]
            .containsExactlyInAnyOrder("A", "B")
        assertThat(results).extracting("age") // [20, null]
            .containsExactlyInAnyOrder(20, null)

    }

    @Test
    fun updateUserNameTest() {
        // given
        val savedUser = userRepository.save(User("A", null))
        val request = UserUpdateRequest(savedUser.id, "B")

        // when
        userService.updateUserName(request)

        // then
        val result = userRepository.findAll()[0]
        assertThat(result.name).isEqualTo("B")
    }

    @Test
    @DisplayName("유저를 삭제한다")
    fun deleteUserTest() {
        // given
        userRepository.save(User("A", null))

        // when
        userService.deleteUser("A")

        // then
        assertThat(userRepository.findAll()).isEmpty()
    }
}
