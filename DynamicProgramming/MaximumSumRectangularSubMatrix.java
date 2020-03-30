/*
https://youtu.be/yCQN096CwWM
https://github.com/mission-peace/interview/blob/master/src/com/interview/dynamic/SubRectangularMatrixWithMaximumSum.java

We Use Kadane's Algorithm to solve this problem
*/

class MaxSumRectangularSubMatrix{
    class Result{
        int maxSum;
        int leftBound;
        int rightBound;
        int lowBound;
        int upBound;
    }

    class KadaneResult{
        int sum;
        int start; //will indicate the start row of the max sum sub matrix 
        int end; //will indicate the end row of the max sum sub matrix
    }
    
    
    public Result maxSum(int A[][]){ 
        int rows = A.length;
        int cols = A[0].length;
        
        int kadaneCol[] = new int[rows];
        Result result = new Result();
        
        //we set a new start boundary for the sub-matrix --> this is the left boundary
        for(int left=0 ; left < cols ; left++){
        
            //Clear the previous values
            Arrays.fill(kadaneCol,0); //O(n)
            
            //the left boundary is set, but we move the right boundary towards the end of the matrix
            for(int right=left ; right < cols ; right++){
                
                //add values from the newly shifted right boundary
                //This is done because we are applying Kadane's Algorithm which works on 1D arrays
                //We pass an array(to the kadane's algorithm) containing cummulative sum of each row(which makes the array 1D)
                //Kadane's algo finds the max sum subarray from this array --> This is nothing but the max sum sub matrix
                //left and right are set in this function and Kadane's algo helps to fix the up and low.
                for(int i=0;i<rows;i++){
                    kadaneCol[i] += A[i][right];
                }
                
                //Update the current result 
                KadaneResult kadaneResult = kadane(kadaneCol);
                if(kadaneResult.sum > result.maxSum){
                    result.maxSum = kadaneResult.sum;
                    result.leftBound = left;
                    result.rightBound = right;
                    result.upBound = kadaneResult.start;
                    result.lowBound = kadaneResult.end;
                }
            }
        }
        
        return result;
        
    }
    
    /*
    Time Complexity - O((col^2)*row) --> cubic
    Space Complexity - O(row)
    */
    
    public KadaneResult kadane(int A[]){ //to return the maxSum, start and end in linear time
        int n = A.length;
    
        if(n == 0) return 0;
        
        KadaneResult kr = null;
        
        int maxEndingHere = A[0];
        int maxSoFar = A[0];
        int maxStart = 0;
        int start = 0;
        int maxEnd = 0;
        
        for(int i=1 ; i < n ; i++){
            //include current element in the sub-array sum
            maxEndingHere+=A[i];
        
            //check if starting a new sub-array gives a greater sum than the current sum
            if(maxEndingHere < A[i]){
                start = i;
                maxEndingHere = A[i];
            }
        
            //update the overall max-sum
            if(maxSoFar < maxEndingHere){
                maxSoFar = maxEndingHere;
                maxEnd = i;
                maxStart = start;
            }
        }
    
        return new KadaneResult(maxSoFar, maxStart, maxEnd);
    }
    
}
