package item05_comparable_comparator;

import java.util.Iterator;
import java.util.TreeSet;

public class MemberTreeSet {
    private TreeSet<Member> treeSet;

    public MemberTreeSet() {
        treeSet = new TreeSet<>();
        /* Member2 _ Comparator 사용시 무조건 타입을 써줘야한다 */
        // treeSet = new TreeSet<Member2>(new Member2()); // comparator 이 구현될
    }

    public MemberTreeSet(Member member) {
        treeSet.add(member);
    }

    public void addMember(Member member) {
        treeSet.add(member);
    }

    public boolean removeMember(int memberId) {
        Iterator<Member> ir = treeSet.iterator();

        while(ir.hasNext()) {
            Member member = ir.next();

            int tempId = member.getMemberId();
            if (tempId == memberId) {
                treeSet.remove(member);
                return true;
            }
        }

        return false;
    }

    public void showAllMember() {
        for(Member member : treeSet) {
            System.out.println(member);
        }

        System.out.println();
    }
}
