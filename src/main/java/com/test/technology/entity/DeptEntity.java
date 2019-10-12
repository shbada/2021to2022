package com.test.technology.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "DEPT")
public class DeptEntity {
    @Id
    @Column(name = "DEPTNO")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int deptNo;

    @Column(name = "DNAME")
    private String dName;

    @Column(name = "LOC")
    private String loc;

}
