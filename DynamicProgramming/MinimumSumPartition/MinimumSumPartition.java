/*

https://www.geeksforgeeks.org/partition-a-set-into-two-subsets-such-that-the-difference-of-subset-sums-is-minimum/
https://www.techiedelight.com/minimum-sum-partition-problem/

Given a set of integers, the task is to divide it into two sets S1 and S2 such that the absolute difference between their sums is minimum.

If there is a set S with n elements, then if we assume Subset1 has m elements, Subset2 must have n-m elements and the value of abs(sum(Subset1) – sum(Subset2)) should be minimum.

Example:


Input:  arr[] = {1, 6, 11, 5} 
Output: 1
Explanation:
Subset1 = {1, 5, 6}, sum of Subset1 = 12 
Subset2 = {11}, sum of Subset2 = 11        



RECURSION
The recursive approach is to generate all possible sums from all the values of array and to check which solution is the most optimal one.
To generate sums we - 
1. either include the i’th item in set 1 or 
2. don’t include the i'th item in set 1, i.e., include it in set 2.

*/

public int minPartition(int A[], int current, int sumSet1, int totalSum){
    if(current == 0) return Math.abs((totalSum - sumSet1) - sumSet1);
    
    return Math.min( minPartition(A, current-1, sumSet1+A[i-1], totalSum), minPartition(A, current-1, sumSet1, totalSum) );
}

public int findMinPartition(int A[]){
    int sum = 0;
    int n = A.length;
    
    for(int i=0;i<n;i++) sum+=A[i];
    
    return minPartition(A, n, 0, sum);
}

/*
Time Complexity - O(n^2)
We explore two choices at each recursion
*/

/*DYNAMIC PROGRAMMING - BOTTOM UP - PUSEDOPOLYNOMIAL

The task is to divide the set into two parts. 
We will consider the following factors for dividing it. 
Let 
  dp[i][j] = true         if some subset from 1st to i'th element has a sum equal to j
           = false        otherwise
    
    i ranges from {1..n}
    j ranges from {0..(sum of all elements)}  

So      
    dp[i][j]  will be true if 
    1) The sum j is achieved including i'th item
    2) The sum j is achieved excluding i'th item.

Let sum of all the elements be S.  

To find Minimum sum difference, we have to find j such 
that Min{sum - j*2  : dp[n][j]  == true } 
    where j varies from 0 to sum/2

The idea is, sum of S1 is j and it should be closest
to sum/2, i.e., 2*j should be closest to sum.

*/


public int minPartition(int A[]){
    int sum = 0;
    int n = A.length;
    
    //calculating total sum of all elements
    for(int i=0;i<n;i++) sum+=A[i];
    
    boolean T[][] = new boolean[n+1][sum+1];
    
    //when sum=0 the result is true
    for(int i=0;i<=n;i++) T[i][0] = true;
    
    //when the input is an empty set the result is false
    for(int j=1;j<=sum;j++) T[0][j] = false;
    
    for(int i=1;i<=n;i++){
        for(int j=1;j<=sum;j++){
            //exclude the element if it is greater than the rqd sum
            if(A[i-1] > j) T[i][j] = T[i-1][j];
            
            //consider both - include and exclude
            T[i][j] = (T[i-1][j] || T[i-1][j-A[i-1]]);
            
        }
    }
    
    /*
    Now my each cell T[i][j] of the table indicates if it is possible to attain a sum=j when considering the elements form 
    [1...i]. If T[i][j] is true it implies that some elements from [1..i] form a subset to sum up to j.
    Thus, T[n][j] indicates if it is possible to attain a sum of j after considering all elements in the input.
    */
    
    int diff = Integer.MAX_VALUE;
    //Find largest j to minimize the difference
    for(int j=sum/2 ; j>=0 ;j--){
        if(T[n][j]){
            diff = sum - (j*2);
            break;
        }
    }
    
    return diff;

}
/*
Time Complexity - O(n * sum)
*/

