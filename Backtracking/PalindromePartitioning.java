//LeetCode 131. Palindrome Partitioning
//Question - https://leetcode.com/problems/palindrome-partitioning/

class Solution {
    public List<List<String>> partition(String s) {
        List<List<String>> res = new ArrayList<>();
        helper(s, 0, new ArrayList<String>(), res);
        return res;
    }
    
    public void helper(String s, int index, List<String> l, List<List<String>> res){
        //Base condition
        if(index == s.length()){
            res.add(new ArrayList<>(l));
            return;
        }
        
        //starting from each index, evaluate substrings of all lengths. Check if each
        //substring is a palindrome or not. If it is a palindrome, go deeper into 
        //recursion to see if it is possible to break down the remaining string into
        //palindromic substrings or not.
        for(int i = index ; i < s.length() ; i++){
            //check if the current substring is a palindrome or not
            if(isPalin(s, index, i)){
                
                //make a partition
                l.add(s.substring(index, i+1));
                
                helper(s, i+1, l, res);
                
                //backtrack - undo the partition
                l.remove(l.size()-1);
            }
        }
    }
    
    public boolean isPalin(String s, int start, int end){
        while(start < end){
            if(s.charAt(start++) != s.charAt(end--)) return false;
        }
        
        return true;
    }
}
