package me.whiteship.refactoring._12_repeated_swtiches;

public class SwitchImprovements_Done {

    public int vacationHours(String type) {
        // 결과를 담을 수 있다.
        int result = switch (type) {
            // switch expression
//            case "full-time" -> 120;
//            case "part-time" -> 80;
//            case "temporal" -> 32;
            case "full-time": yield 120;
            case "part-time": yield 80;
            case "temporal": yield 32;
            default: yield 0;
        };

        return result;
    }
}
