package com.example.membergradebatch.batch;

import com.example.membergradebatch.entity.User;
import com.example.membergradebatch.repository.UserRepository;
import lombok.RequiredArgsConstructor;
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
public class SaveUserTasklet implements Tasklet {
    private final UserRepository userRepository;

    @Override
    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
        List<User> users = createUsers();

        Collections.shuffle(users); /* 섞기 */
        userRepository.saveAll(users);

        return RepeatStatus.FINISHED;
    }

    private List<User> createUsers() {
        List<User> userList = new ArrayList<>();

        IntStream.range(0, 100).forEach(i -> userList.add(User.builder()
                .totalAmount(1_000)
                .username("test username" + i)
                .build()));

        IntStream.range(100, 200).forEach(i -> userList.add(User.builder()
                .totalAmount(200_000)
                .username("test username" + i)
                .build()));

        IntStream.range(200, 300).forEach(i -> userList.add(User.builder()
                .totalAmount(300_000)
                .username("test username" + i)
                .build()));

        IntStream.range(200, 400).forEach(i -> userList.add(User.builder()
                .totalAmount(500_000)
                .username("test username" + i)
                .build()));

        return userList;
    }
}
