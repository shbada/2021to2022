package ch2.인터페이스와클래스;

public class defaultMethod {
    /**
     java8에서 추가된 default 메서드 : List 인터페이스의 soert / Collection 인터페이스의 stream 메서드
     java11에서 추가된 default 메서드 : List의 replaceAll, sort, spliterator 메서드 + 상당히 많은 static 메서드
     */

    /*
    List.java의 sort 메서드

    @SuppressWarnings({"unchecked", "rawtypes"})
    default void sort(Comparator<? super E> c) { // default : 인터페이스 안에서 직접 구현하겠다 (public 키워드가 함축되어있다)
        Object[] a = this.toArray();
        Arrays.sort(a, (Comparator) c);
        ListIterator<E> i = this.listIterator();
        for (Object e : a) {
            i.next();
            i.set((E) e);
        }
    }
     */
}
