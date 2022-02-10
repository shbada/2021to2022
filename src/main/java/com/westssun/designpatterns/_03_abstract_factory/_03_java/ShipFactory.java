package com.westssun.designpatterns._03_abstract_factory._03_java;

import com.westssun.designpatterns._02_factory_method._02_after.Ship;
import com.westssun.designpatterns._02_factory_method._02_after.Whiteship;
import org.springframework.beans.factory.FactoryBean;

/**
 * FactoryBean 을 써야하는 경우?
 * new 가 아닌 만드는 과정이 더 복잡한 경우 직접 구현해서 클래스를 빈으로 등록하는게 낫다.
 */
public class ShipFactory implements FactoryBean<Ship> {

    /**
     * 여기서 만들어지는 클래스가 빈으로 등록된다.
     * (스프링 내부에서)
     * @return
     * @throws Exception
     */
    @Override
    public Ship getObject() throws Exception {
        /* 과정이 복잡하다고 보자.*/
        Ship ship = new Whiteship();
        ship.setName("whiteship");
        return ship;
    }

    @Override
    public Class<?> getObjectType() {
        return Ship.class;
    }
}
