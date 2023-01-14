package com.designpattern.report._01_singleton.step3_enum;

import lombok.Getter;
import lombok.Setter;

/**
 * 단점 : Settings 를 로딩하는 순간 이미 객체를 만든다.
 *     : 상속을 쓰지 못한다.
 *
 * Enum 을 상속받고 있다. (serializable 을 지원받는다.)
 */
public enum EnumSingleton {
    INSTANCE;

    // default 가 private
    private EnumSingleton() {

    }

    @Getter
    @Setter
    private Integer number;
}
