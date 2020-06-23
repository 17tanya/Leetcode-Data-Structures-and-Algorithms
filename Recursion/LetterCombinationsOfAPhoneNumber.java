//LeetCode 17. Letter Combinations of a Phone Number
//Question - https://leetcode.com/problems/letter-combinations-of-a-phone-number/

class Solution {
    List<String> res;
    Map<Character, List<Character>> map;
    
    public List<String> letterCombinations(String digits) {
        int n = digits.length();
        res = new ArrayList<>();
        
        if(n == 0) return res;
        char str[] = new char[n];
        
        map = new HashMap<>();
        map.put('2', Arrays.asList('a', 'b', 'c'));
        map.put('3', Arrays.asList('d', 'e', 'f'));
        map.put('4', Arrays.asList('g', 'h', 'i'));
        map.put('5', Arrays.asList('j', 'k', 'l'));
        map.put('6', Arrays.asList('m', 'n', 'o'));
        map.put('7', Arrays.asList('p', 'q', 'r', 's'));
        map.put('8', Arrays.asList('t', 'u', 'v'));
        map.put('9', Arrays.asList('w', 'x', 'y', 'z'));
        
        helper(str, digits, 0);
        return res;
        
    }
    
    public void helper(char str[], String digits, int index){
        if(index == digits.length()){
            res.add(String.valueOf(str));
            return;
        }
        
        char currChar = digits.charAt(index);
        List<Character> chars = map.get(currChar);
        
        for(char c : chars){
            str[index] = c;
            helper(str, digits, index + 1);
        }
    }
}