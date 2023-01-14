package com.mileage.interfaces.dto;

import com.mileage.domain.enums.MileageManageType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public class MileageHistoryDto {
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class searchRequest {
        private Long mileageIdx;
        private String startDate;
        private String endDate;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class InfoResponse {
        private Long mileageIdx;
        private int point;
        private MileageManageType mileageManageType;
        private String createdAt;
    }
}
