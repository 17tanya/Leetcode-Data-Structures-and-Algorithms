/*
https://github.com/mission-peace/interview/blob/master/src/com/interview/stackqueue/MaximumHistogram.java
https://github.com/mission-peace/interview/blob/master/src/com/interview/dynamic/MaximumRectangularSubmatrixOf1s.java

We use the max-rectangular area in a histogram here. Explanantion of problem - 
    Input - An array --> each element represents the height of a bar in the histogram
    Ouput - maximum possible area in the histogram

We use the above code to find the maximal size rectangular sub-matrix of 1s.
We maintain a 1D array of all values from a row and keep adding the values from the previous rows when the value!=0
Then we apply the histogram code to find the max area rectangle.
*/

class Solution{

    public int maxRectSubmatrix(int A[][]){ //Time Complexity - O(n*m)
        int n = A.length;
        int m = A[0].length;
    
        int maxArea = 0;
    
        //temporary array used for computation with maxHistogram
        //we keep adding column values as we move down the rows but reset if value in A = 0
        int temp[] = new int[m];
    
        for(int i=0;i<n;i++){
            
            for(int j=0;j<m;j++){
                if(A[i][j] == 0) temp[j] = 0;
                else temp[j]+=A[i][j];
            }
            
            maxArea = Math.max(maxHistogram(temp), maxArea);
        }
    
        return maxArea;
    }

    public int maxHistogram(int A[]){ //Time Complexity - O(n)
        Stack<Integer> stk = new Stack<Integer>();
    
        int maxArea = 0;
        int area = 0;
    
        //i indicates the length of the histogram's base considered so far
        int i = 0;
    
        while(i < A.length){
            // we can push a new value when stack is empty or the value on top of stack is smaller or equal to the current value
            //this ensures all values in the stack are in (non-decreasing order)ascending order
            if(stk.isEmpty() || A[stk.peek()] <= A[i]) stk.push(i++);
        
            else{
                int top = stk.pop();
            
                //this is the smallest element in the entire array -->
                //we can safely multiply it with the length of the histogram base --> max base length = i
                if(stk.isEmpty()) area = A[top] * i;
            
                //maximum base length = (i - stk.peek() - 1)
                //this means for the range (stk.peek(), i) both exclusive, the minimum(height of the bar) = A[top]
                else area = A[top] * (i - stk.peek() - 1);
            }
            maxArea = Math.max(maxArea, area);
        }
    
        //the stack might not be empty so we apply the same logic to the remainimg elements of the stack
        while(!stk.isEmpty()){
            int top = stk.pop();
        
            if(stk.isEmpty()) area = A[top]*i;
            else area = A[top] * (i - stk.peek() - 1);
        
            maxArea = Math.max(maxArea, area);
        }
    
        return maxArea;
    }

}
