/*
https://www.techiedelight.com/partition-problem/
https://www.geeksforgeeks.org/partition-problem-dp-18/

To Do - Print the Partitions

Given a sequence of integers, we need to find if they can be divided into two subsets of equal sum.

arr[] = {1, 5, 11, 5}
Output: true 
The array can be partitioned as {1, 5, 5} and {11}

arr[] = {1, 5, 3}
Output: false 
The array cannot be partitioned into equal sum sets.

Main points to solve the problem - 
1. If the sum of all integers of the inout sequence is not even ==> The sequence can not be divided into two subsets of equal sum.
2. If the sum of all integers of teh input seq is even ==> The sequence can be divided into two subsets of equal sum ==>
sum of each subset will be (totalSum/2) ==> Find if a subset sums up to (totalSum/2)

RECURSIVE SOLUTION
We have two choices include or exclude the current item from the subset
1. Include current item in the subset and recur for other elements with sum's value reduced by current element
2. Exclude current item and recur for remaining elements with sum's value as it is.

The below solution uses array's prefixes
*/

public boolean isPartition(int A[], int sum, int current){
    //we found the subset
    if( sum == 0 ) return true;
    
    //we have exhausted all elements and the sum is still not 0 ie. partition not possible
    else if( n==0 && sum!=0 ) return false;
    else{
    
        //include the current item in the subset
        boolean include = isPartition(A, sum-A[current-1], current-1);
        
        //exclude the current item
        boolean exclude = isPartition(A, sum, current-1);
        
        //does any of the two choices help us attain a partition?
        return (include || exclude);
    }
}

public boolean solvePartition(int A[]){
    int n = A.length;
    int sum = 0;
    
    for(int i=0;i<n;i++) sum+=A[i];
    
    //partition not possible
    if(sum % 2 != 0) return false;
    
    else return isPartition(A,sum/2,n);
    
}

/*
Time Complexity - O(2^n)
For each element we are exploring two choices - inclusion and exclusion
*/


/*
DYNAMIC PROGRAMMING - BOTTOM UP APPROACH
The problem is similar to the knapsack problem, where we need to choose items to maximize profit and use maximum capacity of the knapsack. 
Similarly In this problem, we are supposed to chose items. But the constraint/Objective is different. We need to choose items such that the sum of these items is equal to (totalSum)/2.
*/

public boolean solvePartition(int A[]){
    int n = A.length;
    int sum = 0;
    
    for(int i=0;i<n;i++) sum+=A[i];
    
    //partition not possible
    if(sum % 2 != 0) return false;
    
    else return isPartition(A,sum/2,n);
    
}

//Notice the code is very similar to knapsack problem code
public boolean isPartition(int A[], int sum, int n){
    boolean T[][] = new boolean[n+1][sum+1];
    
    for(int i=0;i<=n;i++) T[i][0] = true;
    
    for(int i=1;i<=n;i++){
        for(int j=1;j<=sum;j++){
            if(A[i-1] > j) T[i][j] = T[i-1][j];
            else T[i][j] = (T[i-1][j] || T[i-1][j-A[i-1]]);
        }
    }
    return T[n][sum];
}

/*
Time Complexity - O(n*totalSum)
Space Complexity - O(n*totalSum)
Number of subproblems is dependent on
1. number of elmenets in the array ==> n
2. the subset under consideration --(depends on)--> the target sum(totalSum/2) --(depends on)--> the total sum of all elements
*/

//RECUSRION USING MEMOIZATION
//The below approach uses array's suffixes

public boolean isPartition(int A[], int current, int sum, HashMap<String,Boolean> map){
    
    //we found the subset
    if( sum == 0 ) return true;
    
    //we have exhausted all elements and the sum is still not 0 ie. partition not possible
    else if( n==0 && sum!=0 ) return false;
    
    String key = current+"|"+sum;
    
    if(!map.containsKey(key)){
        //include the current item in the subset
        boolean include = isPartition(A, current-1, sum-A[current-1], map);
        
        //exclude the current item
        boolean exclude = isPartition(A, current-1, sum, map);
        
        //does any of the two choices help us attain a partition?
        map.put(key, (include || exclude));
    }
    return map.get(key);
}

public boolean solvePartition(int A[]){
    int n = A.length;
    int sum = 0;
    HashMap<String, Boolean> map = new HashMap<String,Boolean>();
    for(int i=0;i<n;i++) sum+=A[i];
    
    //partition not possible
    if(sum % 2 != 0) return false;
    
    else return isPartition(A,sum/2,n);
}

/*
Time Complexity - O(n*totalSum)
*/





