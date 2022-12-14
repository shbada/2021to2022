package me.whiteship.refactoring._19_insider_trading.done;

import java.time.LocalDate;

public class Ticket {

    private LocalDate purchasedDate;

    private boolean prime;

    public Ticket(LocalDate purchasedDate, boolean prime) {
        this.purchasedDate = purchasedDate;
        this.prime = prime;
    }

    public LocalDate getPurchasedDate() {
        return purchasedDate;
    }

    public boolean isPrime() {
        return prime;
    }

    /**
     * CheckIn -> Ticket 으로 옮겼다.
     */
    public boolean isFastPass() {
        LocalDate earlyBirdDate = LocalDate.of(2022, 1, 1);

        // isPrime(), getPurchasedDate().. 티켓의 정보가 왜이렇게 많지?
        // 이 메서드는 티켓에 적절하는게 맞지 않을까 라는 생각이 든다.
        return isPrime() && getPurchasedDate().isBefore(earlyBirdDate);
    }
}
