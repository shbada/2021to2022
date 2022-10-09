package _18_코틀린에서_컬렉션을_함수로_다루는_방법

fun main() {
    // filter
//    val apples = fruits.filter {fruit -> fruit.name == "사과"}

    // filter 에 인덱스가 필요하다면?
    // filterIndexed()

    // map : 사과의 가격들을 알려주세요.
//    val applePrices = fruits.filter { fruit -> fruit.name == "사과"}
//        .map()

    // 맵에서 인덱스가 필요하다면?
    // mapIndexed()

    // Mapping의 결과가 null이 아닌걸 가져오고싶다면?
    // mapNotNull

}

// filter
private fun filterFruits(fruits: List<Fruit>, filter: (Fruit) -> Boolean): List<Fruit> {
//    val results = mutableListOf<Fruit>()
//
//    for (fruit in fruits) {
//        if (filter(fruit)) {
//            results.add(fruit)
//        }
//    }
//
//    return results
    return fruits.filter(filter)
}

class Fruit(val name: String, val price: Int) {

}