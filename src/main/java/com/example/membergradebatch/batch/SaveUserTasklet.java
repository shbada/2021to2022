package com.example.membergradebatch.batch;

import com.example.membergradebatch.entity.User;
import com.example.membergradebatch.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;

/**
 * 유저를 저장하기위한 Tasklet
 */
@RequiredArgsConstructor
@Slf4j
public class SaveUserTasklet implements Tasklet {
    private final UserRepository userRepository;

    @Override
    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
        List<User> users = createUsers();

        Collections.shuffle(users); /* 섞기 */
        userRepository.saveAll(users);

        return RepeatStatus.FINISHED;
    }

    /**
     * 유저 생성
     * @return
     */
    private List<User> createUsers() {
        List<User> userList = new ArrayList<>();

        /** 400개 데이터 insert */
        // 0 ~ 99
        IntStream.range(0, 100).forEach(i -> userList.add(User.builder()
                .totalAmount(1_000)
                .username("test username" + i)
                .build()));

        // 100 ~ 199
        IntStream.range(100, 200).forEach(i -> userList.add(User.builder()
                .totalAmount(200_000)
                .username("test username" + i)
                .build()));

        // 200 ~ 299
        IntStream.range(200, 300).forEach(i -> userList.add(User.builder()
                .totalAmount(300_000)
                .username("test username" + i)
                .build()));

        // 300 ~ 399
        IntStream.range(300, 400).forEach(i -> userList.add(User.builder()
                .totalAmount(500_000)
                .username("test username" + i)
                .build()));

        log.info("createUsers size : " + userList.size());
        return userList;
    }
}
