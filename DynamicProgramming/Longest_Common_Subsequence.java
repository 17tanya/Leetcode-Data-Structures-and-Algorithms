/*
LCS is the problem of finding longest common subsequence that is present in given two seqeunces in the SAME ORDER.
ie. Find the longest seqeunce which can be obtained from the first original sequence by deleting some items and from the second original sequence by deleting other items.

example - 
X : ABCBDAB
Y : BDCABA

Length of LCS is 4. LCS are BDAB, BCAB, BCBA

NAIVE APPROACH
check if all subsequence of X[1...n] are also a subsequence of Y[1...m].
Number of subsequences of length 1 = nC1
Number of subsequences of length 2 = nC2
.
.
.
Number of subsequences of length n = nCn

We know, nC0 + nC1 + .... + nCn = 2^n

number of subsquences of length ATLEAST 1 AND ATMOST n = (2^n)-1

Time required of check if a subsequence is also a subsequence of Y = m
Time Complexity of Naive Approach = O(n*(2^n))



RECURSIVE APPROACH - uses Optimal Substructure Property
We will be traversing the strings backwards ==> considering their PREFIXES
We can see, to compute LCS(X[1...n],Y[1...m]) 2 cases arise - 
    1. X[1...n],Y[1...m] end in same element ie, x[n] == Y[m]
       LCS(X[1...n],Y[1...m]) = LCS(X[1...n-1],Y[1...m-1]) + (X[n] or Y[m])
       
    2. X[1...n],Y[1...m] do not end in same element ie, x[n] != Y[m]
       LCS(X[1...n],Y[1...m]) = max {LCS(X[1...n-1],Y[1...m]), LCS(X[1...n],Y[1...m-1])}
    Understand this property with example - 
    X : ABCBDAB
    Y : BDCABA
    The LCS of the two sequences either ends with B or does not.
    Case 1: If LCS ends with B, it can not end with A and so we can remove A from Y, so the problem becomes LCS(X[1...n],Y[1...m-1])
    Case 2: If LCS does not end with B, we can remove B from X, so the problem becomes LCS(X[1...n-1],Y[1...m])
*/

//Recursive Solution

public int LCSlength(String a, String b, int n, int m){
    if(n==0 || m==0) return 0;
    //+1 is for the current match of characters
    if(a.charAt(n-1)==b.charAt(m-1)) return LCSlength(a,b,n-1,m-1) + 1;
    
    return Math.max(LCSlength(a,b,n,m-1),LCSlength(a,b,n-1,m));
}
