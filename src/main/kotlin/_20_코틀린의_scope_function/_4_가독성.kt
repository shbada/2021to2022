package _20_코틀린의_scope_function

fun main() {
    // 1번 코드
//    if (person != null && person.isAdult) {
//        view.showPerson(person)
//    } else {
//        view.showError()
//    }

    // 2번 코드 ( 3번코드보다 1번코드가 더 이해하기 쉽다.)
//    person?.takeIf { it.isAdult } // null 이 아니고 isAdult() true 라면
//        ?.let { view::showPerson } // let 구문 호출되고 (여기서 만약 null을 반환한다면 아래 showError()가 호출됨)
//        ?: view.showError() // 그렇지 않으면 여기 구문이 호출
}