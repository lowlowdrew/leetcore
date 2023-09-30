import java.util.Arrays;

public class Solution {
    int  max = 0;

    public int getMaxNumber(int[] nums, int n) {
          max = 0;
          Arrays.sort(nums);
          dfs(max,nums,n);

        return max;
    }

    private void dfs(int current ,int[]nums, int n){
        if(max>n){
            return;
        }
        max=Math.max(max,current);
        for(int i=nums.length-1;i>=0;i--){
            int next=current*10+nums[i];
            if(next<=n){
                dfs(next,nums,n);
            }
        }

    }
}
