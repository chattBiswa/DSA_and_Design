package interviewQuestions.arrays;

import java.util.Arrays;

public class RotateArray {
    public static void main(String[] args) {
        int [] nums = {1,2,3,4,5,6,7};
        int k=3;

        rotate(nums, k);
        System.out.println(Arrays.toString(nums));
    }

    // Logic:
    // divide the array in 2 parts --> [0, (n-k)-1] and [(n-k), n-1]
    // rotate both parts and then rotate full array
    // ex: nums=[1,2,3,4,5,6,7] => [1,2,3,4, | 5,6,7] => [4,3,2,1, | 7,6,5] => [5,6,7,1,2,3,4]
    // for k>n => k=k%n
    // if k is -ve then set k=k+n (take compliment of k)
    public static void rotate(int [] nums, int k){
        int n= nums.length;
        k= k % n;

        reverse(nums, 0, n-k-1);
        reverse(nums, n-k, n-1);
        reverse(nums, 0, n-1);
    }

    public static void reverse(int [] nums, int start, int end){
        while(start < end){
            int temp = nums[start];
            nums[start] = nums[end];
            nums[end] = temp;
            start++; end--;
        }
    }
}
