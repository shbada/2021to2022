package Y01_그리디;

public class Exam1_거스름돈 {
    public static void main(String[] args) {
        int n = 1260;
        int[] coinTypes = {500, 100, 50, 10};

        int cnt = 0;
        for (int i = 0; i < coinTypes.length; i++) {
            cnt += (n / coinTypes[i]);

            n = n % coinTypes[i];
        }

        System.out.println(cnt);
    }

    /* 해설 */
    public static void 해답보기() {
        int n = 1260;
        int cnt = 0;
        int[] coinTypes = {500, 100, 50, 10};

        for (int i = 0; i < 4; i++) {
            int coin = coinTypes[i];
            cnt += n / coin;
            n %= coin;
        }

        System.out.println(cnt);
    }
}
