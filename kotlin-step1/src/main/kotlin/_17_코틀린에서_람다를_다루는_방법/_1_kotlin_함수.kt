package _17_코틀린에서_람다를_다루는_방법

// 코틀린에서는 함수가 그 자체로 값이 될 수 있다. 변수에 할당될수도, 파라미터로 넘길수도 있다.
fun main() {
    val fruits = listOf(
        Fruit("사과", 1000),
        Fruit("사과", 2000),
        Fruit("사과", 3000)
    )

    val isApple = fun(fruit: Fruit): Boolean {
        return fruit.name == "사과"
    }

    // (Fruit) -> (Boolean) : (파라미터 타입) -> (리턴 타입)
    val isApple3: (Fruit) -> (Boolean) = fun(fruit: Fruit): Boolean {
        return fruit.name == "사과"
    }

    val isApple2 = { fruit: Fruit -> fruit.name == "사과"}
    isApple2(fruits[0])
    isApple.invoke(fruits[0])

    // 함수 자체를 파라미터로 담는다. filterFruits()
    filterFruits(fruits, isApple)
    filterFruits(fruits, isApple2)
    filterFruits(fruits, isApple3)
    filterFruits(fruits, { fruit: Fruit -> fruit.name == "사과"})
    // filter (함수) 가 마지막에 있으면 ?
    filterFruits(fruits) { fruit: Fruit -> fruit.name == "사과" }
    filterFruits(fruits) { fruit -> fruit.name == "사과" } // Fruit 생략 가능
    filterFruits(fruits) { it.name == "사과" } // 파라미터가 1개인 경우 it 가능
    filterFruits(fruits) {
        println("aa")
        // 람다는 여러줄 작성이 가능한데, 마지막 줄의 결과가 람다의 반환값이다.
        it.name == "사과"
    } // 파라미터가 1개인 경우 it 가능
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

class Fruit(val name: String, val price: Int) {

}

