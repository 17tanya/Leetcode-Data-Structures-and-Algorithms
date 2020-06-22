//LeetCode 15. 3Sum
//Question - https://leetcode.com/problems/3sum/

class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        int n = nums.length;
        List<List<Integer>> res = new ArrayList<>();
        
        //to help facilitate finding the triplets
        Arrays.sort(nums);
        
        //consider nums[i] as the first element of the triplet
        for(int i = 0 ; i < n - 2 ; i++){
            //the array is sorted, if the first element is greater than 0 then all 
            //other elements after the current element would be positive as well.
            //The sum can never be 0.
            if(nums[i] > 0) break;
            
            //skip the current number if it has already been used as the first element
            if(i > 0 && nums[i] == nums[i-1]) continue;
            
            
            //logic to find the other two elements of the triplets
            int low = i+1;
            int high = n - 1;
            while(low < high){
                int sum = nums[i] + nums[low] + nums[high];
                
                //if sum of the chosen elements is < 0
                if(sum < 0) low++;
                //if sum of the chosen elements is > 0
                else if(sum > 0) high--;
                //the sum is 0
                else{
                    //append the current triplet
                    res.add(Arrays.asList(nums[i], nums[low], nums[high]));
                    
                    //keeping the first element fixed(i) and exploring other numbers
                    //for the other two positions in the triplet
                    //loops to avoid duplicates
                    while(low < high && nums[low] == nums[low + 1]) low++;
                    while(low < high && nums[high] == nums[high - 1]) high--;
                    
                    //need to update because after the loop the pointers point to last 
                    //location of the repeated number
                    low++;
                    high--;
                }
            }
            
        }
        return res;
    }
}
