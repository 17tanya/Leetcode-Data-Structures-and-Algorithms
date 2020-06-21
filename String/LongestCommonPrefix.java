//LeetCode 14. Longest Common Prefix
//Question - https://leetcode.com/problems/longest-common-prefix/

class Solution {
    public String longestCommonPrefix(String[] strs) {
        int n = strs.length;
        if(n == 0) return "";
        
        int minLen = Integer.MAX_VALUE;
        String s = null;
        int index = 0;
        boolean matched = false;
        
        for(int i = 0 ; i < n ; i++){
            if(minLen > strs[i].length()){
                minLen = strs[i].length();
                s = strs[i];
            }
        }
        
        for(int i = 0 ; i < minLen ; i++){
            for(String str : strs){
                if(str.charAt(i) != s.charAt(i)){
                    return s.substring(0, index);
                }
            }
            index++;
        }
        
        return s;
    }
}
