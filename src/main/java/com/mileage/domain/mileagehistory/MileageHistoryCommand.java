package com.mileage.domain.mileagehistory;

import com.mileage.domain.enums.MileageManageType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

public class MileageHistoryCommand {
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Info {
        private Long mileageIdx;
        private int point;
        private MileageManageType mileageManageType;
        private LocalDateTime createdAt;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class RegisterPointHistory {
        private Long mileageIdx;
        private int point;
        private MileageManageType mileageManageType;
    }
}
