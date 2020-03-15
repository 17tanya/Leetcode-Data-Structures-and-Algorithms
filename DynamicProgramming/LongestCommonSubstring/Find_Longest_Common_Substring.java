/*
https://www.geeksforgeeks.org/print-longest-common-substring/

Using the DP Approach-
We construct a matrix to keep track of maximal length suffix of all substrings. We can find the maximal value from this matrix and traverse from that cell diognally upwards till the cell value becomes 0.
The below approach has a space complexity = O(n*m)
*/

public void printLCStr(String A, String B, int n, int m){
    int lcs[][] = new int[n+1][m+1];
    
    //to store length of longest substring
    int maxLen = 0;
    
    //to store row and column values which point to the end of the longest substring
    int row = 0;
    int col = 0;
    
    for(int i=0 ; i<=n ; i++){
        for(int j=0 ; j<=m ; j++){
            if(i==0 || j==0) lcs[i][j] = 0;
            else if(A.charAt(i-1) == B.charAt(j-1)){
                lcs[i][j] = lcs[i-1][j-1] + 1;
                
                if(maxLen < lcs[i][j]){
                    maxLen = lcs[i][j];
                    row = i;
                    col = j;
                }
            }
            else lcs[i][j] = 0;
        }
    }
    
    if(maxLen == 0){
        System.out.println("No Common Substring");
    }
    else{
        String resStr = "";
        while(lcs[row][col]!=0){
            resStr = A.charAt(row-1)+resStr;
            row--;
            col--;
        }  
        System.out.println(resStr);
    }
}


/*
Space Optimized Approach 
1. The space used by the above solution can be reduced to O(2*n).
2. Variables used -  
    2.1 end - used to store ending point of the longest common substring in string X
    2.2 maxlen - used to store the length of the longest common substring.
3. How is Space Complexity reduced? 
Suppose we are at DP state when the length of X is i and length of Y is j, the result of which is stored in len[i][j].
Now if X[i-1] == Y[j-1], then len[i][j] = 1 + len[i-1][j-1], that is result of current row in matrix len[][] depends on values from previous row. 
Hence the required length of longest common substring can be obtained by maintaining values of two consecutive rows only, thereby reducing space requirements to O(2*n).
4. Print Longest Common Substring
    4.1 To print, we use variable end.
    4.2 When len[i][j] is calculated, it is compared with maxlen. If maxlen is less than len[i][j], then end is updated to i-1 to show that longest common substring ends at index i-1 in X and maxlen is updated to len[i][j].
    The longest common substring then is from index end – maxlen + 1 to index end in X.
5. How to switch between rows?
    5.1 A variable currRow is used to represent that either row 0 or row 1 of len[2][n] matrix is currently used to find the length. 
    5.2 Initially, row 0 is used as the current row for the case when the length of string X is zero. At the end of each iteration, the current row is made the previous row and the previous row is made the new current row.

*/

public void printStr(String A, String B){
    int n = A.length();
    int m = B.length();
    
    int lcs[][] = new int[2][m];
    
    //to store length of the longest common substring
    int maxLen = 0;
    
    //to store the last index of the longest common substring
    int end = 0;
    
    //to keep track of current row
    int currRow = 0;
    
    for(int i=0;i<=n;i++){
        for(int j=0;j<=m;j++){
            if(i==0 || j==0) lcs[currRow][j] = 0;
            else if(A.charAt(i-1) == B.charAt(j-1)){
                lcs[currRow][j] = lcs[1-currRow][j] + 1;
                if(maxLen < lcs[currRow][j]){
                    maxLen = lcs[currRow][j];
                    end = i-1;
                }
            }
            else lcs[currRow][j] = 0;
        }
        
        /*
        When currRow = 0, (1 - currRow) evaluates to 1 ==> currRow becomes 1
        when currRow = 1, (1 - currRow) evaluates to 0 ==> currRow becomes 0
        */
        currRow = 1 - currRow;
    }
    
    if(maxLen==0){
        System.out.println("No Common Substring");
    }
    else{
        System.out.println(X.substring(end-maxLen+1, end+1));
    }
}
/*
Time Complexity - O(n*m)
Space Complexity - O(n)
*/
