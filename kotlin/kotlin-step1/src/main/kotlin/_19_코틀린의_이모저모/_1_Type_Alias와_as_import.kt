package _19_코틀린의_이모저모

import _17_코틀린에서_람다를_다루는_방법.Fruit
//import _17_코틀린에서_람다를_다루는_방법.Fruit as FruitA

/* typealias */
typealias FruitFilter = (Fruit) -> Boolean
// 긴 이름의 클래스 혹은 함수 타입이 있을때 축약하거나 더 좋은 이름을 쓰고싶다.

// filter: (Fruit) -> Boolean 가 너무 길다.
fun fliterFruits(fruits: List<Fruit>, filter: FruitFilter) {

}

data class UltraSuperGuardianTribe(
    val name: String
)
typealias USGTMap = Map<String, UltraSuperGuardianTribe> // 간단히 줄일 수 있다.

/* as_import */
// 다른 패키지의 같은 이름 함수를 동시에 가져오고 싶다면?
// as import : 어떤 클래스나 함수를 임포트할때 이름을 바꾸는 기능
// import 패키지 경로 as A 이런식으로 명칭을 줘서 사용 가능하다.
fun main() {
//    FruitA
}