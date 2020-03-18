/*
https://www.geeksforgeeks.org/find-the-longest-path-in-a-matrix-with-given-constraints/

Find the longest path in a matrix with given constraints

Given a n*n matrix where all numbers are distinct, find the maximum length path (starting from any cell) such that all cells along the path are in increasing order with a difference of 1.

We can move in 4 directions from a given cell (i, j), i.e., we can move to (i+1, j) or (i, j+1) or (i-1, j) or (i, j-1) with the condition that the adjacent cells have a difference of 1.

Example:


Input:  mat[][] = {{1, 2, 9}
                   {5, 3, 8}
                   {4, 6, 7}}
Output: 4
The longest path is 6-7-8-9. 

Observation (Consider the matrix as an example)-
1. All values in the matrix are distinct and unique ranging from 1....n*n. There is AT MOST one possible direction from any cell.
2. A memoization table - dp[][] - is maintained and each time a subproblem is solved, the solution is stored there. 
3. dp[i][j] holds the value of the longest sequence in the sub-matrix starting at (i,j)
4. Since any cell can be the starting cell we check if that cell is explored or not and only then call the main function on that cell.
5. If a cell has been already explored ie. dp(cell)!=-1 it implies that the cell is a part of a larger sequence and exploring it again would result in the same answer which is already stored in that cell(dp[i][j]). 
6. The question says that we need to find the longest increasing sequence such that adjacent cells have a difference of 1. The most logical condition to use in our code seems to be - 
    Math.abs( mat[i][j] - mat[i+1][j] )
    but this is not correct. Consider the above example - If we use the above condition, after the cell (0,1) the control
    moves to (1,1) and then it will again move to (0,1) and so on...
    This will go infintely. 
    BE CAREFUL WITH THE TEST CONDITION. USE - (mat[i][j] + 1 == mat[i-1][j])
*/

public int longestPathFromACell(int mat[][], int dp[][], int n, int row, int col){
    if(i<0 || i>=n || j<0 || j>=n) return 0;
    
    else if(dp[row][col] != -1) return dp[row][col];
    
    int up = Integer.MIN_VALUE;
    int down = Integer.MIN_VALUE;
    int left = Integer.MIN_VALUE;
    int right = Integer.MIN_VALUE;
    
    //Move down
    if(row+1<n && (mat[row][col]+1 == mat[row+1][col]))
        down = 1 + longestPathFromACell(mat, dp, n, row+1, col);
    
    //Move Up
    else if(row-1>=0 && (mat[row][col]+1 == mat[row-1][col]))
        up = 1 + longestPathFromACell(mat, dp, n, row-1, col);
    
    //Move left
    else if(col-1>=0 && (mat[row][col]+1 == mat[row][col-1]))
        left = 1 + longestPathFromACell(mat, dp, n, row, col-1);
        
    //Move right
    else if(col+1<n && (mat[row][col]+1 == mat[row][col+1]))
        right = 1 + longestPathFromACell(mat, dp, n, row, col+1);
    
    //minimum value of the longest path will be 1
    dp[row][col] = Math.max(left, Math.max(right, Math.max(down, Math.max(up,1))));
    return dp[row][col];
}

public int longestPath(int mat[][]){
    int n = mat.length;
    int dp[][] = new int[n][n];
    for(int i=0;i<n;i++){
        for(int j=0;j<n;j++){
            dp[i][j] = -1;
        }
    }
    
    int result = 0;
    for(int i=0;i<n;i++){
        for(int j=0;j<n;j++){
            if(dp[i][j] == -1)
                longestPathFromACell(mat, dp, n, i, j);
            result = Math.max(result, dp[i][j]);
        }
    }
    
    return result;
}

/*
!!!NOT SURE!!!
Time Complexity of longestPathFromACell - O(n^2)
Matrix dimensions are (n*n) and we solve n^2 subproblems
*/
