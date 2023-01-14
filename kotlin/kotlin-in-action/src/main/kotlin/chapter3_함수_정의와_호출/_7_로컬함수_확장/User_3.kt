package chapter3_함수_정의와_호출._7_로컬함수_확장

/**
 로컬함수에서 바깥 함수의 파라미터 접근하기
 데이터베이스에 사용자 객체를 저장하기전 각 필드를 검증하는 로직
 */
class User_3(val id: Int, val name: String, val address: String)

fun saveUser3(user:User) {
    fun validate(value: String,
                 fieldName: String) {
        if (value.isEmpty()) {
            throw IllegalArgumentException (
                // 바깥 함수의 user 파라미터에 직접 접근할 수 있다.
                "Can't save user ${user.id}: empty $fieldName"
            )
        }
    }

    validate(user.name, "Name")
    validate(user.address, "Address")
}