/*
https://www.geeksforgeeks.org/longest-common-substring-dp-29/
https://www.techiedelight.com/longest-common-substring-problem/

A simple solution is to one by one consider all substrings of first string and for every substring check if it is a substring in second string. Keep track of the maximum length substring. There will be O(m^2) substrings and we can find whether a string is subsring on another string in O(n) time (See KMP). So overall time complexity of this method would be 
O(n * m^2)


Recursive Approach
The recursive method for finding longest common substring is:
Given A and B as two strings, let m as the last index for A, n as the last index for B.

    if A[m] == B[n] increase the result by 1.
    if A[m] != B[n] :
      compare with A[m -1] and B[n] or
      compare with A[m] and B[n -1] 
    WITH RESULT RESET TO 0.
!!!!!!!!!    NOTE     !!!!!!!!!
In Longest Common Subsequence we do not make the result 0. Here we want a contiguous string and so we have to set
the result to 0 when the characters DO NOT match which is not the case with Longest Common Subsequence.
*/
class LCS{
    String A;
    String B;
    LCS(String a, String b){
        A = a;
        B = b;
    }
    
    public int LCStr(int n, int m, int count){
        //we have exhausted one of the strings return the length of the suffix-substring found so far.
        if(n==0 || m==0) return count;
        
        //if the last characters match check for a continous substring in the remaining strings A[1..n-1] and B[1..m-1] 
        if(A.charAt(n-1) == B.charAt(m-1)){
            count = LCStr(n-1, m-1, count+1);
        }
        
        /*
        set count to the maximum length of substring found so far. This piece of code is not in an "else" statement
        because even when the last characters match which is indicated by the "if" above it does not gurantee that the match
        will give us the maximum length substring.
        
        So, we need to explore other combinations of strings to be find the maximal length substring even when there is a match
        of the last characters.
        A[i] == B[j] does not gurantee that A[i-1] == B[j-1] and even when A[i-1] == B[j-1] is true it is not guranteed to be
        maximum length substring.
        Also, A[i] != B[j] does not imply (A[i] != B[j-1] or A[i-1] != B[j]). This is possible and might even lead to maximal
        length substring.
        when A[i] != B[j] we need to explore other options.
        */
        count = Math.max(count,Math.max(LCstr(n, m-1,0), LCStr(n-1, m, 0)));
        return count;
        
        //Notice - Only the call within the "if" block increments count all other calls simply reset count to 0
        //This is the difference b/w substring and subsequnce problem
    }
}



/*
Dynamic Programming
find length of the longest common suffix for all substrings of both strings and store these lengths in a table.

The longest common suffix has following optimal substructure property.
1. If last characters match, then we reduce both lengths by 1
    LCS(X, Y, m, n) = LCS(X, Y, m-1, n-1) + 1           if X[m-1] = Y[n-1]
2. If last characters do not match, then result is 0
    LCSuff(X, Y, m, n) = 0                              if (X[m-1] != Y[n-1])

Now we consider suffixes of different substrings ending at different indexes.
The maximum length Longest Common Suffix is the longest common substring.
LCSubStr(X, Y, m, n) = Max(LCSuff(X, Y, i, j)) where 1 <= i <= m and 1 <= j <= n 
*/

public int LCStr(String a, String b, int n, int m){
        int lcs[][] = new int[n+1][m+1];
        int result = 0;
        
        for(int i=0;i<=n;i++){
                for(int j=0;j<=m;j++){
                        if(i==0 || j==0) lcs[i][j] = 0;
                        
                        else if(a.charAt(i-1)==b.charAt(j-1)){
                                lcs[i][j] = lcs[i-1][j-1] + 1;
                                result = Math.max(result,lcs[i][j]);
                        }
                        else lcs[i][j] = 0;
                }
        }
        
        return result;
        
}


/*
Time Complexity = O(n*m)
Number of subproblems = n*m
Space Complexity = O(n*m)

Understanding the significance of matrix, consider the strings "cat" and "tata"
     1  2  3  4     
     t  a  t  a
1 c  0  0  0  0 
2 a  0  1  0  1 
3 t  1  0  2  0

consider row 3 and column 1 ==> comparing "t" and "t" is the direct comparision
But we are actually comparing the PowerSet of "cat" and "t" ie, {"c","a","t","ca","at","cat"} is compared with {"t"}

We first check if a[i] == b[j]
if the above is true we add 1 to the value of the upper left cell which holds the length of longest substring in a[1..i-1] and 
b[1..j-1].
In the subsequence problem, we consider the top and left cells when current characters do not match because the subsequnce can be
broken. 
For a continous substring we can not consider left and top cells as they indicate subsequnces.

*/
