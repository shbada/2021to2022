package _10_코틀린에서_상속_다루는_방법

interface Swimable {
    // 프로퍼티 생성 가능
    // 자식클래스에서 get() 선언
    val swimAbility: Int

    // 이렇게도 가능
    val swimAbility2: Int
        get() = 2

    // 코틀린의 인터페이스는 default 키워드 없이 구현 가능하다.
    // 추상 메서드도 만들 수 있다.
    fun act() {
        println("어푸 어푸")
        println(swimAbility)
        println(swimAbility2)
    }
}