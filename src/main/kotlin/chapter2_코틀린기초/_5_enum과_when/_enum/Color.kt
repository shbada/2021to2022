package chapter2_코틀린기초._5_enum과_when._enum

/*
   enum : 소프트 키워드(soft keyword)라고 부른다.
   enum은 class 앞에 있을때는 특별한 의미를 지니지만 다른 곳에서는 이름에 사용할 수 있다.
   반면 class 는 키워드다. 따라서 class 라는 이름을 사용하려면 clazz나 aClass와 같이 사용해야한다.
*/

enum class Color {
    RED, ORANGE, YELLOW, GREEN, BLUE, INDIGO, VIOLET;

    // enum 을 import 하여 수식자없이 사용할 수 있다.
    fun getWarmth(color: Color) =
        when (color) {
            RED, ORANGE, YELLOW -> "warm"
            GREEN -> "natural"
            BLUE, INDIGO, VIOLET -> "cold"
        }
}