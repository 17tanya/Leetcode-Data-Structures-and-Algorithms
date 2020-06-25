//LeetCode 39. Combination Sum
//Question - https://leetcode.com/problems/combination-sum/

class Solution {
    public List<List<Integer>> combinationSum(int[] nums, int target) {
        List<List<Integer>> res = new ArrayList<>();
        helper(nums, 0, 0, target, new ArrayList<>(), res);
        return res;
    }
    
    public void helper(int nums[], int index, int sum, int target, List<Integer> l, List<List<Integer>> res){
        if(sum > target) return;
        else if(sum == target){
            res.add(new ArrayList<>(l));
            return;
        }
        
        for(int i = index ; i < nums.length ; i++){
            
            l.add(nums[i]);
            helper(nums, i, sum + nums[i], target, l, res);
            l.remove(l.size()-1);
        }
        
    }
}
