//LeetCode 6. ZigZag Conversion
//Question - https://leetcode.com/problems/zigzag-conversion/

class Solution {
    public String convert(String s, int numRows) {
        StringBuffer res[] = new StringBuffer[numRows];
        
        for(int i = 0 ; i < numRows ; i++){
            res[i] = new StringBuffer();    
        }
        
        int i = 0;
        while(i < s.length()){
            //going down
            for(int ind = 0 ; ind < numRows && i < s.length() ; ind++){
                res[ind].append(s.charAt(i++));
            }
            //going up
            for(int ind = numRows - 2 ; ind >= 1 && i < s.length() ; ind--){
                res[ind].append(s.charAt(i++));
            }
        }
        
        for(i = 1 ; i < numRows ; i++){
            res[0].append(res[i]);
        }
        
        return res[0].toString();
    }
}