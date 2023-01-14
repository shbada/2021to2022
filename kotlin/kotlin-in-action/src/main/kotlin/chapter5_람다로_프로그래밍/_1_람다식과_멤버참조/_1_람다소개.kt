package chapter5_람다로_프로그래밍._1_람다식과_멤버참조

/*
코드 블록을 함수 인자로 넘기기

함수형 프로그래밍에서는 클래스를 선언하고 그 클래스의 인스턴스를 함수에 넘기는 대신, 함수를 직접 다른 함수에 전달할 수 있다.
람다 식을 사용하면 코드가 더욱 더 간결해진다.
람다 식을 사용하면 함수를 선언할 필요가 없고 코드 블록을 직접 함수의 인자로 전달할 수 있다.
 */

// 예제 : 버튼 클릭에 따른 동작 정의하기
// 클릭 이벤트를 처리하는 리스너를 추가한다. 이 리스너는 onClick 이라는 메서드가 들어있는 onClickListener를 구현해야한다.

fun main(args: Array<String>) {
    /* 자바 - 무명 내부 클래스로 리스너 구현 */
//    button.setOnClickListener(new OnClickListener() {
//        @Override
//        public void onClick(View view) {
//            클릭 시 수행할 동작
//        }
//    });
//
//    /* 코틀린에서 람다로 리스너 구현하기 */
//    button.setOnClickListener {  클릭 시 수행할 동작  }
}