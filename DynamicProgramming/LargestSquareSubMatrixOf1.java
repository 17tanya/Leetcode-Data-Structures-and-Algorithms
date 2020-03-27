/*
https://www.techiedelight.com/find-size-largest-square-sub-matrix-1s-present-given-binary-matrix/

*/

//Bottom up DP

public int maxSizeOfSquareSubMatrix(int A[][]){
    int n = A.length;
    int m = A[0].length;
    
    //dp[i][j] indicates the side-length of the largest square sub-matrix formed when (i,j) is the bottom-right corner of that sub-matrix
    int dp[][] = new int[n][m];
    
    int maxSize = 0;
    
    for(int i=0;i<n;i++){
        for(int j=0;j<m;j++){
        
            dp[i][j] = A[i][j];
            
            //if we are not in the first row or column and the current element is 1, then check for sub-matrix
            if(i>0 && j>0 && A[i][j]==1){
                //dp[i-1][j] --> Top cell
                //dp[i][j-1] --> left cell
                //dp[i-1][j-1] --> top left cell
                //we chose the minimum value because it is the LIMITING VALUE and we can not have a square sub-matrix
                //of length greater than this minimum value
                dp[i][j] = Math.min(dp[i-1][j], dp[i][j-1], dp[i-1][j-1]) + 1;
            }
            
            maxSize = Math.max(maxSize, dp[i][j]);
        }
    }
    
    return maxSize;
    
}

/*
Time and Space Complexity - O(n*m)
*/
