package me.whiteship.refactoring._11_primitive_obsession._32_replace_conditional_with_polymorphism.done.variation;

import java.util.List;

public class VoyageRating {

    protected Voyage voyage;

    protected List<VoyageHistory> history;

    public VoyageRating(Voyage voyage, List<VoyageHistory> history) {
        this.voyage = voyage;
        this.history = history;
    }

    public char value() {
        final int vpf = this.voyageProfitFactor();
        final int vr = this.voyageRisk();
        final int chr = this.captainHistoryRisk();
        return (vpf * 3 > (vr + chr * 2)) ? 'A' : 'B';
    }

    protected int captainHistoryRisk() { // private -> protected
        int result = 1;
        if (this.history.size() < 5) result += 4;
        result += this.history.stream().filter(v -> v.profit() < 0).count();

        // 특정 경우에 특수한 로직 필요
        // 이 한줄은 옮기자. (ChinaExperiencedVoyageRating 로)
//        if (this.voyage.zone().equals("china") && this.hasChinaHistory()) result -= 2;
        return Math.max(result, 0);
    }

    private int voyageRisk() {
        int result = 1;
        if (this.voyage.length() > 4) result += 2;
        if (this.voyage.length() > 8) result += this.voyage.length() - 8;

        // 특정 경우에 특수한 로직 필요
        if (List.of("china", "east-indies").contains(this.voyage.zone())) result += 4;
        return Math.max(result, 0);
    }

    protected int voyageProfitFactor() {
        int result = 2;

        if (this.voyage.zone().equals("china")) result += 1;
        if (this.voyage.zone().equals("east-indies")) result +=1 ;

        // 여기서부터 로직
        // ChinaExperiencedVoyageRating 로 옮길 수 있지않을까?
        // 1) 메서드 추출
        result += voyageLengthFactor();
        result += historyLengthFactor(); // 직접 호출로 변경

        return result;
    }

    protected int voyageLengthFactor() { // And? 2가지 이상의 일을 한다는 의미  -> 1개의 책임으로 수정
//        int result = 0;

//        result += historyLengthFactor(); // 직접 호출로 변경
        return (this.voyage.length() > 14) ? -1 : 0;
    }

    private int historyLengthFactor() {
        return (this.history.size() > 8) ? 1 : 0;
    }

//    private boolean hasChinaHistory() {
//        return this.history.stream().anyMatch(v -> v.zone().equals("china"));
//    }
}
