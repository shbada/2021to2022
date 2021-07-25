package com.study.batch.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@ToString
@Table(name = "temp_library")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TempLibrary {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idx")
    private Long idx;

    @Column(name = "library_nm")
    private String libraryNm;

    @Column(name = "big_local")
    private String bigLocal;

    @Column(name = "small_local")
    private String smallLocal;

    @Column(name = "library_type")
    private String libraryType;

}
