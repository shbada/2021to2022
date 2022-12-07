package chapter4_클래스_객체_인터페이스._3_중첩_클래스;

import org.jetbrains.annotations.NotNull;

/**
 State 인터페이스를 구현한 ButtonState 클래스를 정의해서 ButtonJava에 대한 구체적인 정보를 저장한다.

 여기서 ButtonState 직렬화 시도시, NotSerializableException 이 발생한다.
 직렬화하려는 변수는 ButtonState 타입의 state 인스턴스인데, 왜 ButtonJava 를 직렬화할 수 없다는 예외가 발생할까?

 자바에서 다른 클래스 안에 정의한 클래스는 자동으로 내부 클래스(inner class)가 된다.
 이 예제의 ButtonState 클래스는 바깥쪽 ButtonJava에 대한 참조를 묵시적으로 포함한다.
 그 참조로 인해 ButtonState 를 직렬화할 수 없다.
 ButtonJava을 직렬화할 수 없으므로 버튼에 대한 참조가 ButtonState의 직렬화를 방해한다.

 이 문제를 해결하기 위해서는 ButtonState 를 static 클래스로 선언해야한다.
 자바에서 중첩클래스를 static으로 선언하면 그 클래스를 둘러싼 바깥쪽 클래스에 대한 묵시적인 참조가 사라진다.
 */
public class ButtonJava implements View {
    @NotNull
    @Override
    public State getCurrentState() {
        return new ButtonState();
    }

    @Override
    public void restoreState(@NotNull State state) {

    }

    /**
     * 중첩 클래스
     */
    public class ButtonState implements State {

    }
}
