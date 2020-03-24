/*
https://www.techiedelight.com/maximum-subarray-problem-kadanes-algorithm/

We maintain 2 variables - 
1. maxEndingHere - stores the maximum sum of the sub-array ending at the current index
    1.1. Include the current element in the sub-array sum 
    1.2. Start a new sub-array from this element ie. sum = current element
2. maxSoFar - stores the maximum possible sum of all the sub-arrays
*/

public int kadane(int A[]){
    int n = A.length;
    
    if(n == 0) return 0;
    
    int maxEndingHere = A[0];
    int maxSoFar = A[0];
    
    for(int i=1 ; i < n ; i++){
        //include current element in the sub-array sum
        maxEndingHere+=A[i];
        
        //check if starting a new sub-array gives a greater sum than the current sum
        maxEndingHere = Math.max(maxEndingHere, A[i]);
        
        //update the overall max-sum
        maxSoFar = Math.max(maxEndingHere, maxSoFar);
    }
    
    return maxSoFar;
}

//Time Complexity - O(n)
//Traverses the array only once
