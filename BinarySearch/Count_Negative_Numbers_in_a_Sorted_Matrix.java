//Leetcode 1351. Count Negative Numbers in a Sorted Matrix
//Question - https://leetcode.com/problems/count-negative-numbers-in-a-sorted-matrix/

/*Given a m * n matrix grid which is sorted in non-increasing order both row-wise and column-wise. 
Return the number of negative numbers in grid.

Example 1:

Input: grid = [[4,3,2,-1],[3,2,1,-1],[1,1,-1,-2],[-1,-1,-2,-3]]
Output: 8
Explanation: There are 8 negatives number in the matrix.

Example 2:

Input: grid = [[3,2],[1,0]]
Output: 0

Example 3:

Input: grid = [[1,-1],[-1,-1]]
Output: 3

Example 4:

Input: grid = [[-1]]
Output: 1
*/


class Solution {
    public int countNegatives(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int low = 0;
        int high = n-1;
        int mid = -1;
        int result = -1;
        int target = 0;
        int ans = 0;
        
        
        for(int i=0;i<m;i++){
            low = 0;
            high = n-1;
            result = -1;
            while(low<=high){
                mid = low + (high-low+1)/2;
                if(grid[i][mid] >= target){ //push algo to right ==> find first negative
                    low = mid + 1;
                }
                else{
                    result = mid; //store current result 
                    high = mid - 1; //push the upper bound to find the first negative
                }
            }
            
            //if all the numbers are positives or zeros result will be -1
            if(result!=-1){
                ans+=(n-result);
            }
        }
        
        return ans;
    }
}


/* Most Voted Solution - 
This solution uses the fact that the negative regions of the matrix will form a "staircase" shape, e.g.:

++++++
++++--
++++--
+++---
+-----
+-----

What this solution then does is to "trace" the outline of the staircase.
Start from bottom-left corner of the matrix, count in the negative numbers in each row.
*/

    public int countNegatives(int[][] grid) {
        int m = grid.length, n = grid[0].length, r = m - 1, c = 0, cnt = 0;
        while (r >= 0 && c < n) {
            if (grid[r][c] < 0) {
                --r;
                cnt += n - c; // there are n - c negative numbers in current row.
            }else {
                ++c;
            }
        }
        return cnt;
    }

/*Simlarly, you can also start from top-right corner, whichever you feel comfortable with, count in the negative numers in each column.*/

    public int countNegatives(int[][] grid) {
        int m = grid.length, n = grid[0].length, r = 0, c = n - 1, cnt = 0;
        while (r < m && c >= 0) {
            if (grid[r][c] < 0) {
                --c;
                cnt += m - r; // there are m - r negative numbers in current column.
            }else {
                ++r;
            }
        }
        return cnt;
    }
