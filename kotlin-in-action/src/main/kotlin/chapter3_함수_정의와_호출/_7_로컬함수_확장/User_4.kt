package chapter3_함수_정의와_호출._7_로컬함수_확장

/**
 검증 로직을 확장 함수로 추출하기
 데이터베이스에 사용자 객체를 저장하기전 각 필드를 검증하는 로직

 함수 중첩은 한단계만 권장한다. (중첩된 함수의 깊이가 깊어지면 코드를 읽기가 어려워진다.)
 */
class User_4(val id: Int, val name: String, val address: String)

fun User.validateBeforeSave() {
    fun validate(value: String, fieldName: String) {
        if (value.isEmpty()) {
            throw IllegalArgumentException (
                // User의 프로퍼티를 직접 사용할 수 있다.
                "Can't save user ${id}: empty $fieldName"
            )
        }
    }

    validate(name,"Name")
    validate(address,"Address")
}

fun saveUser4(user: User) {
    // 확장함수를 호출한다.
    user.validateBeforeSave()
}