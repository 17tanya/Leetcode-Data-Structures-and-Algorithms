class Solution {
    public void rotate(int[][] matrix) {
        int n = matrix.length;
        for(int row=0 ; row<n/2 ; row++){ //number of cycles in matrix
            for(int col=row ; col<n-1-row ; col++){
                int temp = matrix[col][n-1-row];
                matrix[col][n-1-row] = matrix[row][col];
                matrix[row][col] = matrix[n-col-1][row];
                matrix[n-col-1][row] = matrix[n-1-row][n-1-col];
                matrix[n-1-row][n-1-col] = temp;
            }
        }
    }
}