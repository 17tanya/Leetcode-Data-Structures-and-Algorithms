/*
https://www.techiedelight.com/counting-paths-on-grid-to-reach-destination-cell/
https://www.geeksforgeeks.org/number-of-paths-with-exactly-k-coins/


pathCount(m, n, k):   Number of paths to reach mat[m][n] from mat[0][0] 
                      when cost = k

If (m == 0 and n == 0)
   return 1 if mat[0][0] == k else return 0
Else:
    pathCount(m, n, k) = pathCount(m-1, n, k - mat[m][n]) + 
                         pathCount(m, n-1, k - mat[m][n])
                         
Normal Recursion
*/

public int pathCount(int A[][], int row, int col, int cost){
    //Base Case
    if(cost<0) return 0;
    //Base Case --> Valid path
    else if(row==0 && col==0 && cost == A[0][0]) return 1;
    //Base Case
    else if(row==0 && col==0 && cost != A[0][0]) return 0;
    
    //First row --> Only one move --> Go left
    else if(row == 0) return pathCount(A, row, col-1, cost-A[row][col]);
    
    //First col --> Only one move --> Go up
    else if(col == 0) return pathCount(A, row-1, col, cost-A[row][col]);
    
    //Cosider both --> up and left
    else return pathCount(A, row-1, col, cost-A[row][col]) + pathCount(A, row, col-1, cost-A[row][col]);
}

//Memoized Recursion - using a hashmap

public int pathCount(int A[][], int row, int count, int cost, HashMap<String, Integer> map){
    if(cost<0) return 0;
    
    else if(row==0 && col==0 && cost == A[0][0]) return 1;
    
    else if(row==0 && col==0 && cost != A[0][0]) return 0;
    
    String key = row+"|"+col+"|"+cost;
    
    if(!map.containsKey(key)){
        else if(row == 0) map.put(key, pathCount(A, row, col-1, cost-A[row][col]));
        
        else if(col == 0) map.put(key, pathCount(A, row-1, col, cost-A[row][col]));
        
        else map.put(key, pathCount(A, row-1, col, cost-A[row][col]) + pathCount(A, row, col-1, cost-A[row][col]));
    }
    return map.get(key);
}

//Memoized recursion - using an 3d array

public int pathCount(int A[][], int row, int col, int cost, int dp[][]){
    if(cost<0) return 0;
    
    else if(row==0 && col==0 && cost == A[0][0]) return 1;
    
    else if(row==0 && col==0 && cost != A[0][0]) return 0;
    
    else if(dp[row][col][cost] != -1) return dp[row][col][cost];
    else{
        dp[row][col][cost] = pathCount(A, row-1, col, cost-A[row][col]) + pathCount(A, row, col-1, cost-A[row][col]);
        
        return dp[row][col][cost];
    }
}

//Time Complexity of DP solution - O(n*m*cost)
//This is Psedo-Polynomial Time 
