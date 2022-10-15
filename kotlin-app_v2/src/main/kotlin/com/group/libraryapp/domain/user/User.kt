package com.group.libraryapp.domain.user

import com.group.libraryapp.domain.book.Book
import com.group.libraryapp.domain.user.loanHistory.UserLoanHistory
import javax.persistence.*

/**
 * [1. setter public]
 * setter 사용은 불편하다. setter 는 pirvate 하게 만들어야한다.
 * 1) backing property (Calculator.java 에서 _name)
 * 2) custome setter 이용하기
 * var name = name
    private set
 * ---------------
 * 위 두 방법 모두 프로퍼티가 많아지면 번거롭다.
 * 그래서 개인적으로는 setter 을 열어두지만 사용하지 않는 것으로 약속하고 가는 방법을 추천한다.
 *
 * [2. 생성자 안의 프로퍼티, 클래스 body 안의 프로퍼티]
 * 1) 모든 프로퍼티를 생성자에 넣거나
 * 2) 프로퍼티를 생성자 혹은 클래스 body 안에 구분해서 넣을때 명확한 기준이 있거나
 *
 * [3. JPA와 data class]
 * 1) JPA Entity의 경우는 data class로 만들지 말자.
 * - equals, toString 등 자동 생성되는 메서드들이 Entity와 맞지 않다.
 * - User - UserLoanHistory 의 서로 equals()를 계속해서 호출하기 때문
 *
 * [ 작은 Tip ]
 * Entity가 생성되는 로직을 찾고싶다면 constructor 지시어를 명시적으로 작성하고 찾을 수 있다.
 * (생성자 호출 코드를 찾을 수 있다.)
 */
@Entity
class User constructor( // getter 는 들어있다.
    var name: String,
    val age: Int?,

    @OneToMany(mappedBy = "user", cascade = [CascadeType.ALL], orphanRemoval = true)
    val userLoanHistoryList: MutableList<UserLoanHistory> = mutableListOf(), // 가변 리스트로 설정

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
) {
    init {
        if (name.isBlank()) {
            throw IllegalArgumentException("이름은 비어있을 수 없습니다")
        }
    }

    /*
       setter 사용도 가능하지만, 좋은 이름의 함수를 사용하는것이 훨씬 clean 하다.
       setter 사용은 불편하다.
       setter 는 pirvate 하게 만들어야한다.
     */
    fun updateName(name: String) {
        this.name = name
    }

    fun loadBook(book: Book) {
        this.userLoanHistoryList.add(UserLoanHistory(this, book.name))
    }

    fun returnBook(bookName: String) {
        this.userLoanHistoryList.first {
            history -> history.bookName == bookName // 같은 책 중 첫번째 원소를 찾는다.
        }.doReturn()
    }
}