//Leetcode 3. Longest Substring Without Repeating Characters
// Question - https://leetcode.com/problems/longest-substring-without-repeating-characters/

class Solution {
    public int lengthOfLongestSubstring(String s) {
        if(s.length()==0) return 0;
        
        //a Map to store the last-seen index of all characters scanned - we will move the 'start' of our window to right according to the data in the map
        HashMap<Character,Integer> indexTable = new HashMap<>();
        
        int start = 0;
        int end = 0;
        int len = s.length();
        int result = 0;
        
        for(start = 0, end = 0; end<len ; end++){
            char currChar = s.charAt(end);
            if(indexTable.containsKey(currChar)){
                start = Math.max(start,indexTable.get(currChar)+1);
                //the max condition helps us to check if 'currChar' is in the current window or not.
            }
            
            //update indexTable to store the current index of currChar
            indexTable.put(currChar,end);
            result = Math.max(result,end-start+1);
        }
        return result;
        
        
    }
}
