package _17_코틀린에서_람다를_다루는_방법

fun main() {
    // JAVA에서 람다를 쓸때 사용할 수 있는 변수에 제약이 있다.
    // final 인 변수(실질적 final)만 사용 가능하다. (람다 밖의 변수)

    // 코틀린에서는 아무런 문제 없이 동작한다.
    /*
        코틀린에서는 람다가 시작하는 지점에 참조하고 있는 변수들을 모두 포획해서 정보를 가지고있다.
        함수가 불려지는 시점에 존재하는 변수를 모두 포획한다.
        이 데이터 구조를 Closure 라고 부른다.
     */
    var a = "aa"
    a = "bb"

    val fruits = listOf(
        Fruit("사과", 1000),
        Fruit("사과", 2000),
        Fruit("사과", 3000)
    )

    filterFruits(fruits) { it.name == a }
}

// 함수 자체를 파라미터로 담는다.
// filter (함수) 가 마지막에 있으면 ?
private fun filterFruits(fruits: List<Fruit>, filter: (Fruit) -> Boolean): List<Fruit> {
    val results = mutableListOf<Fruit>()

    for (fruit in fruits) {
        if (filter(fruit)) {
            results.add(fruit)
        }
    }

    return results
}