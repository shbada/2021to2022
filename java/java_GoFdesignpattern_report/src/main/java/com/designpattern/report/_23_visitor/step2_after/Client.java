package com.designpattern.report._23_visitor.step2_after;

import com.designpattern.report._23_visitor.step2_after.element.Rectangle;
import com.designpattern.report._23_visitor.step2_after.element.Shape;
import com.designpattern.report._23_visitor.step2_after.visitor.Device;
import com.designpattern.report._23_visitor.step2_after.visitor.Pad;

/**
 * 기존 코드를 변경하지 않고 새로운 기능을 추가하는 방법
 *
 * Visitor
 * ConcreteVisitor
 * - visit(Element **)
 *
 * Element
 * ElementA, ElementB
 * - accept(Visitor) (변하지 않는 메서드) 여기서 Visitor.visit()을 호출해준다.
 *
 * double dispatch (다형성/분배/배치)
 *
 * [장점]
 * - 기존 코드를 수정이 적다. (새로운 visitor 추가할떄 기존 device의 영향은 없다)
 *
 * [단점]
 * - 구조 자체가 복잡하다. double dispatch 의 흐름을 읽는것이 어렵다.
 * - visitor 이 element 를 알아야한다. (Device 인터페이스)
 * - element 가 추가되거나 삭제되면 visitor 코드 수정이 발생한다.
 */
public class Client {

    public static void main(String[] args) {
        /*
         * 실제로 2번 디스패치가 발생 (구체적인 타입을 2번 찾아간다)
         * 1. accept 가 어떤 클래스의 메서드인가? (rectrangle 이겠구나)
         *    device.print(..) 에서 어떤 device 인가? (Pad 겠구나)
         * 2. Pah 의 print() 에서 Retrangle 을 찾아간다.
         */
        Shape rectangle = new Rectangle();
        Device device = new Pad();
        rectangle.accept(device);

        /*
        Shape
        void printTo(Phone phone);
        void printTo(Watch watch);

        이때 shape.printTo(Device) 일때
        컴파일타임에 타입 체크를 한다.
        오버라이딩은 컴파일 타임에 구체 클래스로 넘겨져야한다.

        Device 가 아무리 Phone, Watch 의 상위 타입이여도
        이 타입(Device)을 컴파일 타임에 구체적으로 어떤 타입이 될지를 모른다.
         */
    }
}
