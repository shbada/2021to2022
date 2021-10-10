package com.jpa.bookmanager.domain;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Data /* @NoArgsConstructor 기본 생성자 (인자없는 생성자) 포함 : JPA 에서는 반드시 필요하다. */
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class User {
    @Id
    @GeneratedValue /* 자동으로 증가하는 값 */
    private long id;

    @NonNull
    private String name;

    @NonNull
    private String email;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
