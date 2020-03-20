/*
https://www.geeksforgeeks.org/coin-change-dp-7/

Given a value N, if we want to make change for N cents, and we have infinite supply of each of S = { S1, S2, .. , Sm} valued coins, how many ways can we make the change? The order of coins doesn’t matter.

For N = 4 and S = {1,2,3}, there are four solutions: {1,1,1,1},{1,1,2},{2,2},{1,3}. So output should be 4.
For N = 10 and S = {2, 5, 3, 6}, there are five solutions: {2,2,2,2,2}, {2,2,3,3}, {2,2,6}, {2,3,5} and {5,5}. So the output should be 5.

OPTIMAL SUBSTRUCTURE
Here S[] = array of coin denomination
     m = number of coins (S.length)
     n = Given sum(coin) whose change is to be calculated
     
To count the total number of solutions, we can divide all set solutions into two sets.
1) Solutions that do not contain mth coin (or Sm). ==> count(S[], m-1, n) ==> EXCLUDE CURRENT COIN
2) Solutions that contain at least one Sm. ==> count(S[], m, n-S[m]) ==> INCLUDE CURRENT COIN
    count(S[], m, n) = count(S[], m-1, n) + count(S[], m, n-Sm)
    
*/

//RECURSIVE SOLUTION

public int coinChangeWays(int coins[], int index, int coinVal){

    if(coinVal == 0) return 1;
    
    else if(coinVal < 0) return 0;
    
    else if(coinVal > 0 && index <= 0)
    
    else return coinChangeWays(coins, index-1, coinVal) + coinChangeWays(coins, index, coinVal-coins[index-1]);
}

//DYNAMIC PROGRAMMING - BOTTOM UP APPROACH

public int coinChangeWays(int coins[], int numOfCoins, int coinVal){

    //ways[i][j] indicates the number of ways in which a value=j can be changed(formed) by considering coins from [1...i]
    int ways[][] = new int[numOfCoins+1][coinVal+1];
    
    for(int i=0 ; i <= numOfCoins ; i++) ways[i][0] = 1;
    
    for(int i=1 ; i <= numOfCoins ; i++){
        for(int j=1 ; j <= coinVal ; j++){
        
            //include current coin if [currentVal(j)-currentCoinValue(coins[i-1]) >= 0]
            //Basically check, if it is possible to include coin or not
            if(j >= coins[i-1]){
                //include coin + exclude coin
                ways[i][j] = ways[i-1][j] + ways[i][j - coins[i-1]]; 
            }
            //exclude current coin - value(j) remains same
            else ways[i][j] = ways[i-1][j]; 
        }
    }
    
    return ways[numOfCoins][coinVal];
}

/*
Time Complexity - O(numOfCoins * coinVal)
Space Complexity - O(numOfCoins * coinVal)
*/

//DYNAMIC PROGRAMMING - SPACE OPTIMIZED BOTTOM APPROACH
public int coinChangeWays(int coins[], int coinVal){
    int ways[] = new int[coinVal+1];
    ways[0] = 1;
    
    for(int coinIndex=0 ; coinIndex < coins.length ; coinIndex++){
    
        //currval starts from coins[coinIndex] because for values smaller than coins[coinIndex],
        //ways[coinIndex] remains the same ==> excluding coinIndex
        
        for(int currVal = coins[coinIndex] ; currVal <= coinVal ; currVal++){
            
            //including coins[coinIndex]
            ways[coinIndex] += ways[currVal - coins[coinIndex]];
            
        }
    }
    
    return ways[coinVal];
}

/*
Time Complexity - O(numOfCoins * coinVal)
Space Complexity - O(coinVal)
*/
