package chapter4_클래스_객체_인터페이스._5_클래스_초기화

/*
자바에서는 생성자를 하나 이상 선언할 수 있다.
코틀린은 주(primary) 생성자와 부(secondary) 생성자를 구분한다.
또한 코틀린에서는 초기화블록(initializer block)을 통해 초기화 로직을 추가할 수 있다.
 */
// 중괄호가 없고 괄호 사이에 val 선언만 존재한다.
// 클래스 이름 뒤에 오는 괄호로 둘러싸인 코드를 '주 생성자'라고 부른다.
class User1(val nickname: String)

// 위 코드의 실제 로직
class User2 constructor(val _nickname: String) { //_nickname : 프로퍼티와 생성자 파라미터를 구분해준다.
    // 주 생성자는 생성자 파라미터를 저장하고 그 생성자 파라미터에 의해 초기화되는 프로퍼티를 정의하는 2가지 목적에 쓰인다.
    val nickname: String
    init { // 초기화 블록
        nickname = _nickname
    }
}
/*
constructor
주 생성자나 부 생성자 정의를 시작할때 사용한다.

init
초기화 블록을 시작한다.
초기화 블록에는 클래스의 객체가 만들어질때 실행될 초기화 코드가 들어간다.
초기화 블록은 주 생성자와 함께 사용된다.
주 생성자는 제한적이기 때문에 별도의 코드를 포함할 수 없으므로 초기화 블록이 필요하다.
필요하다면 클래스 안에 여러 초기화 블록을 선언할 수 있다.
 */

/*
nickname 프로퍼티를 초기화하는 코드를 nickname 프로퍼티 선언에 포함시킬 수 있어서
초기화 코드를 초기화 블록에 넣을 필요가 없다.
또 주 생성자 앞에 별다른 애노테이션이나 가시성 변경자가 없다면 constructor를 생략해도 된다.
 */
class User3(_nickname: String) { // 파라미터가 하나뿐인 주 생성자
    // 프로퍼티를 주 생성자의 파라미터로 초기화한다.
    val nickname = _nickname
}

/*
프로퍼티를 초기화하는 식이나 초기화 블록 안에서만 주 생성자의 파라미터를 참조할 수 있다.
클래스 본문에서 val 키워드를 통해 프로퍼티를 정의했다.
주 생성자의 파라미터로 프로퍼티를 초기화한다면 그 주 생성자 파라미터 이름 앞에 val 을 추가하는 방식으로
프로퍼티 정의 초기화를 간략히 쓸 수 있다.
 */
class User4(val nickname: String) // 'val'은 이 파라미터에 상응하는 프로퍼티가 생성된다는 뜻이다.

// 함수 파라미터와 마찬가지로 생성자 파라미터에도 디폴트 값을 정의할 수 있다.
class User(val nickname: String, val isSubscribed: Boolean = true) // 생성자 파라미터에 대한 디폴트 값을 제공한다.

// 클래스의 인스턴스를 만들려면 new 키워드 없이 생성자를 직접 호출하면 된다.
fun main() {
    val test1 = User("test1");
    val test2 = User("test2", false); // 모든 인자를 파라미터 선언 순서대로 지정
    val test3 = User("test3", isSubscribed = false); // 생성자 인자 중 일부에 대해 이름 지정
    println(test1.isSubscribed)
    println(test2.isSubscribed)
    println(test3.isSubscribed)
}