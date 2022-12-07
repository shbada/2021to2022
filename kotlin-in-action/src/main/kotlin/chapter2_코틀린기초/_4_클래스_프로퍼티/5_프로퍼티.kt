package chapter2_코틀린기초._4_클래스_프로퍼티

// default : public
class `5_프로퍼티` (
    /*
      읽기 전용 프로퍼티
      - 비공개 필드와 필드를 읽는 단순한 공개 getter 를 만든다.
     */
    val name: String,

    /*
      쓸 수 있는 프로퍼티
      - 비공개 필드와 필드를 읽는 공개 getter, 쓰는 공개 setter 를 만든다.
     */
    var isMarried: Boolean,
)