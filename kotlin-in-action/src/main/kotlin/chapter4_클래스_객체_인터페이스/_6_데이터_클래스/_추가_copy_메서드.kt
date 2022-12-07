package chapter4_클래스_객체_인터페이스._6_데이터_클래스

/*
데이터 클래스와 불변성 : copy() 메서드

데이터 클래스의 프로퍼티가 꼭 val일 필요는 없다.
원한다면 var 프로퍼티를 써도 된다.
하지만 데이터 클래스의 모든 프로퍼티를 읽기 전용으로 만들어서 데이터 클래스를 불변 클래스로 만들라고 권장한다.
HashMap 등의 컨테이너에 데이터 클래스 객체를 담는 경우엔 불변성이 필수적이다.
데이터 클래스 객체를 키로 하는 값을 컨테이너에 담은 다음에 키로 쓰인 데이터 객체의 프로퍼티를 변경하면,
컨테이너 상태가 잘못될 수 있다.
다중스레드 프로그램의 경우 스레드 동기화를 해야할 필요가 줄어든다.

데이터 클래스 인스턴스를 불변 객체로 더 쉽게 활용할 수 있게 코틀린 컴파일러는 한가지 편의 메서드를 제공한다.
그 메서드는 객체를 복사(copy)하면서 일부 프로퍼티를 바꿀 수 있게 해주는 copy 메서드다.
객체를 메모리상에서 직접 바꾸는 대신 복사본을 만드는 편이 더 낫다.

복사본은 원본과 다른 생명주기를 가지며, 복사를 하면서 일부 프로퍼티 값을 바꾸거나 복사본을 제거해도
원본을 참조하는 다른 부분에 전혀 영향을 끼치지 않는다.


 */
data class CopyClient(val name: String, val postalCode: Int)

class CopyCustomClient(val name: String, val postalCode: Int) {
    /* equals */
    /*
    Any : 코틀린의 모든 클래스의 최상위 클래스다. Any? 는 null이 될수 있는 타입이므로 other 는 null이 될 수 있다.
     */
    override fun equals(other: Any?) : Boolean {
        // other 이 Client 인지 검사 (is 는 자바의 instanceOf와 같다.)
        if (other == null || other !is CopyCustomClient) return false
        // 두 객체의 프로퍼티 값이 서로 같은지 검사
        return name == other.name && postalCode == other.postalCode
    }

    /* toString */
    override fun toString() = "Client(name=$name, postalCode=$postalCode)"

    /* hashCode */
    override fun hashCode() : Int = name.hashCode() * 31 + postalCode

    /* copy */
    fun copy(name: String = this.name, postalCode: Int = this.postalCode) = CopyCustomClient(name, postalCode)
}

fun main() {
    val client2 = CopyCustomClient("김서해", 12345)
    println(client2) // Client(name=김서해, postalCode=12345)
    println(client2.copy(postalCode = 12345)) // Client(name=김서해, postalCode=12345)

    val client = CopyClient("김서해", 12345)
    println(client.copy(postalCode = 12345)) // Client1(name=김서해, postalCode=12345)
}