//Leetcode 159. Longest Substring With Atmost Two Distinct Characters
//Question - https://leetcode.com/problems/longest-substring-with-at-most-two-distinct-characters/

class Solution {
    public int lengthOfLongestSubstringTwoDistinct(String s){
        if(s.length()==0) return 0;
        
        //to store the frequency of characters
        HashMap<Character,Integer> freqTable = new HashMap<>();
        
        int start = 0;
        int end = 0;
        int distinctChars = 0;
        int len = 0; 
        
        while(end<s.length()){
            char endChar = s.charAt(end);
            int freq = freqTable.getOrDefault(endChar,0);
            freq++;
            freqTable.put(endChar,freq); //increment count for repeated characters
            if(freq==1) distinctChars++; //count distinct characters in the window
            end++; //slide 'end' of window to right
            
            while(distinctChars>2){
                //slide 'start' of window to right as long as the number of distinct characters in window is greater than 2
                char startChar = s.charAt(start);
                
                if(freqTable.containsKey(startChar)){
                    freq = freqTable.get(startChar);
                    freq--; 
                    freqTable.put(startChar,freq);
                    if(freq==0) distinctChars--; //startChar is no longer a part of our window and so the number of distinct characters must be decremented
                }
                
                start++;
            }
            len = Math.max(len,end-start); //calculate length after each slide 
            
        }
        return len;
    }
}
