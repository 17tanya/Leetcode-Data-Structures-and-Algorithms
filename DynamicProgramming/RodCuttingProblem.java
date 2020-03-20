/*
https://www.techiedelight.com/rot-cutting/
https://www.geeksforgeeks.org/cutting-a-rod-dp-13/

Given a rod of length n inches and an array of prices that contains prices of all pieces of size smaller than n. Determine the maximum value obtainable by cutting up the rod and selling the pieces. For example, if length of the rod is 8 and the values of different pieces are given as following, then the maximum obtainable value is 22 (by cutting in two pieces of lengths 2 and 6)


length   | 1   2   3   4   5   6   7   8  
--------------------------------------------
price    | 1   5   8   9  10  17  17  20

And if the prices are as following, then the maximum obtainable value is 24 (by cutting in eight pieces of length 1)

length   | 1   2   3   4   5   6   7   8  
--------------------------------------------
price    | 3   5   8   9  10  17  17  20

*/

/*
RECURSION 
1. We are given an array of prices[].
2. Price of length 'i' is prices[i-1]
3. We partition the given length into two parts of length 'i' and 'n-i' and recurse further
4. We explore all partitions to find the one with maximum cost ie. i ranges from [1.....n]
*/

public int rodCut(int prices[], int n){

    if(n == 0) return 0;
    
    int maxCost = Integer.MIN_VALUE;
    
    /*
    Partition the given rod of length 'n' by - 
    1. i=1 (1, n-1)
    2. i=2 (2, n-2)
    3. i=3 (3, n-3)
    .
    .
    .
    .
    n. i=n (n,0)
    */
    
    for(int i=1 ; i<=n ; i++){
        int cost = prices[i-1] + rodCut(prices, n-i);
        
        if(maxCost < cost) maxCost = cost;
    }
    
    return maxCost;
    
}

//Time Complexity - O(n^n)


//DYNAMIC PROGRAMMING - BOTTOM UP APPROACH

public int rodCut(int prices[]){
    int rodLength = prices.length;
    
    //to store the maximum profit acheived from selling a rod of length 'i'
    int cost[] = new int[rodLength + 1];
    
    //consider rod of length 'rodLen'
    for(int rodLen=1; rodLen <= rodLength ; rodLen++){
        
        //Divide rod of length 'rodLen' into two rods of length - 'partition' and 'rodLen-partition' 
        for(int partition=1 ; partition <= rodLen ; partition++){
            /*
            Take the maximum cost after exploring all possible partitions.
            We are fixing the partition with length 'partition' and adding the best cost for the remaining part 
            ie. 'rodLen - partition'
            */
            cost[rodLen] = Math.max( cost[rodLen], price[partition - 1] + cost[rodLen - partition] );
        }
    }
    
    return cost[rodLength];
}

/*
Time Complexity - O(n^2)
*/
