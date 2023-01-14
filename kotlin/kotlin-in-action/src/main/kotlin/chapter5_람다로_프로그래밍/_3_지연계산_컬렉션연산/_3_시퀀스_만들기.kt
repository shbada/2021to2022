package chapter5_람다로_프로그래밍._3_지연계산_컬렉션연산

import java.io.File

/*
asSequence() 를 호출하여 시퀀스를 만들었다.
시퀀스를 만드는 다른 방법으로 generateSequence 함수를 사용할 수 있다.
이 함수는 이전의 원소를 인자로 받아 다음 원소를 계산한다.
 */

fun main() {
    // 0부터 100까지 자연수의 합 구하기
    /*
        numbers, numbersTo100 모두 시퀀스며 연산을 지연 계산한다.
        최종 연산을 수행하기 전까지는 시퀀스의 각 숫자는 계산되지 않는다.
     */
    val numbers = generateSequence(0) { it + 1 }
    val numbersTo100 = numbers.takeWhile { it <= 100 }
    println(numbersTo100.sum()) // 모든 연산은 "sum()"이 호출될 때 수행된다. (최종 연산)

    // 어떤 파일의 상위 디렉터리를 탐색하면서 숨김(Hidden) 속성을 가진 디렉토리가 있는지 검사함으로써
    // 해당 파일이 숨김 디렉토리 안에 들어있는지 찾아내는 예제
    val file = File("/Users/.HiddenDir/a.txt")
    println(file.isInsideHiddenDirectory())
}

// 조건을 만족하는 원소를 찾은 뒤에는 더이상 찾지 않는다.

fun File.isInsideHiddenDirectory() = generateSequence(this) { it.parentFile }.any { it.isHidden } // 최종 연산