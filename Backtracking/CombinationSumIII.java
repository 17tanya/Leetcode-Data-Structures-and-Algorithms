//LeetCode 216. Combination Sum III
//Question - https://leetcode.com/problems/combination-sum-iii/

class Solution {
    //k ==> size of each combination
    //n ==> target sum of each combination
    //numbers from [1..9] can be used only once.
    
    public List<List<Integer>> combinationSum3(int k, int n) {
        List<List<Integer>> res = new ArrayList<>();
        helper(k, n, 1, 0, new ArrayList<>(), res);
        
        return res;
    }
    
    public void helper(int k, int n, int num, int sum, List<Integer> l, List<List<Integer>> res){
        //Check for the base condition
        if(l.size() == k && sum == n){
            res.add(new ArrayList<>(l));
            return;
        }
        
        //to avoid useless recursion
        else if(l.size() == k || sum > n) return;
        
        for(int i = num ; i <= 9 ; i++){
            //make a decision
            l.add(i);
            
            helper(k, n, i + 1, sum + i, l, res);
            
            //backtrack
            l.remove(l.size()-1);
        }
    }
}
