package DP.longestCommonSubsequence;

public class LCS {

    public static void main(String[] args) {
        String str1 = "abcdgh";
        String str2 = "abedfhr";
        int n = str1.length();
        int m = str2.length();
        System.out.println(lcsHelper(str1, str2, n, m));
    }

    static int lcsHelper(String str1, String str2, int n, int m) {
        int[][] dp = new int[n + 1][m + 1];

        int i;
        int j;
        for(i = 0; i < n + 1; ++i) {
            for(j = 0; j < m + 1; ++j) {
                if (i == 0 || j == 0) {
                    dp[i][j] = 0;
                }
            }
        }

        for(i = 1; i < n + 1; ++i) {
            for(j = 1; j < m + 1; ++j) {
                if (str1.charAt(i - 1) == str2.charAt(j - 1)) {
                    dp[i][j] = 1 + dp[i - 1][j - 1];
                } else {
                    dp[i][j] = Math.max(dp[i][j - 1], dp[i - 1][j]);
                }
            }
        }

        return dp[n][m];
    }
}

