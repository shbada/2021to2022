package chapter3_함수_정의와_호출._4_함수를_호출하기_쉽게만들기

/*
다양한 정적 메서드를 모아두는 역할만 담당하여, 특별한 상태나 인스턴스 메서드는 없는 클래스가 생겨난다.
JDK의 Collections 클래스가 전형적인 예다.
코틀린에서는 이런 무의미한 클래스가 필요 없다.
대신 함수를 직접 소스 파일의 최상위 수준, 모든 다른 클래스의 밖에 위치시키면 된다.
그런 함수들은 여전히 그 파일의 맨 앞에 정의된 패키지의 멤버 함수다.
다른 패키지에서 그 함수를 사용하고 싶을때는 그 함수가 정의된 패키지를 임포트해야만 한다.
 */

// 최상위 함수로 선언하기
/*
JVM이 클래스 안에 들어있는 코드만을 실행할 수 있기 때문에 컴파일러는 이 파일을 컴파일할 때 새로운 클래스를 정의해준다.
코틀린만 사용하는 경우에는 그냥 그런 클래스가 생긴다는 사실만 기억하면 된다.

(JAVA)
pakage strings;

public class JoinKt {
    public static String joinToString(...) {
        ...
    }
}

코틀린의 모든 최상위 함수는 이 클래스의 정적인 메서드가 된다.

클래스 이름을 변경하고 싶다면 @JvmName 어노테이션을 추가해라. (파일의 맨 앞, 패키지 이름 선언 이전에)
 */
fun <T> defaultJoinToString2( // default 값이 지정된 파라미터들
    collection: Collection<T>,
    separator: String = ", ",
    prefix: String = "",
    postfix: String = ""
): String {
    val result = StringBuilder(prefix)

    for ((index, element) in collection.withIndex()) {
        if (index > 0) result.append(separator)  // 첫 원소 앞에는 구분자를 붙이면 안된다.
        result.append(element)
    }

    result.append(postfix)

    return result.toString()
}