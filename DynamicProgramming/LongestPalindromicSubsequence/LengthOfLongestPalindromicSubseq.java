/*
https://www.geeksforgeeks.org/longest-palindromic-subsequence-dp-12/
https://www.techiedelight.com/longest-palindromic-subsequence-using-dynamic-programming/
*/

/*
RECURSION
To check for a palindrome X[i....j] we compare the last character with the first character, there are two possibilities - 
1. if X[i] == X[j] then we include the the ith and jth charcaters and recur for the rest of the string,
   ie. X[i+1 .... j-1]
2. if X[i] != Y[j] then we exlude one character at a time and recur in hopes of finding a palindrome subseq. We take the maximum
   of the two.
        2.1. X[i+1 .... j] ==> Remove one character from the start
        2.2. X[i ... j-1] ==> Remove one character from the end
        
        
Recurrence Relation - 
LPS[i......j] = 1                                               if i == j
              = 2 + LPS[i+1 ...... j-1]                         if X[i] == X[j]
              = max(LPS[i+1 ...... j], LPS[i ...... j-1])       if X[i] != X[j]
*/

public int LPSlength(String A, int start, int end){
    if(start > end) return 0;
    
    else if(start == end) return 1;
    
    else if( A.charAt(start) == A.charAt(end) ) return LPSlength(A, start+1, end-1) + 2;
    
    else return Math.max( LPSlength(A, start, end-1) , LPSlength(A, start+1, end));
}

/*
Time Complexity - Worst Case = O(2^n)
Worst case happens when there are no matching characters in the string. each call leads to two more calls.
*/


/*
DYNAMIC PROGRAMMING - BOTTOM UP
Using a matrix. 
The cells below the principal diognal are not filled in this process and are useless.
*/

public int LPSlength(String A){
    int n = A.length();
    
    int lps[][] = new int[n][n];
    
    /*
    The row index indicates the start index of the string under consideration.
    The column index indicates the end index of the string under consideration.
    So, when (i == j) the start index and the end index within the string are same so we are considering a single character at a 
    time. A single character is of length 1 and is considered to be a palindrome.
    */
    
    for(int i=0;i<n;i++) lps[i][i] = 1;
    
    //we have already considered substrings of length=1, now we start with length=2
    for(int strLen=2 ; strLen<=n ; strLen++){
        
        /*
        By setting the value for strLen, we have fixed the window size. The below code simply slides the window by incrementing 
        start and end index to explore all substrings of length=strLen
        */
         
        //setting a start position for a substring of length=strLen
        for(int start=0 ; start < n-strLen+1 ; start++){
            //settign an end position for a substring of length=strLen
            int end = start + strLen - 1;
            
            if(A.charAt(start) == A.charAt(end)){
                if(strLen == 2) lps[start][end] = 2;
                else = 2 + lps[start-1][end-1];
            }
            else lps[start][end] = Math.max(lps[start][end-1], lps[start+1][end]);
        }
        
    }
    
    return lps[0][n-1];
}

/*
Time Complexity ans Space Complexity - O(n^2)
*/


/*
DYNAMIC PROGRAMMING USING HASHMAP TO MEMOIZE RECURSION
*/

public int LPSlength(String A, int start, int end, HashMap<String,Integer> map){
   if(start > end) return 0;
   else if(start == end) return 1;
   
   String key = start+"|"+end;
   
   if(!map.containsKey(key)){
      if(A.charAt(start) == A.charAt(end)) map.put(key, LPSlength(A, start+1, end-1, map) + 2);
      else map.put(key, Math.max(LPSlength(A, start+1, end, map), LPSlength(A, start, end-1, map)));
   }
   return map.get(key);
}

/*
Time Complexity - O(n^2)
*/
