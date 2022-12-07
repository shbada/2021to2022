package _14_코틀린에서_다양한_클래스를_다루는_방법

fun main() {
    val person1 = PersonDto("Kim", 11)
    val person2 = PersonDto("Kim", 15)

    println(person1 == person2)
}

// 계층간의 데이터를 전달하기 위한 DTO (Data Transfer Object)
data class PersonDto ( // data 키워드 : equals, hashCode, toString 자동으로 만들어준다.
    // name arguments 사용하면 builder 와 같은 효과도 얻을 수 있다.
    val name:String,
    val age: Int
    ) {

}