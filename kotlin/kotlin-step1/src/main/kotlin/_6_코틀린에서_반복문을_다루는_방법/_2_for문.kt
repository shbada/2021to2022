package _6_코틀린에서_반복문을_다루는_방법

/*
동작원리

.. 연산자 : 범위를 만들어내는 연산자
1..3 : 1부터 3의 범위

IntProgression(등차수열) <- IntRange
IntRange가 IntProgression를 상속받는다.
step의 기본값은 1이라서 1씩 증가되는 것
"1..3 : 1에서 시작하고 3으로 끝나는 등차수열을 만들어줘" 라는 뜻이다.

시작값 : 1
종료값 : 3
공차 : 1

downTo 1 : 공차 -1
step2 : 공차 2

downTo, step이 함수다.
(중위 호출 함수 : 함수를 호출하는 방법을 다르게 해줌)
변수.함수이름(argument) 대신 '변수 함수이름 argument' 로 쓸수 있게 해주는것

1..5 step 2
1부터 5까지의 등차수열 생성
step2 : 등차수열에 대한 함수를 호출, 등차수열.step(2)
결론적으로 1, 3, 5가 나온다.

Kotlin에서 전통적인 for문은 등차수열을 사용한다.
 */
fun main() {
    for (i in 1..3) {
        println(i)
    }

    // 3부터 1까지 내려간다.
    for (i in 3 downTo 1) {
        println(i)
    }

    // 2칸씩 올라간다.
    for (i in 1..5 step 2) {
        println(i)
    }
}