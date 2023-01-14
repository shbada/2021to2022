package com.group.libraryapp.controller.user

import com.group.libraryapp.dto.user.request.UserCreateRequest
import com.group.libraryapp.dto.user.request.UserUpdateRequest
import com.group.libraryapp.dto.user.response.UserLoanHistoryResponse
import com.group.libraryapp.dto.user.response.UserResponse
import com.group.libraryapp.service.user.UserService
import org.springframework.web.bind.annotation.*

@RestController
class UserController(
    private val userService: UserService
) {
    @PostMapping("/user")
    fun saveUser(@RequestBody request: UserCreateRequest) {
        userService.saveUser(request)
    }

//    @GetMapping("/user")
//    fun getUsers(): List<UserResponse> = userService.getUsers()

    @GetMapping("/user")
    fun getUsers(): List<UserResponse> {
        return userService.getUsers()
    }

    @PutMapping("/user")
    fun updateUserName(@RequestBody request: UserUpdateRequest) {
        userService.updateUserName(request)
    }

    @DeleteMapping("/user")
    fun deleteUser(@RequestParam name: String) { // @RequestParam 는 default 가 required true (String? 가 되면 required false로 한다)
        userService.deleteUser(name)
    }

    @GetMapping("/user/loan")
    fun findAllWithHistoryList(): List<UserLoanHistoryResponse> {
        return userService.getUserLoanHistories()
    }
}