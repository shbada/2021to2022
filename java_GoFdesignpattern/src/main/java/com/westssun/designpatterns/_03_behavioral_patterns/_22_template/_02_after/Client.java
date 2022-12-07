package com.westssun.designpatterns._03_behavioral_patterns._22_template._02_after;

/*
로직이 매우 비슷한데 중간에 몇줄만 다르면? 이런 경우 템플릿 메소드 사용하기가 좋다.

템플릿 메서드 패턴
알고리즘 구조를 서브 클래스가 확장할 수 있도록 템플릿으로 제공하는 방법

[장점]
템플릿 코드를 재사용하고, 중복 코드를 줄일 수 있다.
템플릿 코드를 변경하지 않고 상속을 받아서 구체적인 알고리즘만 변경할 수 있다.

[단점]
리스코프 치환 원칙 위반할 수도 있다.
(상속 구조에서 상위 클래스 타입으로 사용하는 코드에서 상위 타입이 아닌 해당 클래스의 하위 클래스 로 임의로 바꾸더라도 코드가 동작해야한다.)
-> 자식 클래스로 변경해도 부모클래스의 메서드 의도를 동일하게 수행해야한다.
하위클래스에서 다양한 로직을 수행하도록 변경할수록 위 원칙을 꺨 수 있고, 로직도 복잡해진다.
 */
public class Client {

    public static void main(String[] args) {
        // FileProcessor fileProcessor = new Plus("number.txt");
        FileProcessor fileProcessor = new Multiply("number.txt");
        int result = fileProcessor.process((sum, number) -> sum += number);
        System.out.println(result);
    }
}
