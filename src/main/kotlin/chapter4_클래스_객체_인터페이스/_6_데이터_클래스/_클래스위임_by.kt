package chapter4_클래스_객체_인터페이스._6_데이터_클래스

/*
대규모 객체지향 시스템을 설계할때 시스템을 취약하게 만드는 문제는 보통 구현 상속에 의해 발생한다.
하위 클래스가 상위 클래스의 메서드 중 일부를 오버라이드하면 하위 클래스는 상위 클래스의 세부 구현 사항에 의존하게 된다.
시스템이 변함에 따라 상위 클래스의 구현이 바뀌거나 상위 클래스에 새로운 메서드가 추가된다.
이 과정에서 하위 클래스가 상위 클래스에 대해 갖고있던 가정이 깨져서 코드가 정상적으로 작동하지 못하는 경우가 생길 수 있다.

코틀린을 사용하면서 이런 문제를 인식하고 기본적으로 클래스를 final로 취급하기로 결정했다.
모든 클래스를 기본적으로 final로 취급하면 상속을 염두에 두고 open 변경자로 열어둔 클래스만 확장할 수 있다.
열린 상위 클래스의 소스코드를 변경할때는 open 변경자를 보고 해당 클래스를 다른 클래스가 상속하리라 예상할 수 있으므로,
변경시 하위 클래스를 깨지 않기 위해 좀더 조심할 수 있다.

하지만 상속을 허용하지 않는 클래스에 새로운 동작을 추가해야할 때가 있다.
이럴때 사용하는 일반적인 방법이 '데코레이터 패턴'이다.

상속을 허용하지 않는 클래스 대신 사용할 수 있는 새로운 클래스를 만들되, 기존 클래스와 같은 인터페이스를 데코레이터가 제공하게 만들고,
기존 클래스를 데코레이터 내부에 필드로 유지하는 것이다.

새로 정의해야하는 기능은 데코레이터의 메서드에 새로 정의하고,
기존 기능이 그대로 필요한 부분은 데코레이터의 메서드가 기존 클래스의 메서드에게 요청을 전달(forwarding) 한다.

이런 위임은 코틀린이 제공해준다.
인터페이스를 구현할때 by 키워드를 통해 그 인터페이스에 대한 구현을 다른 객체에 위임 중이라는 사실을 명시할 수 있다.
 */
class CustomDelegatingCollection<T> : Collection<T> {
    private val innerList = arrayListOf<T>()
    override val size: Int get() = innerList.size
    override fun isEmpty() : Boolean = innerList.isEmpty()
    override fun contains(element: T): Boolean = innerList.contains(element)
    override fun iterator(): Iterator<T> = innerList.iterator()
    override fun containsAll(elements: Collection<T>): Boolean = innerList.containsAll(elements)
}

// by 키워드 사용 (위 예제 재작성)
class DelegatingCollection<T> (
    innerList: Collection<T> = ArrayList<T>()
) : Collection<T> by innerList{}


/*
CountingSet에 MutableCollection의 구현 방식에 대한 의존 관계가 생기지 않는다.
 */
class CountingSet<T> (
    val innerSet: MutableCollection<T> = HashSet<T> ()
) : MutableCollection<T> by innerSet { // MutableCollection의 구현을 innerSet 에게 위임한다.
    var objectsAdded = 0

    /**
     * 메서드 중 일부의 동작을 변경하고 싶은 경우 메서드를 오버라이드하면 컴파일러가 생성한 메서드 대신 오버라이드한 메서드가 쓰인다.
     * 기존 클래스의 메서드를 위임하는 기본 구현으로 충분한 메서드는 따로 오버라이드할 필요가 없다.
     */
    override fun add(element: T) : Boolean {
        objectsAdded++
        return innerSet.add(element)
    }

    override fun addAll(c: Collection<T>): Boolean {
        objectsAdded += c.size
        return innerSet.addAll(c)
    }
}

fun main() {
    val cset = CountingSet<Int>()
    cset.addAll(listOf(1, 1, 2))
    // 3 objects were added, 2 remain
    println("${cset.objectsAdded} objects were added, ${cset.size} remain")
}