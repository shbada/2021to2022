package chapter4_클래스_객체_인터페이스._5_클래스_초기화

/*
어떤 값은 저장하되, 그 값을 변경하거나 읽을때마다 정해진 로직을 실행하는 유형의 프로퍼티를 만드는 방법을 살펴보자.
값을 저장하는 동시에 로직을 실행할 수 있게 하기 위해서는 접근자 안에서 프로퍼티를 뒷받침하는 필드에 접근할 수 있어야한다.

프로퍼티에 저장된 값의 변경 이력을 로그에 남기려는 경우를 생각해보자.
그런 경우 변경 가능한 프로퍼티를 정의하되, 세터에서 프로퍼티 값을 바꿀때마다 약간의 코드를 추가로 실행해야한다.
 */
class FieldUser(val name: String) {
    var address: String = "unspecified"
        set(value: String) {
            // 추가로직 print
            println("""
            	Address was changed for $name:
                "$field" -> "$value".""".trimIndent()) // 뒷받침하는 필드 값 읽기
            field = value // 뒷받침하는 필드 값 변경
        }
}

/*
field 식별자
1) 게터 : field 값을 읽을수만 있다.
2) 세터 : field 값을 읽거나 쓸 수 있다.

변경 가능 프로퍼티의 게터와 세터 중 한쪽만 직접 정의해도 된다.
컴파일러는 디폴트 접근자 구현을 사용하건 직접 게터나 세터를 정의하건 관계없이 게터나 세터에서 field를 사용하는
프로퍼티에 대해 뒷받침하는 필드를 생성해준다.
다만 field를 사용하지 않는 커스텀 접근자 구현을 정의한다면 뒷받침하는 필드는 존재하지 않는다.
 */
fun main() {
    val user = FieldUser("Alice")
    // 이 구문은 내부적으로 address 의 set()을 호출한다.
    user.address = "address1" // 프로퍼티 값을 변경한다.
}