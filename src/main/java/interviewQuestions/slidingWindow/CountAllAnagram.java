package interviewQuestions.slidingWindow;

import java.util.*;

public class CountAllAnagram {
    public static void main(String[] args) {
        String str = "cbaebabacd";
        String ptr = "abc";

        System.out.println(countAnagram(str, ptr));
    }

    static int countAnagram(String str, String ptr){
        int res=0;
        int k = ptr.length();
        Map<Character, Integer> freq = new HashMap<>();
        int start=0, end=0;

        for(Character c : ptr.toCharArray()){
            freq.put(c, freq.getOrDefault(c, 0)+1);
        }
        int count = freq.size();

        while(end < str.length()){
            if(freq.containsKey(str.charAt(end))){
                freq.put(str.charAt(end), freq.get(str.charAt(end))-1);
                if(freq.get(str.charAt(end)) == 0)
                    count--;
            }

            if(end-start+1 < k){
                end++;
            }else if(end-start+1 == k){
                if(count == 0)
                    res++;

                char c1 = str.charAt(start);
                if(freq.containsKey(c1)){
                    freq.put(c1, freq.get(c1)+1);
                    if(freq.get(c1) == 1)
                        count++;
                }
                end++;
                start++;
            }
        }
        return res;
    }
}
