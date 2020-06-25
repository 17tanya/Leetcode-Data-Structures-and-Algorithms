//LeetCode 90. Subsets II
//Question - https://leetcode.com/problems/subsets-ii/

class Solution {
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        
        //By sorting the array duplicates would come together consecutively
        Arrays.sort(nums);
        
        helper(nums, 0, new ArrayList<>(), res);
        return res;
    }
    
    public void helper(int nums[], int index, List<Integer> subset, List<List<Integer>> res){
        res.add(new ArrayList<>(subset));
        
        for(int i = index ; i < nums.length ; i++){
            //Always include the first distinct number and ignore the following duplicates
            if(i > index && nums[i] == nums[i-1]) continue;
            
            subset.add(nums[i]);
            helper(nums, i+1, subset, res);
            subset.remove(subset.size()-1);
            
        }
    }
}
