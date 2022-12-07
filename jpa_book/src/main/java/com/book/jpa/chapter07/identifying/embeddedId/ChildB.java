package com.book.jpa.chapter07.identifying.embeddedId;

import javax.persistence.*;

/*
create table childb (
       id varchar(255) not null,
        parent_id1 varchar(255),
        parent_id2 varchar(255),
        primary key (id)
    )
 */
@Entity
public class ChildB {
    @Id
    private String id;

    @ManyToOne
    @JoinColumns({ // name , referencedColumnName 가 같을땐 referencedColumnName 생략 가능
            // name : 자식 테이블의 컬럼명
            // referencedColumnName : 조인 대상 부모의 컬럼명
            @JoinColumn(name = "PARENT_ID1", referencedColumnName = "PARENT_ID1"),
            @JoinColumn(name = "PARENT_ID2", referencedColumnName = "PARENT_ID2")
    })
    private ParentB parentB;
}
