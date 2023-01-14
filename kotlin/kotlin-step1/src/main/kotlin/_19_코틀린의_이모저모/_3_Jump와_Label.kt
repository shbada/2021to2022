package _19_코틀린의_이모저모

/*
return : 기본적으로 가장 가까운 enclosing function 또는 익명함수로 값이 반환
break : 가장 가까운 루프가 제거된다.
continue : 가장 가까운 루프를 다음 step으로 보낸다.

// for, while문에서 break, continue는 JAVA와 완전히 동일하다.
 */

fun main() {
    val numbers = listOf(1, 2, 3)
    numbers.map { number -> number + 1 }
        .forEach { number -> println(number) } // continue, break를 쓸수 없다.

    // foreach + break 쓰고 싶다면?
    // 이렇게 쓰기보다는 가급적 for 문 사용을 추천한다.
    run {
        numbers.forEach { number ->
            if (number == 3) {
                // @ : Label
                // 특정 Expression에 라벨이름@을 붙여 하나의 레벨로 간주하고 break, continue, return을 사용하는것
//                return@run // break (run을 가르키는것)
//                return@forEach // continue (forEach를 가르키는것)
            }
            println(number)
        }
    }

    // 라벨을 사용한 Jump는 사용하지 않는 것을 강력 추천한다. 유지보수가 어렵다.
    loop@ for (i in 1..100) {
        for (j in 1..100) {
            if (j ==2) {
                break@loop // 가장 가까운 loop@ 에 대한 것- 그러므로 상위 for(i)를 break 한다
            }
            println("$i $j")
        }
    }

//    for (number in numbers) {
//        println(number)
//    }
}