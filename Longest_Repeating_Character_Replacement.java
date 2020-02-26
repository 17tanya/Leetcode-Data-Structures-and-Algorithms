//Leetcode 424. Longest Repeating Character Replacement
//Question - https://leetcode.com/problems/longest-repeating-character-replacement/

class Solution {
    public int characterReplacement(String s, int k) {
        //number of characters that can be replaced in a given window is
        //charsToBeReplaced = length Of Window - frequency Of Most Frequent Letter In Window
        //if charsToBeReplaced <= k then the window is valid - else window in invalid and we need to slide 'start' to right 
        HashMap<Character,Integer> freqTable = new HashMap<>();
        
        int start = 0;
        int len = 0;
        int maxCount = 0;
        int charsToReplace = 0;
        
        for(int end = 0 ; end < s.length() ; end++){
            
            char endChar = s.charAt(end);
            int freq = freqTable.getOrDefault(endChar,0);
            freq++;
            freqTable.put(endChar,freq);
            maxCount = Math.max(maxCount, freq);
            charsToReplace = end - start - maxCount + 1;
            
            if(charsToReplace>k){
                char startChar = s.charAt(start);
                freq = freqTable.get(startChar);
                freq--;
                freqTable.put(startChar,freq);
                start++;
            }
            len = Math.max(len, end-start+1);
        }
        return len;
    }
}
