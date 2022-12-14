package me.whiteship.refactoring._06_mutable_data._18_split_variable;

/**
 * 리팩토링 18. 변수 쪼개기 (Split Variable)
 *
 * - 어떤 변수가 여러번 재할당 되어도 적절한 경우
 *  > 반복문에서 순회하는데 사용하는 변수 또는 인덱스
 *  > 값을 축적시키는데 사용하는 변수
 *
 *  - 그밖에 경우에 재할당 되는 변수가 있다면 해당 변수는 여러 용도로 사용되는 것이며,
 *  변수를 분리해야 더 이해하기 좋은 코드를 만들 수 있다.
 *  > 변수 하나 당 하나의 책임(Responsibility)을 지도록 만든다.
 *  > 상수를 활용하자. (자바스크립트의 const, 자바의 final)
 *
 *  한 변수가 여러번 할당되는 경우 옳은가? 함수의 용도가 맞는가? 고민해보자.
 *  한 변수는 하나의 책임만 갖도록 리팩토링 하자.
 */
public class Haggis {

    private double primaryForce;
    private double secondaryForce;
    private double mass;
    private int delay;

    public Haggis(double primaryForce, double secondaryForce, double mass, int delay) {
        this.primaryForce = primaryForce;
        this.secondaryForce = secondaryForce;
        this.mass = mass;
        this.delay = delay;
    }

    public double distanceTravelled(int time) {
        double result;
        double acc = primaryForce / mass;
        int primaryTime = Math.min(time, delay);
        result = 0.5 * acc * primaryTime * primaryTime;

        int secondaryTime = time - delay;
        if (secondaryTime > 0) {
            double primaryVelocity = acc * delay;
            acc = (primaryForce + secondaryForce) / mass;
            result += primaryVelocity * secondaryTime + 0.5 * acc * secondaryTime + secondaryTime;
        }

        return result;
    }
}
