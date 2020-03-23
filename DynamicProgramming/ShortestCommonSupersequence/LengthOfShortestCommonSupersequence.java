/*
https://www.techiedelight.com/shortest-common-supersequence-introduction-scs-length/

Shortest Common Supersequence is the problem of finding the the shortest sequence Z, such that sequences X and Y are subsequences of Z.

X: ABCBDAB
Y: BDCABA
Supersequences: ABCBDCABA, ABDCABDAB, ABDCBDABA

OPTIMAL SUBSTRUCTURE OF SUBPROBLEMS -
Let length(X) = n and length(Y) = m
1. If X[n] == Y[m]
   We find the SCS of shortened subsequences X[n-1] & Y[m-1] and append the common element at it's end.
   SCS(X[n], Y[m]) = SCS(X[n-1],Y[m-1]) + X[n]
2. If X[n] != Y[m]
   The shortest supersequence will either end in X[n] or Y[m]. So, we explore both and consider the one giving us the shortest
   supersequence.
   SCS(X[n], Y[m]) = min( SCS(X[n-1],Y[m]) + X[n], SCS(X[n],Y[m-1])+Y[m] )
   
OVERLAPPING SUBPROBLEMS
*/

//RECURSION
public int scsLength(String X, String Y, int n, int m){
    if(n==0 || m==0) return (n+m);
    else if(X.charAt(n-1) == Y.charAt(m-1)) return scsLength(X, Y, n-1, m-1) + 1;
    else return Math.min(scsLength(X, Y, n, m-1), scsLength(X, Y, n-1, m))+1;
}

/*
Time Complexity - O(2^(n+m))
Worst case occurs when none of the characters of the two sequences match - each call leads to two more calls
*/

//MEMOIZED RECURSION
public int scsLength(String X, String Y, int n, int m, HashMap<String, Integer>){
    if(n==0 || m==0) return n+m;
    
    String key = n+"|"+m;
    
    if(!map.containsKey(key)){
        if(X.charAt(n-1) == Y.charAt(m-1)) map.put(key, scsLength(X, Y, n-1, m-1, map)+1);
        else map.put(key, Math.min(scsLength(X, Y, n, m-1, map), scsLength(X, Y, n-1, m, map))+1);
    }
    return map.get(key);
}
/*
Time Complexity and Space Complexity - O(n*m)
*/

//BOTTOM UP DYNAMIC PROGRAMMING
/*
SCS[i][j] = i                                               if j==0
          = j                                               if i==0
          = SCS[i-1][j-1] + 1                               if(X[i] == Y[j])
          = Math.min(SCS[i-1][j], SCS[i][j-1]) + 1          if(X[i] != Y[j])
*/

public int scsLength(String X, String Y){
    int n = X.length();
    int m = Y.length();
    int T[][] = new int[n+1][m+1];
    
    for(int i=0 ; i <=n ; i++) T[i][0] = i;
    for(int j=0 ; j <= m ; j++) T[0][j] = j;
    
    for(int i=1;i<=n;i++){
        for(int j=1;j<=m;j++){
            if(X.charAt(i-1) == Y.charAt(j-1)) T[i][j] = T[i-1][j-1]+1;
            else T[i][j] = Math.min(T[i-1][j], T[i][j-1])+1
        }
    }
    
    return T[n][m];
}

/*
Time Complexity & Space Complexity - O(n*m)
*/


public int scsLength(String X, String Y){
    int n = X.length();
    int m = Y.length();
    int T[][] = new int[2][m+1];
    
    for(int i=0 ; i <=m ; i++) T[0][i] = i;
    
    for(int i=1;i<=n;i++){
        for(int j=0;j<=m;j++){
            if(X.charAt(i-1) == Y.charAt(j-1)) T[i % 2][j] = T[(i-1) % 2][j-1]+1;
            else T[i % 2][j] = Math.min(T[(i-1) % 2][j], T[i % 2][j-1])+1
        }
    }
    
    return T[n % 2][m];
}

/*
Time Complexity - O(n*m)
Space Complexity - (2*m)
*/

