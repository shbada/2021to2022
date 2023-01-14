package study.querydsl.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class MemberDto {
    private String username;
    private int age;

    public MemberDto() {
    }

    /**
     * 생성자 + @QueryProjection
     * 이 어노테이션이 있으면 MemberDto 도 QMemberDto 를 만든다.
     * @param username
     * @param age
     */
    @QueryProjection
    public MemberDto(String username, int age) {
        this.username = username;
        this.age = age;
    }
}
