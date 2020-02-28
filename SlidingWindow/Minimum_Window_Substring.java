//Leetcode 76. Minimum Window Substring
//Question - https://leetcode.com/problems/minimum-window-substring/


class Solution {
    public String minWindow(String s, String t) {
        //creating a storage to check if the threshold frequency of each character in 't' is met.
        HashMap<Character, Integer> freqTable = new HashMap<Character,Integer>();
        
        //fill the frequency table
        for(int i=0;i<t.length();i++){
            char curr = t.charAt(i);
            int freq = 1;
            if(freqTable.containsKey(curr)){
                freq = freqTable.get(curr);
                freq++;
            }
            freqTable.put(curr,freq);
        }
      
        //initialize the window
        int start = 0;
        int end = 0;
        String ans = "";
        int windowLen = Integer.MAX_VALUE;
        //keeps the count of distinct characters in 't' which are not yet in the window
        int unmatchedChars = freqTable.size();
        
        
        //start sliding the window
        while(end<s.length()){
            char endChar = s.charAt(end);
            
            if(freqTable.containsKey(endChar)){
                int freq = freqTable.get(endChar);
                freq--;
                freqTable.put(endChar, freq);
                
                //minimum threshold reached for 'endChar'
                if(freq==0) unmatchedChars--;
            }
            
            end++;
            //as long as the threshold of all characters in 't' is maintained. Keep sliding the start of the window to the right. Trimming unnecessarcy characters to minimize window size.
            //The sliding of start of the window to the right is triggered only when the threshold of all characters in 't' is met.
            while(start<s.length() && unmatchedChars==0){
                char startChar = s.charAt(start);
                
                //update window length and answer after each slide.
                if(end-start<windowLen){
                    windowLen = end-start;
                    ans = s.substring(start,end);
                    
                }
                
                if(freqTable.containsKey(startChar)){
                    int freq = freqTable.get(startChar);
                    freq++;
                    freqTable.put(startChar,freq);
                    
                    //threshold for startChar is not met. sliding the start of window has to be stopped.
                    if(freq>0) unmatchedChars++;
                }
                start++;
            }
        }
        
        return ans;
        
    }
}
