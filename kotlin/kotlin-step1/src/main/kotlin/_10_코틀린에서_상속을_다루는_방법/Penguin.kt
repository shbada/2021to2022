package _10_코틀린에서_상속을_다루는_방법

class Penguin(
    species: String
) : Animal(species, 2), Swimable, Flyable {
    private val wingCount: Int = 2

    override fun move() {
        println("펭귄이 움직입니다~ 꿱꿱")
    }

    // Animal 클래스에 legCount 는 기본적으로 final이다. open을 붙혀줘야한다.
    override val legCount: Int
        get() = super.legCount + this.wingCount

    override val swimAbility: Int
        get() = 3

    override fun act() {
        // 메서드명 동일한 경우 - super<타입>.메서드명()
        super<Swimable>.act()
        super<Flyable>.act()
    }
}