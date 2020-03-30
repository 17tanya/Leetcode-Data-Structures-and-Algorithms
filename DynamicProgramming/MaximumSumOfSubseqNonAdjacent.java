/*
https://www.techiedelight.com/maximum-sum-of-subsequence-with-no-adjacent-elements

This problem is similar to the concept of 0/1 Knapsack Problem. Each element can be included or excluded. All elements in the subseq must be non-adjacent.
*/

//Recursion
//Include the current element and exclude the current element. Choose the max value.

public int maxSumOfSubseqNonAdj(int A[], int currIndex, int prevIndex, int n){

    //we have exhausted all elements of the array
    if(currIndex == n) return 0;
    
    //exclude the current element
    int exc = maxSumOfSubseqNonAdj(A, currIndex+1, prevIndex, n);
    
    int inc = 0;
    
    //include current element only if the it is not adjacent to the prev element considered in the subseq
    if(prevIndex+1 != currIndex) inc = maxSumOfSubseqNonAdj(A, currIndex+1, currIndex, n) + A[currIndex];
    
    //return the maximum sum possible
    return Math.max(inc, exc);
    
}

//Bottom Up Dynamic Programming
public int maxSumOfSubseqNonAdj(int A[]){
    int n = A.length;
    
    if(n == 1) return A[0];
    
    int sum[] = new int[n];
    
    sum[0] = A[0];
    sum[1] = Math.max(A[0], A[1]);
    
    for(int i = 2 ; i < n ; i++){
        int exc = sum[i-1];
        int inc = sum[i-2] + A[i];
        
        sum[i] = Math.max(exc, inc);
        
        //Usefull if both inc, exc and A[i] are negative --> choose the maximum
        sum[i] = Math.max(sum[i], A[i]);
    }
    
    return sum[n-1];
    
}

//Time and Space Complexity - O(n)

//We require only two variables to compute current sum and not entire array
public int maxSumOfSubseqNonAdj(int A[]){
    int n = A.length;
    
    if(n == 1) return A[0];
    
    int prevPrevSum = A[0];
    int prevSum = Math.max(A[0], A[1]);
    
    for(int i = 2 ; i < n ; i++){
        int exc = prevSum;
        int inc = prevPrevSum + A[i];
        
        int currSum = Math.max(inc, exc);
        
        //Usefull if both inc, exc and A[i] are negative --> choose the maximum
        currSum = Math.max(currSum, A[i]);
        
        //Update previous values
        prevPrevSum = prevSum;
        prevSum = currSum;
    }
    
    return prevSum;
}

//Time Complexity - O(n)
//Space Complexity - O(1)

