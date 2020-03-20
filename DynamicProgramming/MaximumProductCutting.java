/*
https://www.geeksforgeeks.org/maximum-product-cutting-dp-36/

Given a rope of length n meters, cut the rope in different parts of integer lengths in a way that maximizes product of lengths of all parts. You must make at least one cut. Assume that the length of rope is more than 2 meters.

Examples:

Input: n = 2
Output: 1 (Maximum obtainable product is 1*1)

Input: n = 3
Output: 2 (Maximum obtainable product is 1*2)

Input: n = 4
Output: 4 (Maximum obtainable product is 2*2)

Input: n = 5
Output: 6 (Maximum obtainable product is 2*3)

Input: n = 10
Output: 36 (Maximum obtainable product is 3*3*4)


OPTIMAL SUBSTRUCTURE
Try all possible cuts and divide current length into 2 parts. Then recursivley call the 'dividing function' on one of the parts to find the best solution.

OVERLAPPING SUBPROBLEMS

RECURRENCE - 
maxProd(n) = max( i*(n-i) , i*maxProd(n-i) )    for i in [1....n-1]
*/

//RECURSIVE SOLUTION
public int maxProd(int rodLength){

    //minimum length of 2 is required if we are supposed to make atleast 2 partitions
    if(rodLength==0 || rodLength==1) return 0;
    
    int maxP = 0;
    
    //trying partitions of all lengths [1...length-1]
    for(int len=1 ; len < rodLength ; len++){
    
        /*
        For the current partition length which is the best?
        leaving it here, making this the last division into 2 ==> len * (rodLength-len) OR
        exploring further ==> len * maxProd(rodLength-len) Here only one of the partition is broken down(rodLength-len) further
        because the other part will be considered eventually because of the loop.
        */
        int currMaxP = Math.max( len*(rodLength-len), len*maxProd(rodLength-len) );
        
        //to chose the best out of all the partitions
        maxP = Math.max(maxP, currMaxP);
        
    }
    
    return maxP;
}


//DYNAMIC PROGRAMMING - MEMOIZATION

public int maxProd(int rodLength){

    //maxP[i] indicates the maximum product obtained if length=i
    int maxP[] = new int[rodLength+1];
    
    maxP[0] = 0;
    maxP[1] = 0;
    
    for(int len=2;len<=rodLength;len++){
        int currMaxP = 0;
        for(int j=1 ; j<len ; j++){
            currmaxP = Math.max(currMaxP, Math.max( len*(len-j), j*maxP[len-j] ) );
        }
        maxP[len] = currMaxP;
    }
    return maxP[rodLength];
}

/*
Time - O(n^2)
*/

//TRICK SOLUTION
/*
If we see some examples of this problems, we can easily observe following pattern.
The maximum product can be obtained be repeatedly cutting parts of size 3 while size is greater than 4, keeping the last part as size of 2 or 3 or 4. For example, n = 10, the maximum product is obtained by 3, 3, 4. For n = 11, the maximum product is obtained by 3, 3, 3, 2.
*/

public int maxProd(int n){ 
      
    // n equals to 2 or 3 must be handled 
    // explicitly 
    if (n == 2 || n == 3) return (n-1); 
  
    // Keep removing parts of size 3  
    // while n is greater than 4 
    int res = 1; 
    while (n > 4) 
    { 
        n -= 3; 
          
        // Keep multiplying 3 to res 
        res *= 3;  
    } 
      
    // The last part multiplied by  
    // previous parts 
    return (n * res);  
} 

