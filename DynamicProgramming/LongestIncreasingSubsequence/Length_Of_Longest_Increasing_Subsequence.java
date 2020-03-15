/*
https://www.techiedelight.com/longest-increasing-subsequence-using-dynamic-programming/
https://www.geeksforgeeks.org/longest-increasing-subsequence-dp-3/
NOTE - This problem can be solved in a better way in O(nlogn) time using BINARY SEARCH

The Longest Increasing Subsequence (LIS) problem is to find the length of the longest subsequence of a given sequence such that all elements of the subsequence are sorted in increasing order.
example - length of LIS for {10, 22, 9, 33, 21, 50, 41, 60, 80} is 6 and LIS is {10, 22, 33, 50, 60, 80}
*/

//RECURSIVE APPROACH
/*
Two possibilities, explore both- 
1. Include current element in LIS if current element is greater than the previous element, then recur for remaining elements.
2. Exclude current element from LIS if current element is smaller or greater than previous element, then recur for remaining elements.

Basically, condition to be checked ==> A[current] > A[prev]
    if(true) INCLUDE A[current] in LIS and recur
    EXCLUDE A[current] in LIS and recur
the condition must be satisfied to INCLUDE the element however the same is not true for EXCLUDE
We EXCLUDE each element irrespective of whether it is greater than A[prev] in order to explore all possibilties.
*/

public int LISlength(int A[], int current, int n, int prev){
    // base case : nothing more to explore
    if(i == n) return 0;
    
    //By calling the function with the same value of prev as in the original call, we are excluding the current element 
    int exc = LISlength(A, current+1, n, prev);
    
    int inc = 0;
    
    //Here ........A[prev], A[current] form an increasing subsequence, we call the function again by including A[current]
    //A[current] is included becuase we have changed the value of prev to A[current]
    if(A[current] > A[prev]){
        inc = LISlength(A, current+1, n, A[current]) + 1;
    }
    
    return Math.max(exc,inc);
    
}


/*
DYNAMIC PROGRAMMING APPROACH - BOTTOM UP
Let arr[0..n-1] be the input array and L(i) be the length of the LIS ending at index i such that arr[i] is the last element of the LIS.
Then, L(i) can be recursively written as:
L(i) = 1 + max( L(j) ) where 0 < j < i and arr[j] < arr[i]; or
L(i) = 1, if no such j exists.
To find the LIS for a given array, we need to return max(L(i)) where 0 < i < n.

How does this work?
1. In the above logic we assume each element of the array is at the end of an increasing subsequence.
2. To find the length of this increasing subsequence we consider all elements before the current element and also the
   respective lengths of increasing subsequences associated with each of these previous elements.
   Let the current element be A[i] and all elements before it be A[0...j]
        2.1. If (A[j] < A[i])
             A[j] is a potential candidate of an increasing subsequence ending at A[i]
        2.2. However the above condition does not gurantee an increasing subsequence of maximal length.
             To get an increasing subseq of maximal length ending at A[i],
             we also need to consider the maximum length of increasing subseq formed ending at A[j] (0<j<i)
        2.3. A[j] < A[i] && L[j] > L[i]
             Include A[j] only if it maintains the increasing subseq and offers more elements than the current maximum.

*/


public int LCslength(int A[]){
    int L[] = new int[A.length];
    int maxLen = 1;
    
    //length of increasing subseq ending at A[0] is 1
    L[0] = 1;
    
    for(int i=1 ; i<A.length ; i++){
        for(int j=0 ; j<i ; j++){
            if(A[j] < A[i] && L[j] > L[i]){
                L[i] = L[j];
            }
        }
        L[i]++; //to include the last element in the subseq
        if(maxLen < L[i]) maxLen = L[i];
    }
    
    return maxLen;
}

/*
Time Complexity - O(n^2)
Space Complexity - O(n)
*/
