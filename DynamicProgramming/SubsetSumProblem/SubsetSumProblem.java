/*
https://www.geeksforgeeks.org/subset-sum-problem-dp-25/
https://www.techiedelight.com/subset-sum-problem/


To Do - Print Subsets and Solve in O(sum)
Further - Does this work on negative numbers?


Given a set of non-negative integers, and a value sum, determine if there is a subset of the given set with sum equal to given sum.
Example:
Input:  set[] = {3, 34, 4, 12, 5, 2}, sum = 9
Output:  True  //There is a subset (4, 5) with sum 9.

A Naive Approach would be to form all subsets and see if their sum equals the given sum. 
Number of subsets = 2^n
Time rqd to check if the sum of subset equals the given sum = n (at most, becuase maximum size of subset can be n)
Time Complexity - O(n 2^n)


RECURSION
We have two choices - 
1. Include the current item and recur with remaining sum
2. Exclude current item and recur
*/

public boolean isSubset(int A[], int current, int sum){
    //Base Case 1: sum == 0
    if( sum == 0 ) return true;
    
    //Base Case 2: we have exhausted all elements and sum is still not 0
    else if( n==0 && sum!=0 ) return false;
    
    //Does the current element's value exceed the remaining sum? ==> Current element has to be EXCLUDED
    if( A[current-1] > sum ) return isSubset(A, current-1, sum);
    
    //Explore both choices - Include and Exclude
    return (isSubset(A, current-1, sum) || isSubset(A, current-1, sum-A[current-1]));
}

/*
Time Complexity - O(2^n)
We explore two choices at each recursion.

The above solution may try all subsets of given set in worst case. Therefore time complexity of the above solution is exponential. The problem is in-fact NP-Complete (There is no known polynomial time solution for this problem).
*/

//DYNAMIC PROGRAMMING - PSUEDOPOLYNOMIAL - BOTTOM-UP APPROACH

public boolean isSubset(int A[], int n, int sum){
    boolean T[][] = new boolean[n+1][sum+1];
    
    //if sum = 0, then answer is true
    for(int i=0;i<=n;i++) T[i][0] = true;
    
    //if the set is input is an empty set
    for(int j=0;j<=sum;j++) T[0][j] = false;
    
    for(int i=1;i<=n;i++){
        for(int j=1;j<=sum;j++){
            
            //Does the current element's value exceed the remaining sum? ==> Current element has to be EXCLUDED
            if(A[i-1] > j) T[i][j] = T[i-1][j];
            
            //Explore both - Include and Exclude
            else T[i][j] = (T[i-1][j] || T[i-1][j-A[i-1]]);
        }
    }
    
    return T[n][sum];
}

/*
Time Complexity - O(n*sum)
*/

//RECURSION USING MEMOIZATION - TOP-DOWN APPROACH

public boolean isSubset(int A[], int current, int sum, HashMap<String, Boolean> map){
    if(sum==0) return true;
    
    else if(n==0 && sum!=0) return false;
    
    String key = current+"|"+sum;
    boolean include = false;
    boolean exclude = false;
    if(!map.containsKey(key)){
        if( A[current-1] > sum ) include = isSubset(A, current-1, sum-A[current-1], map);
        exlude = isSubset(A, current-1, sum, map);
        map.put(key, (include || exclude));
    }
    return map.get(key);
}

/*
Time and Space Complexity - O(n*sum)
*/

