package com.jpa.bookmanager.domain.listener;

import com.jpa.bookmanager.domain.User;
import com.jpa.bookmanager.domain.UserHistory;
import com.jpa.bookmanager.repository.UserHistoryRepository;
import com.jpa.bookmanager.support.BeanUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

/**
 * Entity Listener 은 스프링 빈으로 주입받질 못한다.
 * 스프링 빈을 가져오기 위해 BeanUtils.java 를 생성하여 코드를 수정한다.
 */
public class UserEntityListener {
    /* 2개도 지정 가능 */
    @PrePersist
    @PreUpdate
    public void prePersistAndPreUpdate(Object o) {
        UserHistoryRepository userHistoryRepository = BeanUtils.getBean(UserHistoryRepository.class);

        User user = (User) o;

        UserHistory userHistory = new UserHistory();

        userHistory.setUserId(user.getId());
        userHistory.setName(user.getName());
        userHistory.setEmail(user.getEmail());

        userHistoryRepository.save(userHistory);
    }
}
