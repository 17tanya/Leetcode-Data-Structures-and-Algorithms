//LeetCode 78. Subsets
//Question - https://leetcode.com/problems/subsets/

class Solution {
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        helper(nums, 0, new ArrayList<>(), res);
        
        return res;
    }
    
    public void helper(int nums[], int index, List<Integer> subset, List<List<Integer>> res){
        res.add(new ArrayList<>(subset));
        
        /*
        For each element we have two choices - 
            1. Include element in current subset
            2. Exclude element from current subset
        
        We start from index 0 and it considers all elements in the array ==> this leads to the 
        deepest branch of the state space tree.
        
        Each level in the state space tree will signify a subset of different length.
        
        Input ==> [1,2,3]
        Output ==> [[],[1],[1,2],[1,2,3],[1,3],[2],[2,3],[3]]
        
        State space tree ==>
        level 0                                 []
        level 1             [1]                 [2]                 [3]
        level 2     [1,2]           [1,3]      [2,3]
        level 3    [1,2,3]
        */
        
        for(int i = index ; i < nums.length ; i++){
            //Choice 1. Include the elment
            subset.add(nums[i]);
            helper(nums, i + 1, subset, res);
            //Choice 2. Exclude the element ==> Basically Backtrack after choosing the element
            subset.remove(subset.size()-1);
        }
        
    }
}
