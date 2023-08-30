package interviewQuestions.strings;

public class IsAnagram {
    public static void main(String[] args) {
        String s = "anagram";
        String t = "nagaram";
        if(isAnagram(s, t))
            System.out.println("valid anagram");
        else
            System.out.println("not a valid anagram");
    }

    static boolean isAnagram(String s, String t) {
        int[] charSet = new int[26];
        int n=s.length();
        int m=t.length();
        if(n==m){
            if(n==1 && s.equals(t))
                return true;
            for(int i=0;i<n;i++){
                char temp = s.charAt(i);
                charSet[temp - 'a']++;
            }

            for(int i=0;i<n;i++){
                char temp = t.charAt(i);
                charSet[temp - 'a']--;
            }

            for(int i=0;i<26;i++){
                if(charSet[i] > 0)
                    return false;
            }

        }else{
            return false;
        }

        return true;
    }
}
