package com.mileage.domain;

import com.mileage.domain.enums.MileageManageType;
import com.mileage.domain.mileageHistory.MileageHistoryCommand;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@NoArgsConstructor
public class MileageHistory extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx;

    @OneToOne
    private Mileage mileage;

    private int point;

    @Enumerated(EnumType.STRING)
    private MileageManageType mileageManageType;

    public void createPointHistory(MileageHistoryCommand.RegisterPointHistory registerPointHistory, Mileage mileage) {
        this.mileage = mileage;
        this.point = registerPointHistory.getPoint();
        this.mileageManageType = registerPointHistory.getMileageManageType();
    }

    public MileageHistoryCommand.Info toCommand() {
        MileageHistoryCommand.Info info = new MileageHistoryCommand.Info();
        info.setMileageIdx(this.mileage.getIdx());
        info.setPoint(this.point);
        info.setMileageManageType(this.mileageManageType);
        info.setCreatedAt(this.getCreatedAt());

        return info;
    }
}
