package interviewQuestions.arrays;


import java.util.Arrays;

public class MergeSortedArrays {

    public static void main(String[] args) {
        int[] nums1 = new int[]{-1, 0, 0, 3, 3, 3, 0, 0, 0};
        int n1 = 6;
        int[] nums2 = new int[]{1, 2, 2};
        int n2 = 3;
        merge(nums1, n1, nums2, n2);
    }

    static void merge(int[] nums1, int m, int[] nums2, int n) {
        int end1 = m - 1;
        int end2 = n - 1;
        int endFinal = m + n - 1;

        while(end1 >= 0 && end2 >= 0) {
            if (nums1[end1] > nums2[end2]) {
                nums1[endFinal--] = nums1[end1--];
            } else {
                nums1[endFinal--] = nums2[end2--];
            }
        }

        while(end2 >= 0) {
            nums1[endFinal--] = nums2[end2--];
        }

        System.out.println(Arrays.toString(nums1));
    }
}
