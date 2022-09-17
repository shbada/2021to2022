package me.whiteship.refactoring._07_divergent_change._25_move_function.done;

/**
 * 함수 인라인
 */
public class Account {

    private int daysOverdrawn;

    private AccountType type;

    public Account(int daysOverdrawn, AccountType type) {
        this.daysOverdrawn = daysOverdrawn;
        this.type = type;
    }

    public double getBankCharge() {
        double result = 4.5;
        if (this.daysOverdrawn() > 0) {
            // 메서드 이동 (Account -> AccountType)
            // 만약 이 메서드가 Account의 많은 필드를 바라보고 있다면 다시 Accountㄹ로 옮기는게 나을 수도 있다.
            result += type.overdraftCharge(this.daysOverdrawn());
        }
        return result;
    }

    private int daysOverdrawn() {
        return this.daysOverdrawn;
    }
}
