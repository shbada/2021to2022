package _13_코틀린에서_중첩클래스를_다루는_방법

/*
Static을 사용하는 중첩 클래스
- 밖의 클래스를 직접 참조 불가하다.

Static을 사용하지 않는 중첩 클래스
- 내부 클래스 : 밖의 클래스를 직접 참조 가능
- 지역 클래스 : 메서드 내부에 클래스를 정의
- 익명 클래스 : 일회성 클래스

1) 내부 클래스는 숨겨진 외부 클래스 정보를 가지고있어, 참조를 해지하지 못하는 경우 메모리 누수가 생길 수 있고, 이를 디버깅하기 어렵다.
2) 내부 클래스의 직렬화 형태가 명확하게 정의되지 않아 직렬화할때 한계가 있다.

클래스 안에 클래스를 만들때는 static 클래스를 사용하라
Kotlin은 이러한 Guide를 충실히 따르고있다.

-- 정리
[JAVA]
1) 클래스 안의 static 클래스 : 바깥 클래스 참조 없음 (권장)
2) 클래스 안의 클래스 : 바깥 클래스 참조 있음

[Kotlin]
1) 클래스 안의 클래스 : 바깥 클래스 참조 없음 (권장)
2) 클래스 안의 inner 클래스 : 바깥 클래스 참조 있음
*/

// 기본적으로 바깥 클래스를 참조하지 않은 내부 클래스
class JavaHouse(
    private val adress: String,
    private val livingRoom: LivingRoom
) {
    class LivingRoom( // 기본적으로 바깥 클래스에 대한 연결이 없는 중첩 클래스
        private var area: Double
    )
}

// 권장되지 않은 클래스 안의 클래스 (내부 클래스) - 바깥 클래스를 참조하고 싶다면 inner 키워드 추가
class JavaHouse2(
    private val address: String,
    private val livingRoom: LivingRoom
) {
    inner class LivingRoom( // 기본적으로 바깥 클래스에 대한 연결이 없는 중첩 클래스
        private var area: Double
    ) {
        val address: String
            get() = this@JavaHouse2.address
    }
}