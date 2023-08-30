package interviewQuestions.dynamicProgramming.knapsack;

public class MinSubsetSumDiff {

    public static void main(String[] args) {
        int[] arr = new int[]{3, 1, 4, 2, 2, 1};
        int n = arr.length;
        int c = findMin(arr, n);
        System.out.println("The minimum difference between 2 sets is " + c);
    }

    static int findMin(int[] arr, int n) {
        int range = 0;
        int res = Integer.MAX_VALUE;
        if (n == 0) {
            return 0;
        } else {
            int j = arr.length;

            for(int var7 = 0; var7 < j; ++var7) {
                int i = arr[var7];
                range += i;
            }

            boolean[][] dp = new boolean[n + 1][range + 1];

            int k;
            for(k = 0; k < n + 1; ++k) {
                for(j = 0; j < range + 1; ++j) {
                    if (k == 0) {
                        dp[k][j] = false;
                    }

                    if (j == 0) {
                        dp[k][j] = true;
                    }
                }
            }

            for(k = 1; k < n + 1; ++k) {
                for(j = 1; j < range + 1; ++j) {
                    if (arr[k - 1] > j) {
                        dp[k][j] = dp[k - 1][j];
                    } else {
                        dp[k][j] = dp[k - 1][j - arr[k - 1]] || dp[k - 1][j];
                    }
                }
            }

            for(k = 0; k < (range + 1) / 2; ++k) {
                if (dp[n][k]) {
                    res = Math.min(res, range - 2 * k);
                }
            }

            return res;
        }
    }
}

