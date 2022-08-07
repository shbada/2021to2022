package com.studyolle.modules.zone.form;

import com.studyolle.entity.Zone;
import lombok.Data;

@Data
public class ZoneForm {

    private String zoneName;

    /**
     * city 조회
     * @return
     */
    public String getCityName() {
        return zoneName.substring(0, zoneName.indexOf("("));
    }

    /**
     * privince 조회
     * @return
     */
    public String getProvinceName() {
        return zoneName.substring(zoneName.indexOf("/") + 1);
    }

    /**
     * localName 조회
     * @return
     */
    public String getLocalNameOfCity() {
        return zoneName.substring(zoneName.indexOf("(") + 1, zoneName.indexOf(")"));
    }

    /**
     * Zone 인스턴스 리턴
     * @return
     */
    public Zone getZone() {
        return Zone.builder().city(this.getCityName())
                .localNameOfCity(this.getLocalNameOfCity())
                .province(this.getProvinceName()).build();
    }

}
