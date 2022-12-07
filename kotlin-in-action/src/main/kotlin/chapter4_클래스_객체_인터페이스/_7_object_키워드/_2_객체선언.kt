package chapter4_클래스_객체_인터페이스._7_object_키워드

import java.io.File

// 객체 선언 : 싱글턴을 쉽게 만들기
// 코틀린은 객체 선언 기능을 통해 싱글턴을 기본 지원한다. 객체 선언은 클래스 선언과 그 클래스에 속한 단일 인스턴스의 선언을 합친 선언이다.
// 인스턴스가 하나만 필요한 클래스 = 싱글턴

object Payroll { // object 키워드로 시작
    // 객체 선언은 클래스를 선언하고 그 클래스의 인스턴스를 만들어서 변수에 저장하는 모든 작업을 한문장으로 처리한다.
    // 생성자를 객체 선언에 쓸 수 없다.
    // 인스턴스와 달리 싱글턴 객체는 객체 선언문이 있는 위치에서 생성자 호출 없이 즉시 만들어진다.
    val allEmployees = arrayListOf<Person>()
    fun calculateSalary() {
        for (person in allEmployees) {

        }
    }
}

fun main() {
    // 객체 선언에 사용한 이름 뒤에 마침표(.)를 붙이면 객체에 속한 메서드나 프로퍼티에 접근할 수 있다.
//    Payroll.allEmployees.add(Person())
    Payroll.calculateSalary()

    val persons = listOf(Person("bob"), Person("seohae"))
    println(persons.sortedWith(Person.NameComparator))
}

/*
객체 선언도 클래스나 인터페이스를 상속할 수 있다.
프레임워크를 사용하기 위해 특정 인터페이스를 구현해야하는데, 그 구현 내부에 다른 상태가 필요하지 않은 경우에 이런 기능이 유용하다.
java.util.Comparator 구현은 두 객체를 인자로 받아 그 중 어느 객체가 더 큰지 알려주는 정수를 반환한다.
Comparator 안에는 데이터를 저장할 필요가 없다.
따라서 어떤 클래스에 속한 객체를 비교할때 사용하는 Comparaotr는 보통 클래스마다 단 하나씩만 있으면 된다.
따라서 Comparator 인스턴스를 만드는 방법으로는 객체 선언이 가장 좋은 방법이다.
 */
object CaseInsensitiveFileComparator : Comparator<File> {
    override fun compare(file1: File, file2: File): Int {
        return file1.path.compareTo(file2.path, ignoreCase = true)
    }
}

/*
클래스 안에서 객체를 선언할 수도 있다.
그런 객체도 인스턴스는 단 하나뿐이다.
 */

data class Person(val name: String) {
    object NameComparator : Comparator<Person> {
        override fun compare(p1: Person, p2: Person): Int =
            p1.name.compareTo(p2.name)
    }
}