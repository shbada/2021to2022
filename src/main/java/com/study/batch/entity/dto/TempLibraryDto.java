package com.study.batch.entity.dto;

import com.study.batch.entity.TempLibrary;
import com.study.batch.entity.TempLibraryLocal;
import com.study.batch.entity.TempLibraryType;
import lombok.*;

@Data
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class TempLibraryDto {
    private String libraryNm;
    private String bigLocal;
    private String smallLocal;
    private String libraryType;

    /**
     * TempLibrary
     * @return
     */
    @Builder
    public TempLibrary toEntity() {
        return TempLibrary.builder()
                .libraryNm(libraryNm)
                .bigLocal(bigLocal)
                .smallLocal(smallLocal)
                .libraryType(libraryType)
                .build();
    }

    /**
     * TempLibraryLocal
     * @return
     */
    @Builder
    public TempLibraryLocal toLocalEntity() {
        return TempLibraryLocal.builder()
                .bigLocal(bigLocal)
                .smallLocal(smallLocal)
                .build();
    }

    /**
     * TempLibraryType
     * @return
     */
    @Builder
    public TempLibraryType toTypeEntity() {
        return TempLibraryType.builder()
                .libraryType(libraryType)
                .build();
    }
}
