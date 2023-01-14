package com.designpattern.report._08_composite.step2_after;

import com.designpattern.report._08_composite.step2_after.component.Component;
import com.designpattern.report._08_composite.step2_after.composite.Bag;
import com.designpattern.report._08_composite.step2_after.leaf.Item;

/**
 * [장점]
 * - 복잡한 트리 구조를 편리하게 사용할 수 있다.
 * - 다형성/재귀 활용 가능하다.
 * - 클라이언트 코드를 변경하지 않고 새로운 엘리먼트 타입을 추가할 수 있다.
 *
 * [단점]
 * - 트리를 만들어야하기 때문에 (공통된 인터페이스를 정의해야하기 때문에) 지나친 일반화 해야하는 경우도 생길 수 있다.
 * (getPrice() 를 구현해야하는데, 이를 알아차리는게 쉽지않다) -> 해야한다면, 약간 강제 느낌
 */

public class Client {

    public static void main(String[] args) {
        Item doranBlade = new Item("도란검", 450);
        Item healPotion = new Item("체력 물약", 50);

        Bag bag = new Bag();
        bag.add(doranBlade);
        bag.add(healPotion);

        Client client = new Client();
        client.printPrice(doranBlade);
        client.printPrice(bag);
    }

    /**
     * 추상적인 타입을 받는다.
     * 그렇게되면, 메서드 1개로 모든 타입을 해결할 수 있다.
     * @param component
     */
    private void printPrice(Component component) {
        System.out.println(component.getPrice());
    }


}
