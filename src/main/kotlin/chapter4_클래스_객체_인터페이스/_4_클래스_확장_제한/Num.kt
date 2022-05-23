package chapter4_클래스_객체_인터페이스._4_클래스_확장_제한

import chapter2_코틀린기초._5_enum과_when._클래스계층.Expr

/*
상위 클래스 Expr
- 숫자를 표현하는 Num
- 덧셈 연산을 표현하는 Sum
위 2개의 하위 클래스가 있다.

when 식에서 이 모든 하위클래스를 처리하면 편리하다.
 */
interface Expr

class Num(val value: Int) : Expr
class Sum(val left: Expr, val right: Expr) : Expr

fun eval(e: Expr) : Int =
    /*
    when을 사용해서 Expr 타입의 값을 검사할때 꼭 디폴트 분기인 else 분기를 작성해야한다.

    문제점
    1. 항상 디폴트 분기를 추가하는게 편리하지 않다.
    2. 디폴트 분기가 있으면 이런 클래스 계층에 새로운 하위 클래스를 추가하더라도 컴파일러가 when이 모든 경우를 처리하는지 제대로 검사할 수가 없다.
    -> 실수로 새로운 클래스 처리를 잊어버렸더라도 디폴트 분기가 선택되기 때문에 심각한 버그가 발생할 수 있다.

    코틀린은 sealed 변경자를 제공한다.
    상위 클래스에 sealed 변경자를 붙이면 그 상위 클래스를 상속한 하위 클래스 정의를 제한할 수 있다.
    sealed 클래스의 하위 클래스를 정의할때는 반드시 상위 클래스 안에 중첩시켜야한다.
     */
    when (e) {
        is Num -> e.value
        is Sum -> eval(e.right) + eval(e.left)
        else -> // else 분기가 반드시 있어야한다.
            throw IllegalArgumentException("Unknown expression")
    }