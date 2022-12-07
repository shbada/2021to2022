package chapter3_함수_정의와_호출._4_함수를_호출하기_쉽게만들기

/*
함수와 마찬가지로 프로퍼티도 파일의 최상위 수준에 놓을 수 있다.
어떤 데이터를 클래스 밖에 위치시켜야 하는 경우는 흔하지 않지만, 가끔 유용할때가 있다.
 */
var opCount = 0 // 최상위 프로퍼티 선언
const val constOpCount = 0 // 최상위 프로퍼티 선언  (=public static final)
// java = public static final String constOpCount = 0

fun performOperation() {
    opCount++ // 최상위 프로퍼티의 값을 변경한다.
}

fun reportOperationCount() {
    println("result $opCount") // 최상위 프로퍼티의 값을 읽는다.
}