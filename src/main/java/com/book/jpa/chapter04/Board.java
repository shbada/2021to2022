package com.book.jpa.chapter04;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

/*
create table board (
    id int8 not null,
    cal numeric(10, 2),
    cnt int4 not null,
    text varchar(20) default 'empty',
    primary key (id)
)
 */
//@Entity
/*
create sequence board_seq start 1 increment 1
 */
@SequenceGenerator(
        name = "BOARD_SEQ_GENERATOR",
        sequenceName = "BOARD_SEQ",
        initialValue = 1,
        allocationSize = 1
)
@Getter
@Setter
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
                    generator = "BOARD_SEQ_GENERATOR")
    private Long id;

    //  text varchar(20) default 'empty',
    @Column(columnDefinition = "varchar(20) default 'empty'")
    private String text;

    // cal numeric(10, 2),
    @Column(precision = 10, scale = 2)
    private BigDecimal cal;

    /*
    자바 기본 타입에는 null 값을 입력할 수 없다.
    JPA 는 int type 은 not null 로 지정하는데,
    @Column 이 써지게되면 nullable=true 가 기본값이므로, 이럴땐 nullable=false 를 지정해주는게 좋다.
     */
    @Column(nullable = false)
    private int cnt;
}
