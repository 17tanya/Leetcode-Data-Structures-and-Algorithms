/*
https://www.geeksforgeeks.org/longest-common-substring-dp-29/
https://www.techiedelight.com/longest-common-substring-problem/

A simple solution is to one by one consider all substrings of first string and for every substring check if it is a substring in second string. Keep track of the maximum length substring. There will be O(m^2) substrings and we can find whether a string is subsring on another string in O(n) time (See KMP). So overall time complexity of this method would be 
O(n * m^2)


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
*/
