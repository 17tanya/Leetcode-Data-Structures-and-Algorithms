//LeetCode 20. Valid Parentheses
//Question - https://leetcode.com/problems/valid-parentheses/

class Solution {
    public boolean isValid(String s) {
        Stack<Character> stk = new Stack<>();
        
        for(char c : s.toCharArray()){
            if(c == '(' || c == '[' || c == '{') stk.push(c);
            else{
                switch(c){
                    case ')': if(stk.isEmpty() || stk.pop() != '(') return false;
                        break;
                    case ']': if(stk.isEmpty() || stk.pop() != '[') return false;
                        break;
                    case '}': if(stk.isEmpty() || stk.pop() != '{') return false;
                        break;
                }
            }
        }
        if(stk.isEmpty()) return true;
        return false;
    }
}
