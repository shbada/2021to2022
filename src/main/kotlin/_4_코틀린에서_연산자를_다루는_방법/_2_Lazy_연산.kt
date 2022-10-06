package _4_코틀린에서_연산자를_다루는_방법
fun main() {
    if (fun1() || fun2()) { // fun1()이 true면 뒤에 fun2() 실행 하지 않고 true
        println("본문")
    }

    if (fun2() && fun1()) { // fun2()가 false므로 뒤에 fun1()을 실행하지 않는다.
        println("본문")
    }
}

fun fun1(): Boolean {
    println("fun 1")
    return true
}

fun fun2(): Boolean {
    println("fun 2")
    return false
}


