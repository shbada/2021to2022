package chapter3_함수_정의와_호출._7_로컬함수_확장

/**
 코드 중복을 보여주는 예제
 데이터베이스에 사용자 객체를 저장하기전 각 필드를 검증하는 로직
 */
class User(val id: Int, val name: String, val address: String)

fun saveUser(user: User) {
    if (user.name.isEmpty()) {
        throw IllegalArgumentException (
            "Can't save User ${user.id}: empty Name"
        )
    }

    if (user.address.isEmpty()) {
        throw IllegalArgumentException (
            "Can't save User ${user.id}: empty Address"
        )
    }
}

fun main() {
    saveUser(User(1, "", ""))
}