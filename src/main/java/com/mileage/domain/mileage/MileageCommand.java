package com.mileage.domain.mileage;

import com.mileage.domain.enums.MileageManageType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public class MileageCommand {
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class SavePoint {
        private Long mileageIdx;
        private int point;
        private MileageManageType mileageManageType;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Info {
        private Long mileageIdx;
        private int point;
    }
}
