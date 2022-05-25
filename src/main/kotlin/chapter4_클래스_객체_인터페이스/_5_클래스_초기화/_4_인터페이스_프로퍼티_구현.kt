package chapter4_클래스_객체_인터페이스._5_클래스_초기화

/*
코틀린에서는 인터페이스에 추상 프로퍼티 선언을 넣을 수 있다.

InterfaceUser 인터페이스를 구현하는 클래스가 nickname 의 값을 얻을 수 있는 방법을 제공해야한다.
인터페이스에 있는 프로퍼티 선언에는 뒷받침하는 필드나 게터 등의 정보가 들어있지 않다.
사실 인터페이스는 아무 상태도 포함할 수 없으므로 상태를 저장할 필요가 있다면 인터페이스를 구현한 하위 클래스에서
상태 저장을 위한 프로퍼티 등을 만들어야한다.

PrivateUser는 별명을 저장하기만하고 SubscribingUser는 이메일을 함께 저장한다.
facebookUser는 페이스북 계정의 ID를 저장한다.
이 세 클래스는 각각 다른 방식으로 추상 프로퍼티 nickname을 구현한다.
*/

interface InterfaceUser {
    val nickname: String // 추상 프로퍼티
}

// 주 생성자 안에 프로퍼티를 직접 선언하는 간결한 구문 사용
// User의 추상 프로퍼티를 구현하고 있으므로 override 표시 필요
class PrivateUser(override val nickname: String) : InterfaceUser // 주 생성자에 있는 프로퍼티

class SubscribingUser(val email: String) : InterfaceUser {
    // 뒷받침필드에 저장하지 않고 매번 이메일 주소에서 별명을 계산해 반환한다.
    // 매번 호출할 때마다 substringBefore을 호출해 계산한다.
    override val nickname: String
        get() = email.substringBefore('@') // 커스텀 게터
}

//class FacebookUser(val accountId: Int) : InterfaceUser {
//    // 객체를 초기화하는 단계에 한번만 호출하여 뒷받침 필드에 저장.
//    // getFacebookName 는 페이스북에 접속해서 인증을 거친 후 원하는 데이터를 가져와야하기 때문에 비용이 많이 들 수 있다.
//    // 그래서 객체를 초기화하는 단계에 한번만 getFacebookName을 호출하게 설계했다.
//    override val nickname = getFacebookName(accountId) // 프로퍼티 초기화 식
//}

fun main() {
    println(PrivateUser("test@naver.com").nickname)
    println(SubscribingUser("test@naver.com").nickname)
}

/*
인터페이스는 추상 프로퍼티 뿐만 아니라 게터와 세터가 있는 프로퍼티를 선언할 수 있다.
그런 게터와 세터는 뒷받침하는 필드를 참조할 수 없다.
*/
interface PropertyUser {
    // 추상프로퍼티 email, 커스텀 게터가 있는 nickname
    // 하위클래스는 추상 프로퍼티인 email을 반드시 오버라이딩해야 한다.
    // 반면 nickname은 오버라이드 하지 않고 상속할 수 있다.
    // 인터페이스에 선언된 프로퍼티와 달리 클래스에 구현된 프로퍼티는 뒷받침하는 필드를 원하는대로 사용할 수 있다.
    val email: String
    val nickname: String
        get() = email.substringBefore('@') // 프로퍼티에 뒷받침하는 필드가 없다. 대신 매번 결과를 계산해 돌려준다.
}