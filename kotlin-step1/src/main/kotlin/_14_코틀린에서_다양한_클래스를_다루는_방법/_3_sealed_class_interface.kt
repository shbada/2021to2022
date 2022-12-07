package _14_코틀린에서_다양한_클래스를_다루는_방법

// 상속이 가능하도록 추상클래스를 만들까 하는데, 외부에서는 이 클래스를 상속받지 않았으면 좋겠다.
// 하위클래스를 봉인하자. 우리가 작성한 클래스만 하위 클래스로 되도록 하자.
// 컴파일 타임때 하위 클래스의 타입을 모두 기억한다. 즉, 런타임때 클래스 타입이 추가될 수 없다.
// 하위 클래스는 같은 패키지 안에 있어야한다.
// Enum과 다른점
// - 클래스를 상속받을 수 있다.
// - 하위클래스는 멀티 인스턴스가 가능하다.

sealed class HyundaiCar(
    val name: String,
    val price: Long
)

// 추상화가 필요한 Entity or DTO에 sealed Class 를 활용한다.
private fun handleCar(car: HyundaiCar) {
    when (car) {
        is Avante -> TODO()
        is Grandeur -> TODO()
        is Sonata -> TODO()
    }
}

// 컴파일 타임때 하위 클래스의 타입을 모두 기억한다.
// 즉, 런타임때 클래스 타입이 추가될 수 없다.
class Avante : HyundaiCar("아반떼", 1000L)
class Sonata : HyundaiCar("소나타", 2000L)
class Grandeur : HyundaiCar("그렌저", 3000L)