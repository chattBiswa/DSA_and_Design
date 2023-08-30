package interviewQuestions.arrays;

public class RemoveKDuplicates {
    public static void main(String[] args) {
        int[] nums = {1,1,1,2,2,3,3};
        int k=1;

        System.out.println(removeDuplicates(nums, k));
    }

    public static int removeDuplicates(int[] nums, int k){
        int i=0;
        for(int item : nums){
            if(i<k || nums[i-k]<item)
                nums[i++] = item;
        }

        return i;
    }
}
