package study.querydsl.entity;

import lombok.Getter;
import lombok.Setter;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Q클래스 생성
 * Tasks -> other -> compileQuerydsl
 *
 * build/generated/querydsl/study/querydsl/entity 경로에 QHello 생성
 */
@Entity
@Getter @Setter
public class Hello {
    @Id @GeneratedValue
    private Long id;
}