package item05_comparable_comparator;

import java.util.Comparator;

/**
 * Comparator : 이미 Comparable 이 구현된 경우 사용한다
 */
public class Member2 implements Comparator<Member2> {
    private int memberId;
    private String memberName;

    public Member2() {

    }

    public Member2(int memberId, String memberName) {
        this.memberId = memberId;
        this.memberName = memberName;
    }

    public int getMemberId() {
        return memberId;
    }

    public void setMemberId(int memberId) {
        this.memberId = memberId;
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    @Override
    public int compare(Member2 member1, Member2 member2) { /* 하나는 자신이고, 다른 하나는 비교대상 */
        /** 오름차순 출력 */
        /* this 랑 비교하겠다 */
        if (member1.memberId > member2.memberId) {
            return 1;
        } else if (member1.memberId < member2.memberId) {
            return -1;
        } else {
            return 0;
        }
    }
}
