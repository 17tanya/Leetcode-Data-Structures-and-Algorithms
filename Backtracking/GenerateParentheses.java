//LeetCode 22. Generate Parentheses
//Question - https://leetcode.com/problems/generate-parentheses/

class Solution {
    public List<String> generateParenthesis(int n) {
        List<String> res = new ArrayList<>();
        helper("", res, 0, 0, n);
        return res;
    }
    
    public void helper(String str, List<String> l, int open, int close, int n){
        if(str.length() == n*2){
            l.add(str);
            return;
        }
        
        //each valid parentheses must have n open braces and not more.
        if(open < n) helper(str + '(', l, open + 1, close, n);
        //To maintain a valid parentheses, the closed brackets can only be added when 
        //we have corresponding open brackets available.
        if(close < open) helper(str + ')', l, open, close + 1, n);
    }
}