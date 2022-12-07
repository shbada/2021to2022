package com.batch.order.batch;

import com.batch.order.entity.User;
import com.batch.order.repository.UserRepository;
import com.batch.order.entity.Orders;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;

import java.time.LocalDate;
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
    private final int SIZE = 100;
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
        IntStream.range(0, SIZE).forEach(i -> userList.add(User.builder()
                .orders(Collections.singletonList(Orders.builder()
                        .amount(1_000)
                        .createdDate(LocalDate.of(2020, 11, 1))
                        .itemName("item" + i)
                        .build()))
                .username("test username" + i)
                .build()));

        // 100 ~ 199
        IntStream.range(0, SIZE).forEach(i -> userList.add(User.builder()
                .orders(Collections.singletonList(Orders.builder()
                        .amount(200_000)
                        .createdDate(LocalDate.of(2020, 11, 2))
                        .itemName("item" + i)
                        .build()))
                .username("test username" + i)
                .build()));

        // 200 ~ 299
        IntStream.range(0, SIZE).forEach(i -> userList.add(User.builder()
                .orders(Collections.singletonList(Orders.builder()
                        .amount(300_000)
                        .createdDate(LocalDate.of(2020, 11, 3))
                        .itemName("item" + i)
                        .build()))
                .username("test username" + i)
                .build()));

        // 300 ~ 399
        IntStream.range(0, SIZE).forEach(i -> userList.add(User.builder()
                .orders(Collections.singletonList(Orders.builder()
                        .amount(500_000)
                        .createdDate(LocalDate.of(2020, 11, 4))
                        .itemName("item" + i)
                        .build()))
                .username("test username" + i)
                .build()));

        log.info("createUsers size : " + userList.size());
        return userList;
    }
}
