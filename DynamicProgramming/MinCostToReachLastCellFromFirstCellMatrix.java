/*
https://www.techiedelight.com/find-minimum-cost-reach-last-cell-matrix-first-cell/

From the first cell reach the last cell. Each cell has a cost. Minimize total cost.
Allowed moves - right and bottom --> from (i,j) valid moves are --> (i+1,j) and (i,j+1)
(Further) - consider diaognal moves as well


Optimal substructure - 
cost to reach(i,j) = cost[i][j] + min( cost to reach(i-1,j), cost to reach(i,j-1) )

Overlapping subproblems

Recursive Solution
*/

public int minCost(int cost[][], int row, int col){
  //row or col has reached an invalid value
  if(row==0 || col==0) return Integer.MAX_VALUE;
  
  //we are at the first cell --> return its value
  else if(row==1 && col==1) return cost[row-1][col-1];
  
  else return cost[row-1][col-1] + Math.min(minCost(cost, row-1, col), minCost(cost, row, col-1));
}

//DP Bottom up approach

public int minCost(int cost[][]){
    int n = cost.length;
    int m = cost[0].length;
    
    //pathCost[i][j] denotes the minimum cost to reach the cell(i,j) form the first cell(0,0)
    int pathCost[][] = new int[n][m];
    
    for(int i=0 ; i < n ; i++){
        for(int j=0 ; j < m ; j++){
            pathCost[i][j] = cost[i][j];
            
            //to reach any cell in the first row the only possible way is from left
            if(i==0 && j>0){
                pathCost[i][j]+=pathCost[i][j-1];
            }
            
            //to reach any cell in the first column the only possible way is from top 
            else if(j==0 && i>0){
                pathCost[i][j]+=pathCost[i-1][j];
            }
            
            //consider the path cost from the top and left --> choose minimum
            else{
                pathCost[i][j]+=Math.min(pathCost[i-1][j],pathCost[i][j-1]);
            }
        }
    }
    
    //stores the minimum path cost
    return pathCost[n][m];
}

/*
Time and Space Complexity - O(n*m)

*/
