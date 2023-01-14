package com.jpa.bookmanager.domain.listener;

import com.jpa.bookmanager.domain.User;
import com.jpa.bookmanager.domain.UserHistory;
import com.jpa.bookmanager.repository.UserHistoryRepository;
import com.jpa.bookmanager.support.BeanUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.PostPersist;
import javax.persistence.PostUpdate;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

/**
 * Entity Listener 은 스프링 빈으로 주입받질 못한다.
 * 스프링 빈을 가져오기 위해 BeanUtils.java 를 생성하여 코드를 수정한다.
 */
public class UserEntityListener {
    /* 2개도 지정 가능 */
    @PostPersist // pre -> post 로 해서 user save 후에 처리되도록 (userId null 이 아니게 잘 들어갈것임)
    @PostUpdate
    public void prePersistAndPreUpdate(Object o) {
        UserHistoryRepository userHistoryRepository = BeanUtils.getBean(UserHistoryRepository.class);

        User user = (User) o;

        UserHistory userHistory = new UserHistory();

        // userHistory.setUserId(user.getId()); // pre 로 하면 userId 가 셋 되기 전이라 null 이 들어간다.
        userHistory.setName(user.getName());
        userHistory.setEmail(user.getEmail());
        // UserHistory.java -> @ManyToOne private User user 추가 후 추가
        userHistory.setUser(user);

        userHistoryRepository.save(userHistory);
    }
}
