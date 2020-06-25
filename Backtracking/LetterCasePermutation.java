//LeetCode 784. Letter Case Permutation
//Question - https://leetcode.com/problems/letter-case-permutation/

class Solution {
    public List<String> letterCasePermutation(String S) {
        List<String> res = new ArrayList<>();
        helper(S, "", 0, res);
        return res;
    }
    
    public void helper(String inputString, String newString, int index, List<String> l){
        //base condition
        if(inputString.length() == newString.length()){
            l.add(newString);
            return;
        }
        
        //check if current char is a number or alphabet
        char currChar = inputString.charAt(index);
        if(Character.isDigit(currChar)){
            helper(inputString, newString + currChar, index + 1, l);
        }
        else{
            //considering lower case 
            helper(inputString, newString + Character.toLowerCase(currChar), index + 1, l);
            
            //considering upper case
            helper(inputString, newString + Character.toUpperCase(currChar), index + 1, l);
        }
    }
}
