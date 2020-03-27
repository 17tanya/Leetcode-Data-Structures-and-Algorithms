/*
https://www.geeksforgeeks.org/count-number-binary-strings-without-consecutive-1s/

Given a positive integer N, count all possible distinct binary strings of length N such that there are no consecutive 1’s.

Examples:

Input:  N = 2
Output: 3
// The 3 strings are 00, 01, 10

Input: N = 3
Output: 5
// The 5 strings are 000, 001, 010, 100, 101

Let,
1. a[i] = #binary strings of length i which do not contain any two consecutive 1’s and which END IN 0.
2. b[i] = #binary strings of length i which do not contain any two consecutive 1’s and which END IN 1.

We can append either 0 or 1 to a string ending in 0, but we can only append 0 to a string ending in 1.
This yields the recurrence relation:
    a[i] = a[i - 1] + b[i - 1]
    b[i] = a[i - 1] 

The base cases of above recurrence are a[1] = b[1] = 1. The total number of strings of length i is just a[i] + b[i].
*/

public int countStrings(int n){
    int endsIn0[] = new int[n];
    int endsIn1[] = new int[n];
    
    endsIn1[0] = 1;
    endsIn0[0] = 1;
    
    for(int i=1 ; i < n ; i++){
        endsIn0[i] = endsIn0[i-1] + endsIn1[i-1];
        endsIn1[i] = endsIn0[i-1];
    }
    
    return (endsIn0[n] + endsIn1[n]);
}

/*
Time Complexity and Space Complexity - O(n)
*/

