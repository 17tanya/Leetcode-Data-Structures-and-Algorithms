/*
https://www.geeksforgeeks.org/optimal-strategy-for-a-game-dp-31/

NOTE - Similar to Longest Palindromic Subsequence

Consider a row of n coins of values v1 . . . vn, where n is even. We play a game against an opponent by alternating turns. In each turn, a player selects either the first or last coin from the row, removes it from the row permanently, and receives the value of the coin. Determine the maximum possible amount of money we can definitely win if we move first.

8, 15, 3, 7 : The user collects maximum value as 22(7 + 15)
…….User chooses 7.
…….Opponent chooses 8.
…….User chooses 15.
…….Opponent chooses 3.
Total value collected by user is 22(7 + 15)


There are two choices:
1. The user chooses the ith coin with value Vi: The opponent either chooses (i+1)th coin or jth coin. The opponent intends to choose the coin which leaves the user with minimum value.
i.e. The user can collect the value Vi + min(F(i+2, j), F(i+1, j-1) )
2. The user chooses the jth coin with value Vj: The opponent either chooses ith coin or (j-1)th coin. The opponent intends to choose the coin which leaves the user with minimum value.
i.e. The user can collect the value Vj + min(F(i+1, j-1), F(i, j-2) )

F(i, j)  represents the maximum value the user can collect from 
         i'th coin to j'th coin.

    F(i, j)  = Max(Vi + min(F(i+2, j), F(i+1, j-1) ), 
                   Vj + min(F(i+1, j-1), F(i, j-2) )) 
Base Cases
    F(i, j)  = Vi           If j == i
    F(i, j)  = max(Vi, Vj)  If j == i+1
*/


public int optimalStratergy(int coins[]){
    int n = coins.length;
    
    //to store solutions to sub-problems ==> considering coins in varying ranges
    int T[][] = new int[n][n];
    
    //values lying below the principal diaognal in T are not used.
    //T[i][j] indicates the maximum amount Player1 can earn if coins under consideration are from i....j
    //we maintain a variable 'gap' to indicate the number of coins under consideration
    //gap will range from 1...n
    //gap will also be used like a window, if gap=3 the elements under consideration will be coins[0...2], coins[1...3], ...
    
    //when gap=1, only one coin is considered and Player1 will pick that coin itself.
    //T[i][i] ==> single coin is considered
    for(int i=0;i<n;i++) T[i][i] = coins[i];
         
    /*
    when gap=2, two coins are considered. Player1 has the first chance and will chose the maximum of the two.
    for gap=2, matrix filled will be-
         T[0][1] ==> coins[0],coins[1]
         T[1][2] ==> coins[1],coins[2]
         ...
    we can easily see gap is being slid by one each time.
    */
    for(i=0;i<n-1;i++) T[i][i+1] = Math.max(coins[i], coins[i+1]);
         
    //for gap>=3 we use a nested for loop as here we will use the recurrence relation to fill the table.
    for(int gap=3 ; gap<=n ; gap++){
         for(int start=0 ; start<n-gap+1 ; gap++){
                  int end = start+gap-1;
                  
                  int subprob1 = T[start+2][end];
                  int subProb2 = T[start+1][end-1];
                  int subProb3 = T[start][end-2];
                  
                  T[start][end] = Math.max( coins[start] + Math.min( subprob1, subProb2 ),
                                           coins[end] + Math.min( subProb2, subProb3 ) );
         }
    }
    
    //We start at the diaognal and move towards the upper right corner, as the size of the subproblem increases
    return T[0][n-1];
    
}
