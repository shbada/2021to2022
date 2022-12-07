package chapter2_코틀린기초._5_enum과_when._클래스계층

// 식을 위한 Expr 인터페이스. Sum, Num 클래스는 Expr 인터페이스를 구현한다.
// Expr은 아무 메서드도 선언하지 않으며, 단지 여러 타입의 식 객체를 아우르는 공통 타입 역할만 수행한다.
interface Expr

class Num(val value: Int) : Expr
// Expr 타입의 객체라면 어떤 것이나 Sum 연산의 인자가 될 수 있다. 따라서 Num 이나 다른 Sum이 인자로 올수있다.
class Sum(val left: Expr, val right: Expr) : Expr

fun main() {
    println(eval(Sum(Sum(Num(1), Num(2)), Num(4))))
}

fun eval(e: Expr): Int {
    if (e is Num) {
        // e 의 타입을 컴파일러는 Num 으로 해석한다.
        val n = e as Num // 여기서 Num 으로 타입을 변환하는데 이는 불필요한 중복이다.
        return n.value
    }

    /*
       is 를 사용하여 변수 타입을 검사한다. (=java의 instanceOf 와 비슷하다.)
       코틀린에서는 프로그래머 대신 컴파일러가 캐스팅을 해준다.
       어떤 변수가 원하는 타입인지 일단 is로 검사하고 나면 굳이 변수를 원하는 타입으로 캐스팅하지 않아도,
       마치 처음부터 그 변수가 원하는 타입으로 선언된 것처럼 사용할 수 있다.
       = 스마트 캐스트 (smart cast)
     */
    if (e is Sum) {
        // e 의 타입을 컴파일러는 Sum 으로 해석한다.
        return eval(e.right) + eval(e.left) // 변수 e에 대해 스마트 캐스트를 사용한다.
    }

    throw IllegalArgumentException("Unknown expression")
}

fun evalWhen(e: Expr) : Int =
    when(e) {
        is Num -> e.value
        is Sum -> evalWhen(e.right) + evalWhen(e.left)
        else -> throw IllegalArgumentException("Unknown expression")
    }


// 식이 본문인 함수 : 블록을 본문으로 가질 수 없고
// 블록이 본문인 함수 : return 문이 반드시 존재해야한다.
fun evalWithLogging(e: Expr): Int =
    when (e) {
        is Num -> {
            println("num: ${e.value}")
            e.value // 반환값 (블록의 마지막 식이 블록의 결과 라는 규칙은 블록이 값을 만들어내야하는 경우 항상 성립)
        }

        is Sum -> {
            val left = evalWithLogging(e.left)
            val right = evalWithLogging(e.right)

            println("sum  $left + $right")
            left + right // 반환값
        }

        else -> throw IllegalArgumentException("Unknown expression")
    }