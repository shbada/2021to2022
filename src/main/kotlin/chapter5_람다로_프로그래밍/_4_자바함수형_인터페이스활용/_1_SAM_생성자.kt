package chapter5_람다로_프로그래밍._4_자바함수형_인터페이스활용

fun createAllDoneRunnable() : Runnable {
    return Runnable { println("All done!") }
}

fun main() {
    createAllDoneRunnable().run()
}