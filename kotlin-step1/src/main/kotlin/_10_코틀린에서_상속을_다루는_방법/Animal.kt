package _10_코틀린에서_상속을_다루는_방법

abstract class Animal (
    protected val speciesL: String,

    // 코틀린은 기본적으로 final이다. open을 붙혀줘야한다.
    protected open val legCount: Int, ) {
    abstract fun move()
}
