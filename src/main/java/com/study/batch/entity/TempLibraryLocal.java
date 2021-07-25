package com.study.batch.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@ToString
@Table(name = "temp_library_local")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TempLibraryLocal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idx")
    private Long idx;

    @Column(name = "big_local")
    private String bigLocal;

    @Column(name = "small_local")
    private String smallLocal;

}
