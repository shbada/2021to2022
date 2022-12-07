package _16_코틀린에서_다양한_함수를_다루는_방법

// 함수 안에 함수를 선언할 수 있다.
//fun createPerson(firstName: String, lastName: String): Person {
//    // depth가 깊어지고 코드가 간결하지는 않다.
//    fun validateName(name: String, fieldName: String) {
//        if (name.isEmpty()) {
//            TODO()
//        }
//    }
//
//    validateName(firstName, "firstName")
//    validateName(lastName, "lastName")
//
//    return Person(firstName, lastName, 1)
//}