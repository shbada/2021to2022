package com.example.userservice.service;

import com.example.userservice.client.OrderServiceClient;
import com.example.userservice.config.Resilience4JConfig;
import com.example.userservice.dto.UserDto;
import com.example.userservice.jpa.UserEntity;
import com.example.userservice.repository.UserRepository;
import com.example.userservice.vo.ResponseOrder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.cloud.client.circuitbreaker.CircuitBreaker;
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
import org.springframework.core.env.Environment;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder; /* 주입 불가 - bean 이 아니기 떄문 (UserServiceApplication 에 빈 등록 추가) */
    private final RestTemplate restTemplate;
    private final Environment environment;
    private final OrderServiceClient orderServiceClient;
    private final CircuitBreakerFactory circuitBreakerFactory;

    @Override
    public UserDto createUser(UserDto userDto) {
        userDto.setUserId(UUID.randomUUID().toString());

        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT); /* 강력하게 */

        UserEntity userEntity = mapper.map(userDto, UserEntity.class);
        userEntity.setEncryptedPwd(passwordEncoder.encode(userDto.getPwd()));

        userRepository.save(userEntity);

        return mapper.map(userEntity, UserDto.class);
    }

    @Override
    public UserDto getUserById(String userId) {
        UserEntity userEntity = userRepository.findByUserId(userId);

        UserDto userDto = new ModelMapper().map(userEntity, UserDto.class);

        if (userDto == null) {
            throw new UsernameNotFoundException("User not found");
        }

        /* 주문 */
        // List<ResponseOrder> orders = new ArrayList<>();
        // String orderUrl = "http://127.0.0.1:8000/order-service/%s/orders"; /* user-service.yml */
        /*
        String orderUrl = String.format(environment.getProperty("order_service.url"), userId); // %s : userId
        ResponseEntity<List<ResponseOrder>> orderListResponse = restTemplate.exchange(orderUrl, HttpMethod.GET, null,
                new ParameterizedTypeReference<List<ResponseOrder>>() {
                });

        userDto.setOrders(orderListResponse.getBody());
        */
//        List<ResponseOrder> orderList = null;
//        try {
//            orderList = orderServiceClient.getOrders(userId);
//        } catch (FeignException fe) {
//            log.error(fe.getMessage());
//        }

        /* Error Decoder */
        // List<ResponseOrder> orderList = orderServiceClient.getOrders(userId);

        /* zipkin */
        log.info("Before call orders microservice");

        /* resilience4j 사용 */
        CircuitBreaker circuitBreaker = circuitBreakerFactory.create("circuitbreaker");
        List<ResponseOrder> orderList = circuitBreaker.run(() -> orderServiceClient.getOrders(userId), throwable ->
            /* 문제 발생시 아래 빈 리스트를 리턴해준다. */
            new ArrayList<>()
        );

        userDto.setOrders(orderList);

        return userDto;
    }

    @Override
    public Iterable<UserEntity> getUserByAll() {
        return userRepository.findAll();
    }

    @Override
    public UserDto getUserDetailsByEmail(String userName) {
        UserEntity userEntity = userRepository.findByEmail(userName);

        return new ModelMapper().map(userEntity, UserDto.class);
    }

    /**
     * userName 으로 이메일 조회
     * @param userName
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        /** 로그인필터 2) 두번째 실행되는 메소드 */
        UserEntity userEntity = userRepository.findByEmail(userName);

        if (userEntity == null) {
            throw new UsernameNotFoundException(userName);
        }

        return new User(userEntity.getEmail(), userEntity.getEncryptedPwd(), true, true,
                true, true, new ArrayList<>());
    }
}
