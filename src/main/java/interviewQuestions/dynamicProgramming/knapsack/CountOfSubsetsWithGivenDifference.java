package interviewQuestions.dynamicProgramming.knapsack;


public class CountOfSubsetsWithGivenDifference {
    public static void main(String[] args) {
        int[] arr = new int[]{1, 2, 3, 1, 2};
        int diff = 1;
        int n = arr.length;
        int c = countSubsets(arr, n, diff);
        System.out.println("Count is = " + c);
    }

    static int countSubsets(int[] arr, int n, int diff) {
        int range = 0;
        int var5 = arr.length;

        int i;
        int j;
        for(i = 0; i < var5; ++i) {
            j = arr[i];
            range += j;
        }

        int sum = (range + diff) / 2;
        int[][] dp = new int[n + 1][sum + 1];

        for(i = 0; i < n + 1; ++i) {
            for(j = 0; j < sum + 1; ++j) {
                if (i == 0) {
                    dp[i][j] = 0;
                }

                if (j == 0) {
                    dp[i][j] = 1;
                }
            }
        }

        for(i = 1; i < n + 1; ++i) {
            for(j = 1; j < sum + 1; ++j) {
                if (arr[i - 1] <= j) {
                    dp[i][j] = dp[i - 1][j - arr[i - 1]] + dp[i - 1][j];
                } else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }

        return dp[n][sum];
    }
}

