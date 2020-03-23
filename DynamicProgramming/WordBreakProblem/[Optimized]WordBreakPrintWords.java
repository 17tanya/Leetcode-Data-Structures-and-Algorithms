/*
https://leetcode.com/problems/word-break-ii/submissions/
*/

class Solution {
    public List<String> wordBreak(String s, List<String> wordDict) {
        if(s.length()==0 || s==null) return new ArrayList<String>();
        
        int maxLen = -1;
        //to find the length of the longest word in the dictionary
        //will be used to minimize iterations
        for(int i=0 ; i < wordDict.size() ; i++){
            if(maxLen < wordDict.get(i).length()) maxLen = wordDict.get(i).length();   
        }
        
        //Converting List to Set as retieval from set takes place in constant time
        Set<String> dic = new HashSet<String>(wordDict);
        
        //to store solutions to already seen subproblems
        HashMap<Integer, ArrayList<String>> map = new HashMap<>();
        
        //helper function
        return dfs(s,0,maxLen,dic,map);
        
    }
    
    public List<String> dfs(String s, int start, int maxLen, Set<String> dict, HashMap<Integer, ArrayList<String>> map){
        
        //if subproblem is already solved return the stored solution
        if(map.containsKey(start)) return map.get(start);
        
        //list to store solutions to the current subproblem
        ArrayList<String> res = new ArrayList<String>();
        
        //if we have exhausted the entire string
        if(start == s.length()) res.add("");
        
        /*
        the current subproblem starts at the index 'start'
        instead of considering the string s[start..... s.length()-1] 
        we consider the string ending at minimum of(s.length(), start+maxLen)
        Logic - If want to check if current prefix s[start...end] is a word in the dictionary or not - If it is a valid word 
        it can have a ( maximum length <= length of the longest word in the dictionary ) - considering lengths larger than
        this value is simply a waste of iterations, as no such word exisists in the dictionary.
        However, there might be a case when we are near the end of the string and [start+maxLen > s.length()] in such case 
        the upper bound becomes s.length() ==> we take minimum of the two ==> Math.min(s.length(), start+maxLen)
        */
        
        //considers all possible prefixes
        for(int end=start ; end < Math.min(s.length(), start+maxLen) ; end++){
        
            String prefix = s.substring(start, end+1);
            
            //check if the prefix is a valid word
            if(dict.contains(prefix)){
            
                //solve the subproblem - break down the suffix
                //broken suffix is returned - possible to break into multiple valid results - hence a list is returned
                List<String> brokenSuffix = dfs(s,end+1,maxLen,dict,map);
                
                //for each valid broken suffix add the current prefix and store it in the result
                for(String suff : brokenSuffix){
                    if(suff.length()==0) res.add(prefix); //current prefix is actually the last valid suffix
                    else res.add(prefix + " " + suff);
                }
            }
        }
        
        //store result to current subproblem
        map.put(start, res);
        
        //return result to current subproblem
        return res;
        
    }
}
