//Leetcode 438. Find All Anagarms In a String
// Question - https://leetcode.com/problems/find-all-anagrams-in-a-string/

class Solution {
    public List<Integer> findAnagrams(String s, String p) {
        //to store the result
        List<Integer> res = new ArrayList<>();
        
        //to store frequency of each character in 'p'
        HashMap<Character,Integer> freqTable = new HashMap<>();
        
        for(int i=0;i<p.length();i++){
            int freq = freqTable.getOrDefault(p.charAt(i),0);
            freqTable.put(p.charAt(i),++freq);
        }
        
        int start = 0;
        int end = 0;
        int counter = freqTable.size();
        
        
        while(end<s.length()){
        //keep sliding the 'end' of the window to the right till all the characters of 'p' are present in the window
            char endChar = s.charAt(end);
            
            if(freqTable.containsKey(endChar)){
                int freq = freqTable.get(endChar);
                --freq;
                freqTable.put(endChar,freq);
                if(freq==0) counter--;
            }
            
            end++;
            
            //the current window has all the characters of 'p'. slide the 'start' of the window and check if the window contains anagram or not
            while(counter==0){
                
                //checking for anagram
                if(end-start == p.length()) res.add(start);
                
                char startChar = s.charAt(start);
                
                if(freqTable.containsKey(startChar)){
                    int freq = freqTable.get(startChar);
                    freq++;
                    freqTable.put(startChar,freq);
                    if(freq>0) counter++; //condition not met - window does not contain all characters of 'p' - stop sliding the 'start' of window to right and begin moving 'end' to the right
                }
                
                start++;
            }
            
        }
        
        return res;
    }
}
