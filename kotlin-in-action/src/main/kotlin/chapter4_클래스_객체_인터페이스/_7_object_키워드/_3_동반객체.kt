package chapter4_클래스_객체_인터페이스._7_object_키워드

/*
코틀린 클래스 안에는 정적인 멤버가 없다. 코틀린 언어는 자바 static 키워드를 지원하지 않는다.
그 대신 코틀린에서는 패키지 수준의 최상위 함수와 객체 선언을 활용한다.

1) 패키지 수준의 최상위 함수 : 자바의 정적 메서드 역할을 거의 대신할 수 있다.
2) 객체 선언 : 자바의 정적 메서드 역할 중 코틀린 최상위 함수가 대신할 수 없는 역할이나 정적 필드를 대신할 수 있다.

대부분의 경우 최상위 함수 활용을 더 권장한다.
하지만 최상위 함수는 private로 표시된 클래스 비굥개 멤버에 접근할 수 없다.
그래서 클래스의 인스턴스와 관계없이 호출해야 하지만, 클래스 내부 정보에 접근해야 하는 함수가 필요할 때는 클래스의 중첩된 객체 선언의
멤버 함수로 정의해야한다.

클래스 안에 정의된 객체 중 하나에 companion 이라는 특별한 표시를 붙이면 그 클래스의 동반 객체로 만들 수 있다.
동반 객체의 프로퍼티나 메서드에 접근하려면 그 동반 객체가 정의된 클래스 이름을 사용한다.
그 결과 동반 객체의 멤버를 사용하는 구문은 자바의 정적 메서드 호출이나 정적 필드 사용 구문과 같아진다.
 */
class A {
    // 동반 객체는 자신을 둘러싼 모든 private 멤버에 접근할 수 있다.
    // 따라서 동반 객체는 바깥쪽 클래스의 private 생성자를 호출할 수 있다.
    companion object {
        fun bar() {
            println("Companion object called")
        }
    }
}

fun main() {
    A.bar() // Companion object called

    val result = CompanionFactoryUser.newSubscribingUser("sss@test.com")
    print(result.nickname)


    // 클래스 이름을 통해 동반 객체에 속한 멤버를 참조할 수 있다.
    // 필요하다면 Person2.Loader 같은 방식으로 이름을 붙일 수도 있다.
    // 이름을 지정하지 않으면 동반 객체 이름은 자동으로 Companion이 된다.
    val seohae = Person2.Loader.fromJSON("seohae")
    println(seohae.name)

    val seohae2 = Person2.fromJSON("seohae2")
    println(seohae.name)
}

class CompanionUser {
    val nickname: String

    constructor(email: String) { // 부 생성자
        nickname = email.substringBefore('@')
    }

//    constructor(facebookAccountId: Int) { // 부 생성자
//        nickname = getFacebookName(facebookAccountId)
//    }
}

// 팩토리 메서드
class CompanionFactoryUser private constructor(val nickname: String) { // 주 생성자를 비공개로 만든다.
    companion object { // 동반 객체를 선언한다.
        fun newSubscribingUser(email: String) = CompanionFactoryUser(email.substringBefore('@'))
//        fun newFacebookUser(accountId: Int) = CompanionFactoryUser(getFacebookName(accountId))
    }
}

/*
팩토리 메서드는 매우 유용하다. 이름을 정할 수 있고, 그 팩토리 메서드가 선언된 클래스의 하위 클래스 객체를 반환할 수도 있다.
하지만 클래스를 확장해야만 하는 경우에는 동반 객체 멤버를 하위 클래스에서 오버라이드할 수 없으므로 여러 생성자를 사용하는 것이 낫다.
 */

/**
 * 동반 객체를 일반 객체 처럼 사용
 */

/*
동반 객체는 클래스 안에 정의된 일반 객체다.
따라서 동반 객체에 이름을 붙이거나, 동반 객체가 인터페이스를 상속하거나, 동반 객체 안에 확장 함수와 프로퍼티를 정의할 수 있다.
 */

class Person2(val name: String) {
    companion object Loader {
        fun fromJSON(jsonText: String): Person2 = Person2(jsonText) // 동반 객체가 인터페이스 구현
    }
}

/**
 * 동반 객체의 인터페이스 구현
 */
/*
다른 객체 선언과 마찬가지로 동반 객체도 인터페이스를 구현할 수 있다.
 */
interface JSONFactory<T> {
    fun fromJSON(jsonText: String): T
}

class Person3(val name: String) {
    companion object : JSONFactory<Person3> {
        override fun fromJSON(jsonText: String): Person3 = Person3(jsonText) // 동반 객체가 인터페이스 구현
    }
}

// 이제 JSON으로부터 각 원소를 다시 만들어내는 추상 팩토리가 있다면 Person3 객체를 그 팩토리에게 넘길 수 있다.
//fun loadFromJSON<T>(factory: JSONFactory<T>): T {
//    ...
//}
//loadFromJSON(Person3) // 동반 객체의 인스턴스를 함수에 넘긴다. (Person3 클래스의 이름을 사용했다.)


/**
 * 동반 객체의 확장
 */
/*
확장 함수를 사용하면 코드 기반의 다른 곳에서 정의된 클래스의 인스턴스에 대해 새로운 메서드를 정의할 수 있었다.
클래스에 동반 객체가 있으면 그 객체 안에 함수를 정의함으로써 클래스에 대해 호출할 수 있는 확장 함수를 만들 수 있다.

C 클래스 안에 동반 객체가 있고,
그 동반 객체(C.Companion) 안에 func를 정의하면 외부에서는 func()를 C.func()으로 호출할 수 있다.
 */
class Person4(val firstName: String, val lastName: String) {
    companion object {
        // 비어 있는 동반 객체를 선언한다.
    }
}

//fun Person4.Companion.fromJSON(json: String): Person { // 확장 함수를 선언한다.
//    //...
//}

// 마치 동반 객체 안에서 fromJSON 함수를 정의한 것처럼 호출할 수 있따.
// 실제로 fromJSON 는 클래스 밖에서 정의된 확장함수다. (멤버 함수가 아니다.)
// 동반 객체에 대한 확장 함수를 작성할 수 있으려면, 원래 클래스에 동반 객체를 꼭 선언해줘야한다.
//val p = Person4.fromJSON("aaa")