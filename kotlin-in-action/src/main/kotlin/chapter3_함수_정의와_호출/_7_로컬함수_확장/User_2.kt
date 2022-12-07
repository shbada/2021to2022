package chapter3_함수_정의와_호출._7_로컬함수_확장

/**
 코드 중복 줄이기
 데이터베이스에 사용자 객체를 저장하기전 각 필드를 검증하는 로직
 */
class User_2(val id: Int, val name: String, val address: String)

fun saveUser2(user:User) {
    // 한 필드를 검증하는 로컬 함수를 정의한다.
    fun validate(user: User,
                 value: String,
                 fieldName: String) {
        if (value.isEmpty()) {
            throw IllegalArgumentException (
                "Can't save user ${user.id}: empty $fieldName"
            )
        }
    }

    // 로컬함수를 호출해서 각 필드를 검증한다.
    /* 아쉬운점 : 로컬함수에게 User 객체를 하나하나 전달해야한다. */
    validate(user, user.name, "Name")
    validate(user, user.address, "Address")
}