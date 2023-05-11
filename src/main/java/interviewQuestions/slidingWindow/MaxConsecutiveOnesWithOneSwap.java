package interviewQuestions.slidingWindow;

public class MaxConsecutiveOnesWithOneSwap {
    public static void main(String[] args) {
        int[] arr = {0, 1, 0, 1, 0, 0, 1, 1, 1, 0, 1, 1, 1};
        System.out.println(maxOnes(arr));
    }

    static int maxOnes(int[] arr){
        int right=0;
        int left=0;
        int lastIndexOfZero=-1;
        int res=0;
        while(right<arr.length){
            if(arr[right] == 0){
                left=lastIndexOfZero+1;
                lastIndexOfZero=right;
            }

            res=Math.max(res, right-left+1);
            right++;
        }
        return res;
    }
}
