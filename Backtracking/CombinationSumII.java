//LeetCode 40. Combination Sum II
//Question - https://leetcode.com/problems/combination-sum-ii/

class Solution {
    public List<List<Integer>> combinationSum2(int[] nums, int target) {
        List<List<Integer>> res = new ArrayList<>();
        
        Arrays.sort(nums);
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
            if(i > index && nums[i] == nums[i-1]) continue;
            
            l.add(nums[i]);
            helper(nums, i + 1, sum + nums[i], target, l, res);
            l.remove(l.size()-1);
        }
    }
}
