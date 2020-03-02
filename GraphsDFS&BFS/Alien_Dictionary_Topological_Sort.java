//Leetcode 269. Alien Dictionary
//Question - https://leetcode.com/problems/alien-dictionary/


/*There is a new alien language which uses the latin alphabet.
However, the order among letters are unknown to you.
You receive a list of words from the dictionary, where words are sorted lexicographically by the rules of this new language.
Derive the order of letters in this language.

For example,
Given the following words in dictionary,

[
  "wrt",
  "wrf",
  "er",
  "ett",
  "rftt"
]
The correct order is: "wertf".

Note:
You may assume all letters are in lowercase.
If the order is invalid, return an empty string.
There may be multiple valid order of letters, return any one of them is fine.*/


import java.util.*;

public class Main{
    public static void main(String args[]){
    
        Solution sol = new Solution();
        Scanner s = new Scanner(System.in);
        int n = s.nextInt(); //number of words
        String words[] = new String[n];
        for(int i=0;i<n;i++) words[i] = s.next();
        System.out.println(sol.alienOrder(words));
        
    }
}


class Solution{
    public String alienOrder(String[] words) {
        
        if(words.length==0 || words==null) return "";
        
        StringBuffer result = new StringBuffer(""); //to store topological order
        HashMap<Character,Integer> inDegree = new HashMap<>();
        HashMap<Character,Set<Character>> edgeList = new HashMap<>();
        
        for(String s : words){ //initialize (indegree = 0) for all distinct characters of all words
            for(char c : s.toCharArray()){
                inDegree.put(c,0);
            }
        }
        
        for(int i=0 ; i<words.length-1 ; i++){
            //compare a pair of words occuring one after the other in the dictionary
            String s1 = words[i]; //comes first in dictionary
            String s2 = words[i+1]; //comes after the above in the dictionary
            int minLen = Math.min(s1.length(),s2.length()); 
            
            for(int j=0 ; j<minLen ; j++){
                char c1 = s1.charAt(j);//lexicographically smaller
                char c2 = s2.charAt(j);//lexicographically bigger
                
                if(c1!=c2){ //matching each pair of characters of the above strings
                    //treat c1 and c2 as vertices of the graph ==> add an edge
                    //the edge should be directed from c1 ==> c2 (maintain lexicographical order)
                    Set<Character> neighbour = edgeList.getOrDefault(c1,new HashSet<>());
                    if(!neighbour.contains(c2)){
                        neighbour.add(c2); //adding c2 to the neighbours list of c1
                        edgeList.put(c1,neighbour);
                        //adding this edge implies increment in the inDegree of c2
                        inDegree.put(c2,inDegree.get(c2)+1); //changing inDegree of c2
                    }
                }
            }
        }
        
        //topological sort
        Queue<Character> inDegree0 = new LinkedList<>();
        for(char c : inDegree.keySet()){
            if(inDegree.get(c)==0) inDegree0.add(c);
        }
        
        while(!inDegree0.isEmpty()){
            char curr = inDegree0.poll();
            result.append(String.valueOf(curr));
            
            if(edgeList.containsKey(curr)){ //getting neighbours of current character(vertex)
                for(char c : edgeList.get(curr)){
                    inDegree.put(c,inDegree.get(c)-1);
                    if(inDegree.get(c)==0) inDegree0.add(c);
                }
            }
        }
        
        if(result.length()!=inDegree.size()) return ""; //if the input is invalid ==> contains cycles
        else return String.valueOf(result);
    }
}    
