package interviewQuestions.dynamicProgramming.knapsack;

public class RodCutting {

    public static void main(String[] args) {
        int[] arr = new int[]{1, 5, 8, 9, 10, 17, 17, 20};
        int n = 8;
        System.out.println(cutRod(arr, n));
    }

    static int cutRod(int[] price, int n) {
        int l = price.length;
        if (l != 0 && n != 0) {
            int[][] dp = new int[l + 1][l + 1];

            int i;
            int j;
            for(i = 0; i < l + 1; ++i) {
                for(j = 0; j < l + 1; ++j) {
                    if (i == 0 || j == 0) {
                        dp[i][j] = 0;
                    }
                }
            }

            for(i = 1; i < l + 1; ++i) {
                for(j = 1; j < l + 1; ++j) {
                    if (i <= j) {
                        dp[i][j] = Math.max(price[i - 1] + dp[i][j - i], dp[i - 1][j]);
                    } else {
                        dp[i][j] = dp[i - 1][j];
                    }
                }
            }

            return dp[l][l];
        } else {
            return 0;
        }
    }
}
