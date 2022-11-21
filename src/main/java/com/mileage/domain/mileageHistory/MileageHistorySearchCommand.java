package com.mileage.domain.mileageHistory;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public class MileageHistorySearchCommand {
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class SearchPointHistory {
        private Long mileageIdx;
        private String startDate;
        private String endDate;
    }
}
