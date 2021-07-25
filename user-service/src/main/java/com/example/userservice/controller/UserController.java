package com.example.userservice.controller;

import com.example.userservice.dto.UserDto;
import com.example.userservice.jpa.UserEntity;
import com.example.userservice.service.UserService;
import com.example.userservice.vo.Greeting;
import com.example.userservice.vo.RequestUser;
import com.example.userservice.vo.ResponseUser;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.ArrayList;

@RestController
@RequestMapping("")
@RequiredArgsConstructor
@Slf4j
public class UserController {

    private final Environment env;
    private final UserService userService;

    private final Greeting greeting; /* 예 제므로 일단 주입으로 개발 */

    @GetMapping("/health_check")
    public String status() {
        log.info(env.getProperty("local.server.port"));
        log.info(env.getProperty("server.port"));
        log.info(env.getProperty("token.secret"));
        log.info(env.getProperty("token.expiration_time"));

        return String.format("It's Working in User Service"
                    + ", port(local.server.port) = " +  env.getProperty("local.server.port")
                    + ", port(server.port) = " + env.getProperty("server.port")
                    + ", token secret = " + env.getProperty("token.secret")
                    + ", token expiration time = " + env.getProperty("token.expiration_time")
        );
    }

    @GetMapping("/welcome")
    public String welcome() {
//        return env.getProperty("greeting.message");
        return greeting.getMessage();
    }

    @PostMapping("/users")
    public ResponseEntity<?> createUser(@RequestBody RequestUser user) {
        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT); /* 강력하게 */

        UserDto userDto = mapper.map(user, UserDto.class);

        userService.createUser(userDto);

        ResponseUser responseUser = mapper.map(userDto, ResponseUser.class);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseUser);
    }

    @GetMapping("/users")
    public ResponseEntity<?> getUsers() {
        Iterable<UserEntity> userList = userService.getUserByAll();

        List<ResponseUser> result = new ArrayList<>();
        userList.forEach(v -> {
            result.add(new ModelMapper().map(v, ResponseUser.class));
        });

        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

    @GetMapping("/users/{userId}")
    public ResponseEntity<?> getUser(@PathVariable String userId) {
        UserDto userDto = userService.getUserById(userId);

        return ResponseEntity.status(HttpStatus.CREATED).body(new ModelMapper().map(userDto, ResponseUser.class));
    }
}
