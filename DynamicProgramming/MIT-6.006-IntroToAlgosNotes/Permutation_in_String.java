//Leetcode 567. Permutation in String
//Question - https://leetcode.com/problems/permutation-in-string/

class Solution {
    public boolean checkInclusion(String s1, String s2) {
        if(s1.length()==0 && s2.length()==0) return true;
        else if(s1.length()==0) return true;
        else if(s2.length()==0) return false;
        //We are supposed to check if 's2' contains the anagram of 's1'
        
        HashMap<Character,Integer> freqTable = new HashMap<>();
        int freq = -1;
        for(int i=0;i<s1.length();i++){
            char curr = s1.charAt(i);
            freq = freqTable.getOrDefault(curr,0);
            freq++;
            freqTable.put(curr,freq);
        }
        //System.out.println(freqTable);
        int end = 0;
        int start = 0;
        int counter = freqTable.size();
        
        while(end < s2.length()){
            char endChar = s2.charAt(end);
            
            if(freqTable.containsKey(endChar)){
                freq = freqTable.get(endChar);
                freq--;
                freqTable.put(endChar,freq);
                if(freq==0) counter--;
            }
            
            end++;
            
            while(counter==0){
                char startChar = s2.charAt(start);
                
                if(end-start==s1.length()){
                    return true;
                }
                
                if(freqTable.containsKey(startChar)){
                    freq = freqTable.get(startChar);
                    freq++;
                    freqTable.put(startChar,freq);
                    if(freq>0) counter++;
                }
                start++;
            }
        }
        return false;
    }
}
