package com.example.userservice.controller;

import com.example.userservice.common.CommonResponse;
import com.example.userservice.dto.KafkaTestDto;
import com.example.userservice.dto.UserDto;
import com.example.userservice.entity.UserCnts;
import com.example.userservice.entity.Users;
import com.example.userservice.form.UserForm;
import com.example.userservice.kafka.KafkaProducer;
import com.example.userservice.service.UserCntsService;
import com.example.userservice.service.UserService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@Api(tags = {"UserController"})
@RestController
@RequestMapping("/users/")
@RequiredArgsConstructor
public class UserController {

    private final CommonResponse commonResponse;
    private final Environment env;
    private final UserService userService;
    private final UserCntsService userCntsService;
    private final KafkaProducer kafkaProducer;

    @GetMapping("/healthCheck")
    public ResponseEntity<?> status() {
        return commonResponse.send(String.format("It's Working in User Service on Port %s", env.getProperty("local.server.port")));
    }

    @PostMapping("")
    public ResponseEntity<?> createUser(@Valid UserForm userForm) {
        /* 1. users */
        Users users = new Users(userForm.getUserId(), userForm.getUserName(), userForm.getUserAge(), userForm.getUserSex());
        Long userIdx = userService.createUser(users);

        /* 2. user_cnts */
        UserCnts userCnts = UserCnts.createUserCnts(users, userForm.getTelNo(), userForm.getPhone());
        userCntsService.createUserCnts(userCnts);

        return commonResponse.send(userIdx);
    }

    /**
     * 회원 리스트 조회
     * @return
     */
    @GetMapping("/list")
    public ResponseEntity<?> list() {
        /*
            API 를 만들때는 엔티티를 외부로 반환하면 안된다.
            Entity 가 변경되면 API 스펙이 변경된다.
         */
        List<Users> users = userService.findUsers();

        return commonResponse.send(new ModelMapper().map(users, UserDto.class));
    }

    /**
     * kafka 테스트 API (producer: User, consumer: Order)
     * @return
     */
    @GetMapping("/kafka")
    public ResponseEntity<?> kafkaProducerTest() {
        KafkaTestDto kafkaTestDto = new KafkaTestDto();
        kafkaTestDto.setTopicName("test");
        kafkaTestDto.setProducerName("user");
        kafkaTestDto.setConsumerName("order");

        /* 카프카에 메시지 전달 실행 */
        String resultString = kafkaProducer.send("test", kafkaTestDto);

        return commonResponse.send(resultString);
    }
}
