package chapter2_코틀린기초._5_enum과_when._enum

/*
   enum : 소프트 키워드(soft keyword)라고 부른다.
   enum은 class 앞에 있을때는 특별한 의미를 지니지만 다른 곳에서는 이름에 사용할 수 있다.
   반면 class 는 키워드다. 따라서 class 라는 이름을 사용하려면 clazz나 aClass와 같이 사용해야한다.
*/

enum class Color2 (
    val r: Int, val g: Int, val b: Int // 상수의 프로퍼티를 정의한다.
) {
    RED(255, 0, 0), ORANGE(255, 165, 0), // 각 상수를 생성할때 그에 대한 프로퍼티 값을 지정한다.
    YELLOW(255, 255, 0), GREEN(0, 255, 0), BLUE(0, 0, 255),
    INDIGO(75, 0, 130), VIOLET(238, 130, 238); // ; 을 여기서 꼭 사용해야한다.

    // enum 클래스 안에서 함수를 정의한다.
    fun rgb() = (r * 256 + g) * 256 + b

    // 함수의 반환값으로 when 식을 직접 사용한다.
    fun getMnemonic(color: Color2) =
        when (color) { // 색이 특정 enum 상수와 같을때 그 상수에 대응하는 문자열을 돌려준다.
            // break 문을 쓰지 않아도 된다.
            RED -> "Richard"
            ORANGE -> "Of"
            YELLOW -> "York"
            GREEN -> "Gave"
            BLUE -> "Battle"
            INDIGO -> "In"
            VIOLET -> "Vain"
        }

    // 한 when 분기 안에 여러 값을 사용할 수 있다.
    fun getWarmth(color: Color2) =
        when (color) {
            RED, ORANGE, YELLOW -> "warm"
            GREEN -> "natural"
            BLUE, INDIGO, VIOLET -> "cold"
        }

    /*
       setOf 함수 : 인자로 전달받은 여러 객체를 그 객체들을 포함하는 집합인 Set 객체로 만드는 코틀린 표준 라이브러리
       - 집합(set)은 원소가 모여있는 컬렉션으로, 각 원소의 순서는 중요하지 않다.
       - SetOf(c1, c2) 와 setOf(RED, YELLOW) 가 같다는 말은 c1이 RED고, c2가 YELLOW 또는 c1이 YELLOW, c2 RED인 경우다.
     */
    fun mix(c1 : Color2, c2: Color2) {
        // when 식의 인자를 아무 객체나 사용할 수 있다.
        // when 은 이렇게 인자로 받은 객체가 각 분기 조건에 있는 객체와 같은지 테스트한다.
        when (setOf(c1, c2)) {
            setOf(RED, YELLOW) -> ORANGE
            setOf(YELLOW, BLUE) -> GREEN
            setOf(BLUE, VIOLET) -> INDIGO
            else -> throw Exception("Dirty color") // 매치되는 분기 조건이 없으면 이 문장을 실행한다.
        }
    }

    // 인자없는 when 사용
    fun mixOptimized(c1 : Color2, c2 : Color2) =
        // when에 아무 인자도 없으려면 각 분기의 조건이 boolean 결과를 계산하는 식이여야한다.
        when {
            (c1 == RED && c2 == YELLOW) ||
                    (c1 == YELLOW && c2 == RED) -> ORANGE
            (c1 == YELLOW && c2 == BLUE) ||
                    (c1 == BLUE && c2 == YELLOW) -> GREEN
            (c1 == BLUE && c2 == VIOLET) ||
                    (c1 == VIOLET && c2 == BLUE) -> INDIGO
            else -> throw Exception("Dirty color") // 매치되는 분기 조건이 없으면 이 문장을 실행한다.
        }
}