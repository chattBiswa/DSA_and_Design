package interviewQuestions.arrays;

// Kadane's Algo
public class MaxSubArraySum {

    public static void main(String[] args) {
        int[] nums = new int[]{-2, 1, -3, 4, -1, 2, 1, -5, 4};
        System.out.println(maxSubArraySum(nums));
    }

    static int maxSubArraySum(int[] nums) {
        int sum = 0;
        int max = nums[0];

        for (int item : nums) {
            sum += item;
            max = Math.max(sum, max);
            if (sum < 0) {
                sum = 0;
            }
        }

        return max;
    }
}