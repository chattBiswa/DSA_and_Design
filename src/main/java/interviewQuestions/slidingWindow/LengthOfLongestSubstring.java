package interviewQuestions.slidingWindow;

import java.util.HashMap;

public class LengthOfLongestSubstring {
    public static void main(String[] args) {
        String s = "abcabcbb";
        System.out.println(lengthOfLongestSubstring(s));
    }

    static int lengthOfLongestSubstring(String s) {
        int left = 0;
        int right = 0;
        int reslen = 0;
        int n = s.length();

        for(HashMap<Character, Integer> charIdxMap = new HashMap<>(); right < n; ++right) {
            if (charIdxMap.containsKey(s.charAt(right))) {
                left = Math.max(left, charIdxMap.get(s.charAt(right)) + 1);
            }

            charIdxMap.put(s.charAt(right), right);
            reslen = Math.max(reslen, right - left + 1);
        }

        return reslen;
    }
}