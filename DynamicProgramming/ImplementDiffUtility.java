/*
https://www.techiedelight.com/implement-diff-utility/

This is same as the Longest Common Subsequence Problem.
We find the longest common subsequence and for the remaining sequence we print the diff.

*/

//Recursion Using lookup table
//X is the initial string, Y is the final string, m = len(X), n = len(Y), dp[][] --> table filled during LCS length computation

public void printDiff(String X, String Y, int m, int n, int dp[][]){
    //when both characters match we have nothing to do. Print the character and skip it from both the strings. --> m-1 & n-1
    if(m>0 && n>0 && (X.charAt(m-1) == Y.charAt(n-1))){
        //Notice the function is called first
        printDiff(X, Y, m-1, n-1, dp);
        System.out.println(" " + X.charAt(m-1));
    }
    
    //When characters from Y are not present in X
    else if(n > 0 && (m == 0 || (dp[m][n-1] >= dp[m-1][n]))){
        diff(X, Y, m, n-1, dp);
        System.out.println("+" + Y.charAt(n-1));
    }
    
    //when characters from X are not present in Y
    else if(m > 0 && (n == 0 || dp[m][n-1] < dp[m-1][n])){
        diff(X, Y, m-1, n, dp);
        System.out.println("-" + X.charAt(m-1));
    }
}

/*
Time and Space Complexity - O(n*m)
*/
