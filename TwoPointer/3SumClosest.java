//LeetCode 16. 3Sum Closest
//Question - https://leetcode.com/problems/3sum-closest/

class Solution {
    public int threeSumClosest(int[] nums, int target) {
        int n = nums.length;
        Arrays.sort(nums);
        int res = nums[0] + nums[1] + nums[n-1];
        
        for(int i = 0 ; i < n - 2 ; i++){
            if(i > 0 && nums[i] == nums[i-1]) continue;
            
            int low = i+1;
            int high = n-1;
            
            while(low < high){
                int sum = nums[i] + nums[low] + nums[high];
                if(Math.abs(sum - target) < Math.abs(res - target)){
                    res = sum;
                }
                if(sum < target) low++;
                else high--;
            }
        }
        
        return res;
    }
}
