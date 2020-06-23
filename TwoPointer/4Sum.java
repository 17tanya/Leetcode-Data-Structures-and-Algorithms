//LeetCode 18. 4Sum
//Question - https://leetcode.com/problems/4sum/

class Solution {
    public List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> res = new ArrayList<>();
        int n = nums.length;
        
        Arrays.sort(nums);
        int sum = 0;
        
        for(int i = 0 ; i < n - 3 ; i++){
            /*
            NOTE - Do not use the condition -
                if(nums[i] > target) break;
                
            The target might be a negative number and all the numbers in the array could be 
            greater than this target(even the negative numbers). However, this does not mean
            that the negative target can not be reached.
            
            example - 
            nums = [1,-2,-5,-4,-3,3,3,5]
            target = -11
            
            solution = [[-5,-4,-3,1]]
            */
            
            //to avoid duplicates
            if(i > 0 && nums[i] == nums[i-1]) continue;

            for(int j = i + 1 ; j < n - 2 ; j++){
                //to avoid duplicates
                if(i + 1 != j && nums[j] == nums[j-1]) continue;
                
                //NOTE - The same applies for this condition - if(nums[i] + nums[j] > target) break;
                
                int low = j + 1;
                int high = n - 1;
                
                while(low < high){
                    
                    sum = (nums[i] + nums[j] + nums[low] + nums[high]);
                    if(sum < target) low++;
                    else if(sum > target) high--;
                    else{
                        res.add(Arrays.asList(nums[i], nums[j], nums[low], nums[high]));
                        
                        //to avoid duplicates
                        while(low < high && nums[low] == nums[low + 1]) low++;
                        while(low < high && nums[high] == nums[high - 1]) high--;
                        
                        low++;
                        high--;
                    }
                }
            }
        }
        return res;
    }
}
