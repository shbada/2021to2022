package _2_코틀린에서_null을_다루는_방법

fun main() {
    val person = Person("공부하는 개발자")
    // JAVA
    // 오류 : @Nullable // null 이 될 수 있다.
    // 허용 : @NotNull // null 이 될 수 없다.
    // JAVA 코드에 위 어노테이션이 없다면 코틀린에서는 null 정보를 알수가 없다.
    // 위 어노테이션이 아무것도 없다면 일단 진행되고, 실제로 null이 들어온다면 NullPointerException 이 발생한다.
    startsWithA(person.name)
}

// str은 무조건 null이 아니다.
fun startsWithA(str: String): Boolean {
    return str.startsWith("A")
}