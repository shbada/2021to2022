package chapter4_클래스_객체_인터페이스._5_클래스_초기화

import java.util.jar.Attributes
import javax.naming.Context

/*
코틀린에서는 생성자가 여럿있는 경우가 자바보다 훨씬 적다.
자바에서 오버로드한 생성자가 필요한 상황 중 상당수는 코틀린의 디폴트 파라미터 값과 이름 붙인 인자 문법으로 해결할 수 있다.

그래소 생성자가 여럿 필요한 일반적인 상황은,
프레임워크 클래스를 확장해야하는데 여러가지 방법으로 인스턴스를 초기화할 수 있게 다양한 생성자를 지원해야 하는 경우다.
예를 들어 자바에서 선언된 생성자가 2개인 View 클래스가 있다고 하자.
그 클래스를 코틀린으로는 다음과 비슷하게 정의할 수 있다.
 */

open class View {
    // 이 클래스는 주 생성자를 선언하지 않고, 부 생성자만 2가지 선언한다.
    constructor(ctx: Context) { // 부 생성자도 constructor 키워드로 시작한다.

    }

    constructor(ctx: Context, attr: Attributes) {

    }
}

//class MyButton: View {
//    // 상위 클래스의 생성자를 호출한다.
//    // 생성자가 상위 클래스 생성자에게 객체 생성을 위임한다.
//    constructor(ctx: Context) : super() {
//    }
//
//    constructor(ctx: Context, attr: Attributes) : super() {
//    }
//}

/*
Mybutton2 클래스의 생성자중 하나가 파라미터의 디폴트 값을 넘겨서
같은 클래스의 다른 생성자(this 사용하여 참조)에게 생성을 위임한다.
두번째 생성자는 여전히 super()를 호출하여 상위 클래스 생성자에게 객체 생성을 위임한다.
 */
//class MyButton2: View {
//    // this를 사용해서 클래스 자신의 다른 생성자를 호출할 수 있다.
//    constructor(ctx: Context) : this(ctx, MY_STYLE) {
//    }
//
//    constructor(ctx: Context, attr: Attributes) : super(ctx, attr) {
//    }
//}

/*
클래스에 주 생성자가 없다면 모든 부 생성자는 반드시 상위 클래스를 초기화하거나,
다른 생성자에게 생성을 위임해야한다.
각 부 생성자에서 객체 생성을 위임하는 내용을 보면, 그 끝에는 상위 클래스 생성자를 호출한다.

부 생성자가 필요한 주된 이유는 자바 상호운용성이다.
하지만 부 생성자가 필요한 다른 경우도 있다.
클래스 인스턴스를 생성할때 파라미터 목록이 다른 생성 방법이 여럿 존재하는 경우에는 부 생성자를 여럿 둘 수 밖에 없다.

 */