//LeetCode 47. Permutations II
//Question - https://leetcode.com/problems/permutations-ii/

class Solution {
    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(nums);
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
            //The 2nd check is for duplicates
            
            /*
            Generating repeated permutations from duplicates can be avoided by using two
            different conditions - 
                1. (i > 0 && nums[i] == nums[i-1] && visit[i-1]) continue;
                This means include current number if the previous identical number is not 
                included. After tracing out the state space tree, we can see that this condition
                creates the permutation by the last duplicate number, any permutation with an 
                intermediate identical number would never reach the base case and creates a
                waste of space and time.
                
                2. (i > 0 && nums[i] == nums[i-1] && !visit[i-1]) continue;
                This means that include current number if the previous identical number is
                included. After tracing out the state space tree, we can see that this condition
                creates the permutation by the first duplicate number, any permutation with an 
                intermediate identical number is stopped from going deeper into recursion,
                saving time and space.
            */
            if(visit[i] || (i > 0 && nums[i] == nums[i-1] && !visit[i-1])) continue;
            
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
