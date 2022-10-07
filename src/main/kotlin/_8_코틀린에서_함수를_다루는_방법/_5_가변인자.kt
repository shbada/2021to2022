package _8_코틀린에서_함수를_다루는_방법

fun main() {
    printAll("A", "B", "C") // ,로 구분

    val array = arrayOf("A", "B", "C") // 배열
    printAll(*array) // 가변인자 넣어줄때 배열을 바로 넣는 대신, *를 사용해야한다.
    // spread 연산자, 배열을 , 쓰는것처럼 꺼낸다.
}

fun printAll(vararg strArr: String) { // JAVA의 ...을 쓰 는대신 vararg
    for (str in strArr) {
        println(str)
    }
}