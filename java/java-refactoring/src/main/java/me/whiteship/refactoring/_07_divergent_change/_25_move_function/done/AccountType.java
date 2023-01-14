package me.whiteship.refactoring._07_divergent_change._25_move_function.done;

public class AccountType {
    private boolean premium;

    public AccountType(boolean premium) {
        this.premium = premium;
    }

    public boolean isPremium() {
        return this.premium;
    }

    /**
     * Account 에서 move
     * -> 만약 이 메서드가 Account의 많은 필드를 바라보고 있다면 다시 Accountㄹ로 옮기는게 나을 수도 있다.
     * @param daysOverdrawn
     * @return
     */
    public double overdraftCharge(int daysOverdrawn) {
        // AccountType 의 메서드
        if (isPremium()) { // 프리미엄 계정인 경우
            // AccountType으로 옮긴다면?
            // Account -> AccountType 의존성이 생겨버린다.
            final int baseCharge = 10;

            if (daysOverdrawn <= 7) { // Account의 daysOverdrawn
                return baseCharge;
            } else {
                return baseCharge + (daysOverdrawn - 7) * 0.85;
            }
        } else {
            return daysOverdrawn * 1.75;
        }
    }
}
