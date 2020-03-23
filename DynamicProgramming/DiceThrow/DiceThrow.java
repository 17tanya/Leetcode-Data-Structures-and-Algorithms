/*
https://www.geeksforgeeks.org/dice-throw-dp-30/

Given n dice each with m faces, numbered from 1 to m, find the number of ways to get sum X. X is the summation of values on each face when all the dice are thrown.

Let the function to find X from n dice is: Sum(m, n, X)
The function can be represented as:
Sum(m, n, X) = Finding Sum (X - 1) from (n - 1) dice plus 1 from nth dice
               + Finding Sum (X - 2) from (n - 1) dice plus 2 from nth dice
               + Finding Sum (X - 3) from (n - 1) dice plus 3 from nth dice
                  ...................................................
                  ...................................................
                  ...................................................
              + Finding Sum (X - m) from (n - 1) dice plus m from nth dice

So we can recursively write Sum(m, n, x) as following
Sum(m, n, X) = Sum(m, n - 1, X - 1) + 
               Sum(m, n - 1, X - 2) +
               .................... + 
               Sum(m, n - 1, X - m)
               
Observe Overlapping Problems
Sum(6, 3, 8) = Sum(6, 2, 7) + Sum(6, 2, 6) + Sum(6, 2, 5) + 
               Sum(6, 2, 4) + Sum(6, 2, 3) + Sum(6, 2, 2)

To evaluate Sum(6, 3, 8), we need to evaluate Sum(6, 2, 7) which can 
recursively written as following:
Sum(6, 2, 7) = Sum(6, 1, 6) + Sum(6, 1, 5) + Sum(6, 1, 4) + 
               Sum(6, 1, 3) + Sum(6, 1, 2) + Sum(6, 1, 1)

We also need to evaluate Sum(6, 2, 6) which can recursively written
as following:
Sum(6, 2, 6) = Sum(6, 1, 5) + Sum(6, 1, 4) + Sum(6, 1, 3) +
               Sum(6, 1, 2) + Sum(6, 1, 1)
..............................................
..............................................
Sum(6, 2, 2) = Sum(6, 1, 1)
*/

//DYNAMIC PROGRAMMING - BOTTOM UP APPROACH

public long findWays(int dices, int faces, int sum){
    //HANDLING EXTREMES
    /*
    Value of sum is too less.
    1. If it is less than the number of dices we return 0.
       Minimum value for each dice is 1 ==> minimum sum of all faces of all dices == #dices
    2. If it the sum is equal to the number of dices ==> there is exactly 1 way to get to this sum
    */
    if(sum <= dices) return (sum == dices);
    
    /*
    Value of sum is too huge.
    max value of all faces of all dices = dices*faces
    */
    if(sum >= dices*faces) return (sum == dices*faces);
    
    //ways[i][j] indicate the number of ways to attain a sum 'j' when we consider dices [1....i]
    //0th row and 0th column are not used
    long ways[][] = new long[dices+1][sum+1];
    
    for(int col=0 ; col <= Math.min(sum,faces) ; col++) ways[1][col] = 1;
    
    for(int d=2 ; d <= dices ; d++){
        for(int s=1 ; s <= sum ; s++){
        
            //to calculate ways[d][s] we explore all faces
            for(int f=1 ; f <= Math.min(s-1,faces) ; f++){
                /*
                since the current dice 'd' gives a value 'f', we need to find #ways to form a sum of (s-j) without considering
                the current dice 'd'
                */
                ways[d][s]+=ways[d-1][s-f];
            }
        }
    }
    
    return ways[dices][sum];
}

/*
Time Complexity - O(dices*sum*faces)
Space Complexity - O(dices*sum)
*/
