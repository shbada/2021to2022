package chapter4_클래스_객체_인터페이스._6_데이터_클래스

/*
자바와 마찬가지로 코틀린 클래스도 toString, equals, hashCode 등을 오버라이드 할 수 있다.
코틀린은 이런 메서드 구현을 자동으로 생성해줄 수 있다.
우선 직접 구현해보자.
 */

// 고객 이름과 우편번호를 저장하는 간단한 Client 클래스
class Client(val name: String, val postalCode: Int)

/*
toString()
자바처럼 코틀린의 모든 클래스도 인스턴스의 문자열 표현을 얻을 방법을 제공한다.
 */

/*
equals()

자바에서는 ==를 원시타입과 참조 타입을 비교할때 사용한다.
원시 타입의 경우
- 두 피연산자의 값이 같은가? (동등성)

참조 타입의 경우
- 피연산자의 주소가 같은가? (참조 비교)

코틀린에서는 == 연산자가 내부적으로 equals를 호출해서 객체를 비교한다.
따라서 클래스가 equals를 오버라이드하면 ==를 통해 안전하게 그 클래스의 인스턴스를 비교할 수 있다.
참조 비교를 위해서는 === 연산자를 사용할 수 있다.
연산자는 자바에서 객치의 참조를 비교할때 사용하는 ==와 동일하다.
 */

/*
hashCode()
자바에서는 equals를 오버라이드할때 반드시 hashCode 로 함께 오버라이드해야한다.
 */
class CustomClient(val name: String, val postalCode: Int) {
    /* equals */
    /*
    Any : 코틀린의 모든 클래스의 최상위 클래스다. Any? 는 null이 될수 있는 타입이므로 other 는 null이 될 수 있다.
     */
    override fun equals(other: Any?) : Boolean {
        // other 이 Client 인지 검사 (is 는 자바의 instanceOf와 같다.)
        if (other == null || other !is CustomClient) return false
        // 두 객체의 프로퍼티 값이 서로 같은지 검사
        return name == other.name && postalCode == other.postalCode
    }

    /* toString */
    override fun toString() = "Client(name=$name, postalCode=$postalCode)"

    /* hashCode */
    override fun hashCode() : Int = name.hashCode() * 31 + postalCode
}

fun main() {
    /* toString */
    val client1 = CustomClient("김서해", 1234)
    println(client1) // Client(name=김서해, postalCode=1234)

    /* equals */
    val client2 = Client("김서해2", 12345)
    val client2_2 = Client("김서해2", 12345)
    println(client2 == client2_2) // false

    // override
    val client3 = CustomClient("김서해3", 12345)
    val client3_2 = CustomClient("김서해3", 12345)
    println(client3 == client3_2) // true
}

/*
위 코드는 모두 코틀린에서 자동으로 생성해준다.
data 라는 변경자를 클래스 앞에 붙이면 필요한 메서드를 컴파일러가 직접 생성해준다.
data 변경자가 붙은 클래스를 데이터 클래스라고 부른다.

- 인스턴스간 비교를 위한 euqals
- HashMap과 같은 해시 기반 컨테이너에서 키로 사용할 수 있는 hashCode
- 클래스의 각 필드를 선언 순서대로 표시하는 문자열 표현을 만들어주는 toString
 */
data class AutoClient(val name: String, val postalCode: Int)

/*
equals와 hashCode는 주 생성자에 나열된 모든 프로퍼티를 고려해 만들어진다.
생성된 equals 메서드는 모든 프로퍼티 값의 동등성을 확인한다.
hashCode 메서드는 모든 프로퍼티의 해시 값을 바탕으로 계산한 해시 값을 반환한다.
이때 주 생성자 밖에 정의된 프로퍼티는 equals, hashCode를 계산할때 고려의 대상이 아니므로 유의하자.
 */


