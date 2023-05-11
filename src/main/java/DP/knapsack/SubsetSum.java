package DP.knapsack;

public class SubsetSum {

    public static void main(String[] args) {
        int[] set = new int[]{1, 3, 4, 5};
        int sum = 13;
        int n = set.length;
        if (isSubsetSum(set, n, sum)) {
            System.out.println("Found a subset with given sum");
        } else {
            System.out.println("No subset with given sum");
        }

    }

    static boolean isSubsetSum(int[] arr, int n, int sum) {
        boolean[][] dp = new boolean[n + 1][sum + 1];
        if (n == 0 && sum != 0) {
            return false;
        } else {
            int i;
            int j;
            for(i = 0; i < n + 1; ++i) {
                for(j = 0; j < sum + 1; ++j) {
                    if (i == 0) {
                        dp[i][j] = false;
                    }

                    if (j == 0) {
                        dp[i][j] = true;
                    }
                }
            }

            for(i = 1; i < n + 1; ++i) {
                for(j = 1; j < sum + 1; ++j) {
                    if (arr[i - 1] > j) {
                        dp[i][j] = dp[i - 1][j];
                    } else {
                        dp[i][j] = dp[i - 1][j - arr[i - 1]] || dp[i - 1][j];
                    }
                }
            }

//            for(i = 0; i < n + 1; ++i) {
//                for(j = 0; j < sum + 1; ++j) {
//                    System.out.print(dp[i][j] + ", ");
//                }
//
//                System.out.println();
//            }

            return dp[n][sum];
        }
    }
}

