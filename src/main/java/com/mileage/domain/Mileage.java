package com.mileage.domain;

import com.mileage.common.exception.BadRequestException;
import com.mileage.common.response.ErrorCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter
@Entity
@NoArgsConstructor
public class Mileage extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx;

    private int point;

    public void charge(int chargePoint) {
        this.point = this.point + chargePoint;
    }

    public void use(int usePoint) {
        if (this.point < usePoint) {
            throw new BadRequestException(ErrorCode.NOT_ENOUGH_POINT);
        }

        this.point = this.point - usePoint;
    }
}
