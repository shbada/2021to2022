package _11_코틀린에서_접근제어를_다루는_방법

/*
kotlin 에서는 패키지를 namespace(위치) 지정 용도로만 사용한다.
기본 접근 지시어 : public
.kt 파일에 변수, 함수, 클래스 여러개를 바로 만들 수 있다.


1) public : 모든 곳에서 접근 가능
(어디서는 사용 가능하다.)
2) protected : 선언된 클래스 또는 하위 클래스에서만 접근 가능
(파일 최상단에는 사용 불가능하다)
3) internal : 같은 모듈에서만 접근 가능
(모듈 : 한번에 컴파일되는 Kotlin 코드)
(- 자바에서는 public 으로 판단하여, 어떤 파일에서든 가져올 수 있다.)
4) private : 선언된 클래스 내에서만 접근 가능
(같은 파일 내에서만 접근 가능하다)
(- 자바에서는 패키지가 동일한(protected)로 판단하여 동일한 패키지의 멤버에 접근가능하다)

-- 파일의 접근제어
1) public : 어디서는 사용 가능하다.
2) protected : 파일 최상단에는 사용 불가능하다
3) internal : 같은 모듈에서만 접근 가능
4) private : 같은 파일 내에서만 접근 가능하다

-- 클래스의 접근제어
1) public : 모든 곳에서 접근 가능하다.
2) protected : 선언된 클래스 또는 하위 클래스에서만 접근 가능
3) internal : 같은 모듈에서만 접근 가능
4) private : 선언된 클래스 내에서만 접근 가능

-- 생성자의 접근제어
생성자는 constructor 생략이 가능했는데, 접근지시어를 적으려면 constructor 을 명시해야한다.

-- 프로퍼티 제어
internal val name: String, // getter, setter 부여
setter 에만 추가로 가시성 부여 가능

 */
fun main() {

}

// 프로퍼티 제어 예제
class Car(
    internal val name: String, // getter, setter 부여
    private val owner: String, // getter, setter 부여
    _price: Int
) {
    // default public
    var price = _price

    // private set, public get
    var price2 = _price
        private set
}