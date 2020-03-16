/*
https://www.techiedelight.com/0-1-knapsack-problem/
https://www.geeksforgeeks.org/0-1-knapsack-problem-dp-10/
*/

/*
In the 0/1 Knapsack Problem we must include an item entirely or exclude the item with an objective of maximizing profit/value constrainted by the size of the knapsack.

If the problem is not 0/1 knapsack and we are allowed to split items, we can take a greedy approach
we can chose items by sorting items according to profit per unit weight.
In doing so, only the last element or a single element will have to be split.
*/

/*RECURSION
For each item we have 2 choices - 
1. Include the item and recur for the remaining item. Here the capacity of the knapsack is reduced.
2. Exclude the item and recur for the remaining item. Capacity of knapsack remains the same.

*/

public int knapsack(int W, int item, int wt[], int val[]){
    //BASE CASE: we have exhausted all items or we have utilized the entire capacity of knapsack
    if(n==0 || W==0) return 0;
    
    //If the weight of current item exceeds the capacity of knapsack it can not be included - EXCLUDE ITEM
    else if(wt[n-1] > W) return knapsack(W, item-1, wt, val);
    
    //Try both - INCLUDE AND EXCLUDE - chose the best
    else return Math.max(knapsack(W, item-1, wt, val), val[item-1] + knapsack(W-wt[item-1], item-1, wt, val))
}


/*
Time Complexity - O(2^n)
For each item we are exploring the two choices(We include and exclude each item) to find the best.
*/

/*
DYNAMIC PROGRAMMING - BOTTOM UP APPROACH
*/

public int knapsack(int W, int item, int wt[], int val[]){
    int k[][] = new int[item+1][W+1];
    
    for(int i=0;i<=item;i++){
        for(int j=0)
    }
}


