/*
https://www.geeksforgeeks.org/longest-increasing-path-matrix/

Given a matrix of N rows and M columns. From m[i][j], we can move to m[i+1][j], if m[i+1][j] > m[i][j], or can move to m[i][j+1] if m[i][j+1] > m[i][j]. The task is print longest path length if we start from (0, 0).

Examples:

Input : N = 4, M = 4
        m[][] = { { 1, 2, 3, 4 },
                  { 2, 2, 3, 4 },
                  { 3, 2, 3, 4 },
                  { 4, 5, 6, 7 } };
Output : 7
Longest path is 1 2 3 4 5 6 7.

Input : N = 2, M =2
        m[][] = { { 1, 2 },
                  { 3, 4 } };
Output :3
Longest path is either 1 2 4 or 
1 3 4.

*/

//RECURSION USING MEMOIZATION - DYNAMIC PROGRAMMING

public int longestPathLength(int dp[][], int mat[][], int n, int m, int row, int col){
    if(dp[row][col]==0){
        //if the current call has been made, it means this cell is valid and should be considered in the sequence
        //The current cell is valid, but it might not be possible to move right/down so store the minimum result
        int result = 1;
        
        //if we can go down in the matrix and if that cell satisfies the property - explore
        if(row+1<n && mat[row][col] < mat[row+1][col])
            result = 1 + longestPathLength(dp, mat, n, m, row+1, col);
        
        //if we can go right in the matrix and if that cell satisfies the property - explore
        if(col+1<n && mat[row][col] < mat[row][col+1])
            result = Math.max(result, 1 + longestPathLength(dp, mat, n, m, row, col+1) );
            
        //NOTE - A CALL IS MADE ONLY WHEN THE NEXT CELL(RIGHT/BOTTOM) SATISFIES THE PROPERTY ==>
        //EACH CALL INDICATES A VALID CELL
        
        dp[row][col] = result;
    }
    return dp[row][col];
}

/*
Time Complexity - O(n*m)
Number of subproblems - n*m
*/
