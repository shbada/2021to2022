package item05_comparable_comparator;

/**
 * 대부분 Comparable 를 사용한다.
 */
public class Member implements Comparable<Member> {
    private int memberId;
    private String memberName;

    public Member(int memberId, String memberName) {
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

    /**
     * 비교 구현
     * @param member
     * @return
     */
    @Override
    public int compareTo(Member member) {
        /** 오름차순 출력 */
        /* this 랑 비교하겠다 */
        if (this.memberId > member.memberId) {
            return 1;
        } else if (this.memberId < member.memberId) {
            return -1;
        } else {
            return 0;
        }

        /** 내림차순 출력 */
//        if (this.memberId > member.memberId) {
//            return -1;
//        } else if (this.memberId < member.memberId) {
//            return 1;
//        } else {
//            return 0;
//        }

//        return (this.memberId - member.memberId);        // 오름차순
//        return (this.memberId - member.memberId) * (-1); // 내림차순
    }
}
