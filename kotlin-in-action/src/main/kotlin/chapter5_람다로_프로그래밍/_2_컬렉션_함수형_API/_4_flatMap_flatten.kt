package chapter5_람다로_프로그래밍._2_컬렉션_함수형_API

/*
flatMap : 먼저 인자로 주어진 람다를 컬렉션의 모든 객체에 적용하고, 람다를 적용한 결과, 얻어지는 여러 리스트를 한 리스트로 모은다.
 */
class Book(val title: String, val authors: List<String>)

fun main() {
    /*
               "abc" "def"
    "a", "b", "c"     "d", "e", "f"

      "a", "b", "c", "d", "e", "f"
     */
    val strings = listOf("abc", "def")
    println(strings.flatMap { it.toList() })
    // 1) toList() 적용 : 그 문자열에 속한 모든 문자로 이뤄진 리스트가 만들어진다.
    // 2) 리스트의 리스트에 들어있던 모든 원소를 단일 리스트로 반환한다.

    val books = listOf(Book("Thursday Next", listOf("Jasper Fforde")),
        Book("Mort", listOf("Terry Pratchett")),
        Book("Good Omens", listOf("Terry Pratchett", "Neil Gaiman")))

    books.flatMap { it.authors }.toSet(); // 책의 저자를 모두 모은 집합 사용
    // 1) flatMap : 모든 책의 작가를 평평한(문자열만으로 이뤄진) 리스트 하나로 모은다.
    // 2) toSet : 리스트에서 중복을 없애고 집합을 만든다.

    // 특별히 변환해야할 내용이 없다면 리스트의 리스트를 평평하게 펼치기만 하면 되므로,
    // listOfLists.flatten() 을 사용할 수 있다.
}
