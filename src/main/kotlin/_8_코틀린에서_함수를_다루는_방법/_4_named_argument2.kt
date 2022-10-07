package _8_코틀린에서_함수를_다루는_방법

// 참고 : JAVA 코드의 메서드를 호출할때는 named argument를 쓸수 없다.
// JAVA는 JVM에서 컴파일할때 매개변수 이름을 가지고있지않기 때문이다.
fun main() {
    // 이름 명시하여 builder 비슷하게 사용 가능
    printNameAndGender2(name = "KIM", gender = "F")
}

fun printNameAndGender2(name: String, gender: String) {
    println(name)
    println(gender)
}