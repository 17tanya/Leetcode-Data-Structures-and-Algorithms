/*
https://www.techiedelight.com/find-maximum-sum-submatrix-in-given-matrix/
First read - https://www.techiedelight.com/calculate-sum-elements-sub-matrix-constant-time/
Extenstion of the problem - Calculate Sum of All Elements in a Submatrix in constant time

In this problem we are supposed to find maximum sum of a submatrix of size k*k

*/

class Point{
    int x;
    int y;
    
    Point(int i, int j){
        x = i;
        y = j;
    }
    
}

class SubMatrix{
    int sum[][];
    
    public void fillSumMatrix(int A[][]){
        int n = A.length;
        int m = A[0].length;
        
        sum = new int[n][m];
        
        sum[0][0] = A[0][0];
        
        //pre-process first row
        for(int j=1 ; j < m ; j++){
            sum[0][j] = sum[0][j-1] + A[0][j];
        }
        
        //pre-process first col
        for(int i=1 ; i < n ; i++){
            sum[i][0] = sum[i-1][0] + A[i][0];
        }
        
        //compute rest of the sum-matrix
        for(int i=1 ; i < n ; i++){
            for(int j=1 ; j < m ; j++){
                sum[i][j] = A[i][j] + sum[i-1][j] + sum[i][j-1] - sum[i-1][j-1];
            }
        }
    }
    
    public Point maxSumSubmatrix(int k){ //returns the bottom right corner of the maximum sum submatrix
        //dimensions of the required submatrix --> (k*k)
        
        //dimensions of main matrix
        int n = sum.length;
        int m = sum[0].length;
        
        int currSum = 0;
        int maxSum = 0;
        Point p = null;
        
        for(int i=k-1 ; i < n ; i++){
            for(int j=k-1 ; j < m ; j++){
                
                currSum = sum[i][j];
                
                if(i-k >= 0){
                    result -= sum[i-k][j];
                }
                if(j-k >= 0){
                    result -= sum[i][j-k];
                }
                if(i-k >= 0 && j-k >= 0){
                    result += sum[i-k][j-k];
                }
                
                if(maxSum < currSum){
                    maxSum = currSum;
                    p = new Point(i,j);
                }
            }
        }
        return p;
        
    }
    
}


