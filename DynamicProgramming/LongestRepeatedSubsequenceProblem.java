/*
https://www.techiedelight.com/longest-repeated-subsequence-problem/

The problem is a modification of Longest Common Subsequence Problem. Here, we take both the strings to be the same and then find the Longest Common Subsequence.

The Recurrence is almost the same as LCS - 
LRS[i][j] = 0                               if i = 0 || j = 0
          = LRS[i-1][j-1] + 1               if (i != j) && s[i] == s[j]
          = max(LRS[i-1][j], LRS[i][j-1])   if s[i] != s[j]
i and j can not have the same index as they represent the indixes of the same string. 

Normal Recursion would require exponential time and in worst case - NONE OF THE CHARACTERS REPEAT - time complexity turns out to be O(2^n). Each call further makes two other calls.

*/


//Memoized Recursion

public int LRSlen(String s, int i, int j, HashMap<String, Integer> map){
    if(i==0 || j==0) return 0; //nothing to check of repetition - we have exhausted the string
    
    String key = i+"|"+j;
    
    if(!map.containsKey(key)){
        if(i!=j && s.charAt(i-1) == s.charAt(j-1)){
            map.put(key, LRSlen(s, i-1, j-1, map) + 1);
        }
        else{
            map.put(key, Math.max(LRSlen(s, i-1, j, map), LRSlen(s, i, j-1, map)));
        }
    }
    return map.get(key);
} 
//Time & Space Complexity - O(n^2)


//Bottom Up DP

public int LRSlen(String s){
    int n = s.length();
    
    //len[i][j] indicates the length of the LRS of s[0...i-1] and s[0...j-1]
    int len[][] = new int[n+1][n+1];
    
    for(int i=1 ; i < n ; i++){
        for(int j=1 ; j < n ; j++){
            if( i!=j && s.charAt(i-1) == s.charAt(j-1) ){
                len[i][j] = len[i-1][j-1] + 1;
            }
            else len[i][j] = Math.max(len[i][j-1], len[i-1][j]);
        }
    }
    
    return len[n][n];
}

/*
Time Complexity - O(n^2)
Space Complexity - O(n^2) --> can be further optimized to O(n) [we only require the values from the previous rows]
*/

//Print the LRS --> Assuming we already have the 2D len[][] array with us

public String printLRS(String s, int i, int j, int len[][]){
    if(i==0 || j==0) return "";
    
    if(i!=j && s.charAt(i-1) == s.charAt(j-1)){
        return printLRS(s, i-1, j-1, len) + s.charAt(i-1);
    }
    else{
        if(len[i-1][j] > len[i][j-1]){
            return printLRS(s, i-1, j, len);    
        }
        else{
            return printLRS(s, i, j-1, len);  
        }
    }
    
}
