/*
https://www.geeksforgeeks.org/matrix-chain-multiplication-dp-8/

NOTE - Similar to Longest Plaindromic Subsequence


Given a sequence of matrices, find the most efficient way to multiply these matrices together. The problem is not actually to perform the multiplications, but merely to decide in which order to perform the multiplications.

example, suppose A is a 10 × 30 matrix, B is a 30 × 5 matrix, and C is a 5 × 60 matrix. Then,

    (AB)C = (10×30×5) + (10×5×60) = 1500 + 3000 = 4500 operations
    A(BC) = (30×5×60) + (10×30×60) = 9000 + 18000 = 27000 operations.
    

Given an array p[] which represents the chain of matrices such that the ith matrix Ai is of dimension p[i-1] x p[i]. We need to write a function MatrixChainOrder() that should return the minimum number of multiplications needed to multiply the chain.

  Input: p[] = {40, 20, 30, 10, 30}   
  Output: 26000  
  There are 4 matrices of dimensions 40x20, 20x30, 30x10 and 10x30.
  Let the input 4 matrices be A, B, C and D.  The minimum number of 
  multiplications are obtained by putting parenthesis in following way
  (A(BC))D --> 20*30*10 + 40*20*10 + 40*10*30

  Input: p[] = {10, 20, 30, 40, 30} 
  Output: 30000 
  There are 4 matrices of dimensions 10x20, 20x30, 30x40 and 40x30. 
  Let the input 4 matrices be A, B, C and D.  The minimum number of 
  multiplications are obtained by putting parenthesis in following way
  ((AB)C)D --> 10*20*30 + 10*30*40 + 10*40*30

  Input: p[] = {10, 20, 30}  
  Output: 6000  
  There are only two matrices of dimensions 10x20 and 20x30. So there 
  is only one way to multiply the matrices, cost of which is 10*20*30


OPTIMAL SUBSTRUCTURE-
1. In a chain of matrices of size n, we can place the first set of parenthesis in n-1 ways.
2. let the chain be ABCD, then there are 3 ways to place first set of parenthesis outer side: (A)(BCD), (AB)(CD) and (ABC)(D).
3. When we place a set of parenthesis, we divide the problem into subproblems of smaller size.
4. Minimum number of multiplication needed to multiply a chain of size n = Minimum of all n-1 placements (these placements create subproblems of smaller size)

OVERLAPPING SUBPROBLEMS
*/

//RECURSIVE CODE
public int matrixChain(int dims[], int start, int end){

    //BASE CONDITION - We have only one matrix, which can not be multiplied ==> cost = 0
    if(start == end) return 0;
    
    int minCost = Integer.MAX_VALUE;
    
    /*
    The current window under consideration is [start...end]
    k partitions the range [start...end] into 2 ==> [start....k] and [k+1...j]
    These two partitions are the subproblems.
    Since we need to find the partition with minimum cost, we try all values of k
    ie. All possible partitions possible
    */
    for(int k = start ; k < end ; k++){
        int cost = matrixChain(dims, start, k) + 
                   matrixChain(dims, k+1, end) + 
                   (dims[start-1] * dims[k] * dims[end]);
                   /*
                   Cost of multiplication of two matrices - number of arithemetic computations involved.
                   */
                   
        if(cost < minCost) minCost = cost;
    }
    
    return minCost;
}

//DYNAMIC PROGRAMMING - BOTTOM UP
/*
1. In the recursive code, (start == end) was our base condition and building from that we considered matrices in continuous window of size = 2, 3, ... n-1.
2. To consider all continuous matrices of a given window size, the window is slid by one.
3. Window of size=2 is a subproblem of window of size=3 and so on...
3. A matrix is used to store solutions of the subproblems.
4. mat[i][j] indicates the minimum cost required to multiply matrices [i...j]
5. Only the values above the principal diaognal will be computed. The rest will remain unused.
6. Final solution will be mat[1][n-1]
*/

public int matrixChain(int dims[]){
    int n = dims.length;
    
    int cost[][] = new int[n][n];
    
    //we will use cost[][] from (1,1) because the number of input matrices is (n-1)
    
    //cost of multiplying 1 matrix is 0
    //This is equivalent to the leaf nodes in the recursion tree
    for(int i=1;i<n;i++) cost[i][i] = 0;
    
    /*
    After the leaf nodes of the recursion tree are computed, the control moves to the next level above the leaf nodes.
    In that level the window size=2 and it keeps increasing by 1 as we move up each level in the recursion tree. 
    So, gap start at 2 and will increase up to (n-1)
    */
    for(int gap = 2 ; gap < n ; gap++){
        //fixing the start and end of the current gap
        for(int start = 1 ; start < n-gap+1 ; start++){
            int end = start+gap-1;
            
            cost[start][end] = Integer.MAX_VALUE;
            
            /*
            The current window under consideration is [start...end]
            k partitions the range [start...end] into 2 ==> [start....k] and [k+1...j]
            These two partitions are the subproblems.
            Since we need to find the partition with minimum cost, we try all values of k
            ie. All possible partitions possible
            */
            for(int k=start ; k < end ; k++){
                
                int currCost = cost[start][k] + cost[k+1][end] + (dims[start-1] * dims[k] * dims[end]);
                
                //since all possible partitions will be explored we must keep track of the minimum cost
                if(cost[start][end] > currCost) cost[start][end] = currCost;
            }
        }
    }
    
    return cost[1][n-1];
    
}


