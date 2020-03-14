//GFG - https://www.geeksforgeeks.org/longest-common-subsequence-dp-4/
//TechieDelight - https://www.techiedelight.com/longest-common-subsequence/

/*
LCS is the problem of finding longest common subsequence that is present in given two seqeunces in the SAME ORDER.
ie. Find the longest seqeunce which can be obtained from the first original sequence by DELETING some items and from the second original sequence by deleting other items.
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
/*
Worst Case Time Complexity of the above solution is = O(2^(m+n))
Worst Case is when there is NO common character in the two sequences.
example - X : AAAA and Y : BBBBBB
In the recursive solution, some subproblems are computed again ==> OVERLAPPING SUBPROBLEMS ==> DP CAN BE USED!
*/


//MEMOIZED RECURSIVE APPROACH - TOP DOWN APPROACH
/*Top Down approach - Breaking Bigger problem into smaller subproblem and calculate and store the result in case it is needed in future*/
class LCS{
    HashMap<String,Integer> map = new HashMap<String,Integer>();
    
    public int LCSlength(String a, String b, int n, int m){
        if(n==0 || m==0) return 0;
        
        String key = n+"|"+m;
        if(!map.containsKey(key)){
            if(a.charAt(n-1)==b.charAt(m-1))  map.put(key, LCSlength(a,b,n-1,m-1)+1);
    
            else map.put(key, Math.max(LCSlength(a,b,n,m-1),LCSlength(a,b,n-1,m)));
        }
        
        return map.get(key);
    }
}

/*
Time Complexity - O(n*m)
Number of unique subproblems = n*m
Other redundant recursive calls take constant time
Space Complexity - O(n*m)
*/

//BOTTOM-UP APPROACH 
/*Calculate smaller value first and work towards the bigger problem.*/
class LCS{
    public int LCSlength(String a, String b, int n, int m){
        int lcs[][] = new int[n+1][m+1];
        
        //first column of all rows is 0
        for(int i=0;i<=n;i++) lcs[i][0] = 0;
        //first row of all coumns is 0
        for(int j=0;j<=m;j++) lcs[0][j] = 0;
        
        for(int i=1;i<=n;i++){
            for(int j=1;j<=m;j++){
                /*
                when a[i]==b[j], we can simply look for the last answer and add one to it. 
                One is added to reflect that the current pair of characters are a match.
                last answer is found by not considering the current elements ie. going to the upper dioagnal cell
                */
                if(a.charAt(i-1)==b.charAt(j-1)){
                    lcs[i][j] = lcs[i-1][j-1]+1;
                }
                
                /*
                when a[i]!=b[j],
                We can discard one character at a time from both the sequence to see if there is a match or not.
                let us assume,
                Sequence after dropping the last character = oldSeq
                Sequence kept as it is ie. Sequence which includes the current character = newSeq
                
                Since we have added a new character in the newSeq, there is a possibilty that the last character of oldSeq matches the newly added last char of newSeq.
                */
                else{
                    lcs[i][j] = Math.max(lcs[i-1][j], lcs[i][j-1]);
                }
            }
        }
        
        return lcs[n][m];
    }
}
/*
Time Complexity - 
We are computing each of the (n*m) subproblems = O(n*m)
Space Complexity -
Storing results of all subproblems = O(n*m)
*/
