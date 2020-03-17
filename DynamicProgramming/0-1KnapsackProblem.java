/*
https://www.techiedelight.com/0-1-knapsack-problem/
https://www.geeksforgeeks.org/0-1-knapsack-problem-dp-10/

To Do - Print the selected items to be put in the kanpsack
*/

/*
In the 0/1 Knapsack Problem we must include an item entirely or exclude the item with an objective
of maximizing profit/value constrainted by the size of the knapsack.

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

public int knapsack(int W, int items, int wt[], int val[]){
    int k[][] = new int[items+1][W+1];
    
    for(int item=0 ; item <= items ; item++){
        for(int w=0 ;w <= W ; w++){
            //Base Case: when items = 0 or capacity of knapsack = 0
            if(item==0 || w==0) k[item][w] = 0;
            
            //if weight of current item exceeds the knapsack capacity we EXCLUDE current item
            //Simply put the value which was calculated with respect to previous item and same knapsack capacity
            else if(wt[item-1] > w) k[item][w] = k[item-1][w];
            
            //If the weight of item does not exceed knapsack's capacity - we explore 2 choices
            //1. INCLUDE current item - we find the cell that represents a state which has been calculated with respect to the 
            //previous item and knapsack's capacity reduced by current item's weight ie, k[item-1][w-wt[item-1]]
            //2. EXCLUDE current item - value same as the one which was calculated
            //with respect to previous item and same knapsack capacity
            else k[item][w] = Math.max( k[item-1][w], val[item-1] + k[item-1][w-wt[item-1]]);
        }
    }
    
    return k[items][W];
}

/*
Time and Space Complexity - O(items * knapsackCapacity)
*/

//RECURSION USING MEMOIZATION

public int knapsack(int W, int item, int wt[], int val[], HashMap<String,Integer> map){
    if(W==0 || item==0) return 0;
    
    String key = item + "|" + W;
    int include = 0;
    int exclude = 0;
    if(!map.containsKey(key)){
        if(wt[item] < W) include = v[n] + knapsack(W-w[n],item-1,wt,val,map);
        exclude = knapsack(W,item-1,wt,val,map);
        
        map.put(key, Math.max(include,exclude));
    }
    return map.get(key);
}

//Time Complexity - O(n*W)
//where n - number of items and W - capacity of knapsack


