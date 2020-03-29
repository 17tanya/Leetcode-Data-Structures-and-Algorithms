/*
https://www.techiedelight.com/longest-bitonic-subsequence/
https://youtu.be/TWHytKnOPaQ

Bitonic Subsequence - It is a subsequence which first increases and then decreases.(strictly)
This problem uses the same concept as the problem - Longest Increasing Subsequence

We maintain two arrays - I[] and D[]
For each index i in the input array,
    I[i] indicates the length of the longest increasing subsequence starting from 0 and ending at i
    D[i] indicates the length of the longest increasing subsequence starting from end of the input array and ending at i
    
*/

public int lengthOfLongestBitonicSubseq(int A[]){
    int n = A.length;
    
    int I[] = new int[n];
    int D[] = new int[n];
    
    //Initialization
    I[0] = 1;
    D[n-1] = 1;
    
    //starting from the start of the array
    for(int i=1 ; i < n ; i++){
        I[i] = 1;
        
        //for all elements occuring before A[i], check if they can be included to form an increasing subseq ending at i
        for(int j=0 ; j < i ; j++){
            if(A[j] < A[i]){
                //Chose max value
                I[i] = Math.max(I[i], I[j]+1);
            }
        }
    }
    
    //starting from the end of the array
    //Increasing subseq from end --> decreasing subseq from start
    for(int i=n-2 ; i >= 0 ; i--){
        D[i] = 1;
        
        //for all elements occuring after A[i], check if they can be included to form an increasing subseq ending at i
        for(int j=n-1 ; j > i ; j--){
            if(A[i] > A[j]){
                D[i] = Math.max(D[i], D[j]+1);
            }
        }
    }
    
    //Find length of longest bitonic subseq
    int maxLen = 0;
    for(int i=0 ; i < n ; i++){
        //subtract 1 --> i is included twice, both in I and D
        if(maxLen < I[i] + D[i] - 1) maxLen = I[i] + D[i] - 1;
    }
    
    return maxLen;
}

//Time Complexity - O(n^2)
//Space Complexity - O(n)


