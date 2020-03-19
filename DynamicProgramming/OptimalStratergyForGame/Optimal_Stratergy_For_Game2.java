/*
https://www.geeksforgeeks.org/optimal-strategy-for-a-game-set-2/

Similar to MIN-MAX

In the previous code, we used 4 recursive calls. Here we minimze the number of calls to two.
1. The logic remains the same. Previously we maintained 'gap' and slid it to consider all the coins.
2. Here instead of generating all possible moves we use the min-max statergy.
  Consider gap=3 and items under consideration are [20,10,2] and their sum=32
  Player1 has 2 choices - chose 20 or 2, but how to find which choice will maximize Player1's points
  We consider both - chose 20 and call recur on [10,2] or chose 2 and recur on [20,10]
  Now, it is player2's turn, who also wants to maximize his points and so he will chose the max of the two values
  if he is offered [10,2] he chooses 10
  if he is offered [20,10] he chooses 20
  Now, knowing this information Player1 must make a move which minimizes the maximum value Player2 gets.
  THE ABOVE IS DONE USING - THE TOTAL SUM OF THE CURRENT WINDOW ie. ITEMS UNDER CONSIDERATION.
  ie. MAX( SUM(20,10,2) - 10, ==> Player1 choses 20 and player2 chooses 10
            SUM(20,10,2) - 20 ) ==> Player1 chooses 2 and Player2 chooses 20
  
  2.1. Player2 choses max out of two adjacent elements. The remaining is what Player1 gets.
  
*/

public int calcSum(int coins[]){
    int sum = 0;
    int n = coins.length;
    int dp[][] = new int[n][n];
    
    for(int i=0;i<n;i++) sum+=coins[i];
        
    for(int i=0;i<n;i++){
        for(int j=0;j<n;j++){
            dp[i][j] = -1;
        }
    }
    return game(coins, dp, 0, n-1, sum);
}

public int game(int coins[], int dp[][], int i, int j, int sum){
    if(i+1==j) return Math.max(coins[i], coins[j]);
    
    if(dp[i][j]!=-1) return dp[i][j];
    
    dp[i][j] = Math.max(sum-game(coins, dp, i+1, j, sum-coins[i]),
                        sum-game(coins, dp, i, j-1, sum-coins[j]));
    return dp[i][j];
}
    
    
