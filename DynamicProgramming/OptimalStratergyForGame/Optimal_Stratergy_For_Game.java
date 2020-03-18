/*
https://www.geeksforgeeks.org/optimal-strategy-for-a-game-dp-31/

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
    
    int T[][] = new int[n][n];
    
    //values lying below the principal diaognal in T are not used.
    //T[i][j] indicates the coins under consideration are from i....j
    //we maintain a variable 'gap' to indicate the number of coins under consideration
    //gap will range from 0...n
    //gap will also be used like a window, if gap=3 the elements under consideration will be coins[0...2], coins[1...3], ...
    
    //when gap=0 only 
}
