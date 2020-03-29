/*
https://www.techiedelight.com/increasing-subsequence-with-maximum-sum/

Basically the same as the problem - Longest Increasing Subsequence - Instead of storing the max length, we store the max sum. 

The problem is to find the maximum sum possible after considering all increasing subsequences.
*/

public int maxSumOfIncSubseq(int A[]){
    int n = A.length;
    
    //sum[i] indicates the sum of the increasing subsequence formed ending at i
    int sum[] = new int[n];
    
    sum[0] = A[0];
    
    for(int i = 1 ; i < n ; i++){
        for(int j = 0 ; j < i ; j++){
            if(A[j] < A[i] && sum[i] < sum[j]){
                sum[i] = sum[j];
            }
        }
        sum[i] += A[i];
    }
    
    int maxSum = 0;
    for(int i=0 ; i < n ; i++){
        if(maxSum < sum[i]) maxSum = sum[i];
    }
    
    return maxSum;
    
}

/*
Time Complexity - O(n^2)
Space Complexity - O(n)
*/
