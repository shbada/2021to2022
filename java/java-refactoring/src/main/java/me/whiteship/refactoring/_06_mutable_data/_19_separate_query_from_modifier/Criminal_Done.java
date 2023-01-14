package me.whiteship.refactoring._06_mutable_data._19_separate_query_from_modifier;

import java.util.List;

public class Criminal_Done {

    /**
     * for 문 중복
     * @param people
     */
    public void alertForMiscreant(List<Person> people) {
        for (Person p : people) {
            if (p.getName().equals("Don")) { // 찾으면
                setOffAlarms(); // 알람 끄기
            }

            if (p.getName().equals("John")) {
                setOffAlarms();
            }
        }
    }

    /**
     * for 문 중복 제거
     * @param people
     */
    public void alertForMiscreant2(List<Person> people) {
        if (!findMiscreant(people).isBlank()) {
            setOffAlarms(); // 알람 끄기
        }
//        for (Person p : people) {
//            if (p.getName().equals("Don")) { // 찾으면
//                setOffAlarms(); // 알람 끄기
//            }
//
//            if (p.getName().equals("John")) {
//                setOffAlarms();
//            }
//        }
    }

    public String findMiscreant(List<Person> people) {
        for (Person p : people) {
            if (p.getName().equals("Don")) { // 찾으면
                return "Don";
            }

            if (p.getName().equals("John")) {
                return "John";
            }
        }

        return "";
    }

    private void setOffAlarms() {
        System.out.println("set off alarm");
    }
}
