package item05_comparable_comparator;

import java.util.Comparator;
import java.util.TreeSet;

public class MemberTreeSetTest {
    public static void main(String[] args) {
        MemberTreeSet memberTreeSet = new MemberTreeSet();

        Member memberHong = new Member(1004, "홍길동");
        Member memberLee = new Member(1001, "이순신");
        Member memberKim = new Member(1002, "김유신");
        Member memberKang = new Member(1003, "강감찬");

        memberTreeSet.addMember(memberHong);
        memberTreeSet.addMember(memberLee);
        memberTreeSet.addMember(memberKim);
        memberTreeSet.addMember(memberKang);

        /* Comparable 에러 발생; 비교 구현을 하지 않아서 -> Member implements Comparable<Member> 추가 */
        memberTreeSet.showAllMember();

        /* 순서는 중요하지 않고, 나중엔 정렬되서 나온다는 것을 기억 */
//        TreeSet<String> set = new TreeSet<String>();
//
//        set.add("홍길동");
//        set.add("강감찬");
//        set.add("이순신");
//
//        System.out.println(set);

        TreeSet<String> set = new TreeSet<String>(new MyCompare());
        /* String 은 이미 구현되어있다. 문자열 오름차순으로 */
        set.add("Park");
        set.add("Kim");
        set.add("Lee");

        System.out.println(set);
    }
}

class MyCompare implements Comparator<String> {

    @Override
    public int compare(String o1, String o2) {
        // return o1.compareTo(o2); /* 기존의 String compare */
        return o1.compareTo(o2) * (-1); // 내림차순
    }
}
