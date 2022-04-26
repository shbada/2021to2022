package com.algorithm._11_백트래킹;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Date 2022/04/26
 * @URL https://leetcode.com/problems/letter-combinations-of-a-phone-number/
 */
public class L17_LetterCombinationsofaPhoneNumber {
    static Map<Character, String> map = new HashMap<>();
    static List<String> result = new ArrayList<>();
    static int size = 0;

    static {
        map.put('2', "abc");
        map.put('3', "def");
        map.put('4', "ghi");
        map.put('5', "jkl");
        map.put('6', "mno");
        map.put('7', "pqrs");
        map.put('8', "tuv");
        map.put('9', "wxyz");
    }

    public static void main(String[] args) {
        // write your code here
        L17_LetterCombinationsofaPhoneNumber main = new L17_LetterCombinationsofaPhoneNumber();
        List<String> strings = main.letterCombinations("23");

        System.out.println(strings);
    }

    public List<String> letterCombinations(String digits) {
        size = digits.length();

        // 빈 문자열의 경우 빈 리스트 리턴
        if (size == 0) {
            return new ArrayList<>();
        }

//        dfs(digits, "");
        dfs(digits, "", 0);

        return result;
    }

    /*
       abc def
       a + d
       a + e
       a + f

       b + d
       b + e
       b + f

       c + d
       c + e
       c + f
     */
//    private void dfs(String digits, String words) {
//        /* size (기존 문자열의 길이) 와 동일할 경우 */
//        if (size == words.length()) {
//            result.add(words); // 만들어진 문자열 add
//            return;
//        }
//
//        // 2
//        String target = map.get(digits.charAt(0)); // abc
//
//        for (int i = 0; i < size; i++) {
//            // 3
//            String substring = digits.substring(1);
//            dfs(substring, words + target.charAt(i));
//        }
//    }

    private void dfs(String digits, String words, int index) {
        /* size (기존 문자열의 길이) 와 동일할 경우 */
        if (size == words.length()) {
            result.add(words); // 만들어진 문자열 add
            return;
        }

        // 2
        String target = map.get(digits.charAt(index)); // abc

        for (int i = 0; i < target.length(); i++) {
            // 3
            dfs(digits, words + target.charAt(i), index + 1);
        }
    }
}
