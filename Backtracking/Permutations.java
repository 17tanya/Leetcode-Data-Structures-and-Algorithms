//LeetCode 46. Permutation
//Question - https://leetcode.com/problems/permutations/

class Solution {
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        boolean visit[] = new boolean[nums.length];
        
        helper(nums, visit, new ArrayList<>(), res);
        return res;
    }
    
    public void helper(int nums[], boolean visit[], List<Integer> perm, List<List<Integer>> res){
        //Base Case
        if(perm.size() == nums.length){
            res.add(new ArrayList<>(perm));
            return;
        }
        
        for(int i = 0 ; i < nums.length ; i++){
            //Check if nums[i] is already in the current permutation or not
            if(visit[i]) continue;
            
            //mark nums[i] as visited for the current permutation
            visit[i] = true;
            perm.add(nums[i]);
            
            //generate the permutation further
            helper(nums, visit, perm, res);
            
            //backtrack to generate new permutation
            visit[i] = false;
            perm.remove(perm.size()-1);
        }
    }
}
