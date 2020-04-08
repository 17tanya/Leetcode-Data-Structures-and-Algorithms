class Solution {
    public int strStr(String haystack, String needle) {
        if(needle.length() == 0) return 0; //return 0 when needle is an empty string
        
        //if hackstack is empty or if it is smaller than needle then we will not find needle
        if(haystack.length() == 0 || haystack.length() < needle.length()) return -1;
        
        int i = 0;
        
        //conditon i == 0 is required to handle the condition when length(haystack) == length(needle)
        while( i == 0 || i <= haystack.length() - needle.length() ){
            
            //we keep checking for further matches if the current characters match
            for(int j=0 ;j < needle.length() && haystack.charAt(i+j) == needle.charAt(j) ; j++){
                if(j == needle.length()-1) return i; //return the start index in haystack only after we have exhausted the entire needle
            }
            
            i++;
        }
        
        return -1;
    }
}
