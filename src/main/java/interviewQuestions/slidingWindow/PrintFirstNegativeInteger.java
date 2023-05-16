package interviewQuestions.slidingWindow;

import java.util.*;

public class PrintFirstNegativeInteger {
    public static void main(String[] args) {
        long[] arr = {-8, 2, 3, -6, 10};
        int K=2;
        System.out.println(Arrays.toString(firstNegativeInteger(arr, K)));
    }
    static long[] firstNegativeInteger(long[] arr, int K)
    {
        int l=0,r=0;
        List<Long> ans = new ArrayList<>();
        Queue<Long> q = new LinkedList<>();
        int n=arr.length;
        while(r<n){
            if(arr[r] < 0)
                q.offer(arr[r]);
            if(r-l+1 < K)
                r++;
            else if(r-l+1 == K){
                if(q.size() > 0){
                    long val = q.peek();
                    ans.add(val);
                    if(val == arr[l])
                        q.poll();
                }
                else{
                    ans.add((long)0);
                }

                l++;
                r++;
            }
        }
        return ans.stream().mapToLong(i -> i).toArray();
    }
}
