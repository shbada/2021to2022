package com.study.batch.jobs.M01_csvFile.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@ToString
@Table(name = "temp_library_type")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TempLibraryType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idx")
    private Long idx;

    @Column(name = "library_type")
    private String libraryType;



}
