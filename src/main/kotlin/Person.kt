// name, age : property
// Int? : null 이 될 수 있는 타입
// Int? = null : 파라미터 default 값 설정
// '데이터' 클래스
data class Person(val name: String, val age: Int? = null)
    fun main(args: Array<String>) { // 최상위 함수
        // age = 29 : 이름 붙인 파라미터
        val persons = listOf(Person("영희"), Person("철수", age = 29))

        // 람다식
        // 엘비스 연산자 (?: 0) : age 가 null 인 경우 0을 반환하고, 그렇지 않은 경우 age 값을 반환한다.
        val oldest = persons.maxByOrNull {it.age ?: 0}

        // 문자열 템플릿
        println("나이가 가장 많은 사람 : $oldest")

        /*
           결과 : 나이가 가장 많은 사람 : Person(name=철수, age=29)
           -> toString 을 자동 생성한다.
         */
    }

/**
 * 코틀린은 정적 타입(statically typed) ㅓㅇㄴ어다.
 * -> 모든 프로그램 구성 요소의 타입을 컴파일 시점에 알 수 있고, 프로그램 안에서 객체의 필드나 메서드를 사용할 때마다
 *    컴파일러가 타입을 검증해준다는 뜻이다.
 *
 * (타입 추론 : type Inference)
 * 코틀린은 모든 변수의 타입을 프로그래머가 직접 명시할 필요가 없다.
 * 대부분의 경우 코틀린 컴파일러가 문맥으로부터 변수 타입을 자동으로 유추할 수가 있다.
 *
 * var x = 1
 *
 * 변수 x의 타입이 Int 임을 자동으로 알아낸다.
 */