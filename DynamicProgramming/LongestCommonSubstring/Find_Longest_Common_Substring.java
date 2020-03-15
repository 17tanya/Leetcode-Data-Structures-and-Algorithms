/*

Using the DP Approach-
We construct a matrix to keep track of maximal length suffix of all substrings. We can find the maximal value from this matrix and traverse from that cell diognally upwards till the cell value becomes 0.

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
        System.out.println("No Substring");
    }
    
    String resStr = "";
    while(lcs[row][col]!=0){
        resStr = A.charAt(row-1)+resStr;
         row--;
         col--;
    }
    
    System.out.println(resStr);
}
