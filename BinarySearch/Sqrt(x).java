//Leetcode 69. Sqrt(x)
//Question - https://leetcode.com/problems/sqrtx/
//Further Reading - Newton's root-finding algorithm

/*Implement int sqrt(int x).

Compute and return the square root of x, where x is guaranteed to be a non-negative integer.

Since the return type is an integer, the decimal digits are truncated and only the integer part of the result is returned.

Example 1:

Input: 4
Output: 2

Example 2:

Input: 8
Output: 2
Explanation: The square root of 8 is 2.82842..., and since 
             the decimal part is truncated, 2 is returned.

*/

class Solution {
    public int mySqrt(int x) {
        if(x==0) return 0; //corner case
        
        int low = 1;
        int high = x;
        //search range is [1,x]
        //can be improved further ==> [1, x/2]
        //(you need to do math to prove it: x/2 should include the sqrt(x), i.e. (x/2)^2 >= x, then x >= 2.
        
        int result = -1;
        while(low<=high){
            int mid = low + (high-low)/2;
            if(mid<=x/mid){ //use this condition and not (mid*mid<=x) because it might cause integer overflow
                result = mid;
                low = mid + 1;
            }
            else high = mid - 1;
        }
        return result;
    }
}
