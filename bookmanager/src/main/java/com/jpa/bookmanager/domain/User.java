package com.jpa.bookmanager.domain;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Data /* @NoArgsConstructor 기본 생성자 (인자없는 생성자) 포함 : JPA 에서는 반드시 필요하다. */
@RequiredArgsConstructor
@NoArgsConstructor
@AllArgsConstructor
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

//    @OneToMany(fetch = FetchType.EAGER)
//    private List<Address> address;
}
