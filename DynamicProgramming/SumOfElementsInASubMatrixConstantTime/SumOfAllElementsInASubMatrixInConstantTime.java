/*
https://www.techiedelight.com/calculate-sum-elements-sub-matrix-constant-time/

We pre-process and maintain a separate matrix to return answers in constant time.
sum[i][j] represents the sum of all elements in the sub-matrix with top left corner at (0,0) and bottom right corner at (i,j)
Formula - 
    sum[i][j] = A[i][j] + sum[i-1][j] + sum[i][j-1] - sum[i-1][j-1]
    
Solution to the query - 
    let (p,q) --> top left corner
    and (r,s) --> bottom right corner

result = sum[r][s] - sum[r][q-1] - sum[p-1][s] + sum[p-1][q-1]
*/

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
    
    public int computeQueries(int p, int q, int r, int s){
        //(p,q) --> top left and (r,s) --> bottom right
        
        int result = sum[r][s];
        
        if(q-1 >= 0){
            result -= sum[r][q-1]
        }
        if(p-1 >= 0){
            result -= sum[p-1][s];
        }
        if(q-1 >= 0 && p-1 >= 0){
            result += sum[p-1][q-1];
        }
        
        return result;
        
    }
    
}

