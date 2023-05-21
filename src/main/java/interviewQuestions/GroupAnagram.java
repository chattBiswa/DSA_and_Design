package interviewQuestions;
import java.util.*;

public class GroupAnagram {
    public static void main(String[] args) {
        String[] strs = {"eat","tea","tan","ate","nat","bat"};
        System.out.println(groupAnagrams(strs));
    }

    static List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> strMp = new HashMap<>();

        if(strs.length == 0)
            return new ArrayList<>();

        for(String s : strs){
            char [] charFreq = new char[26];
            for(char c : s.toCharArray()){
                charFreq[c - 'a']++;
            }
            String sc = String.valueOf(charFreq);

            if(!strMp.containsKey(sc)){
                strMp.put(sc, new ArrayList<>());
            }
            strMp.get(sc).add(s);
        }


        return new ArrayList<>(strMp.values());
    }
}
