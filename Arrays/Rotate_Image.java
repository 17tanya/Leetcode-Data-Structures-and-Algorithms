//Leetcode 48. Rotate Image
//Question - https://leetcode.com/problems/rotate-image/

/*Reference - https://www.geeksforgeeks.org/inplace-rotate-square-matrix-by-90-degrees/
Below are some important observations.

First row of source –> First column of destination, elements filled in opposite order
Second row of source –> Second column of destination, elements filled in opposite order
so … on
Last row of source –> Last column of destination, elements filled in opposite order.
An N x N matrix will have floor(N/2) square cycles. For example, a 4 X 4 matrix will have 2 cycles.
The first cycle is formed by its 1st row, last column, last row and 1st column.
The second cycle is formed by 2nd row, second-last column, second-last row and 2nd column.

The idea is for each square cycle, we swap the elements involved with the corresponding cell in the matrix in anti-clockwise direction
i.e. from top to left, left to bottom, bottom to right and from right to top one at a time. We use nothing but a temporary variable to achieve this.

Input:
 1  2  3  4 
 5  6  7  8 
 9 10 11 12 
13 14 15 16 

Output:
 4  8 12 16 
 3  7 11 15 
 2  6 10 14 
 1  5  9 13

First Cycle (Involves Red Elements)
 (1)  (2)  (3)  (4) 
 (5)   6    7   (8) 
 (9)   10   11  (12) 
 (13) (14) (15) (16) 

Moving first group of four elements (First
elements of 1st row, last row, 1st column 
and last column) of first cycle in counter
clockwise. 
(4)   2   3 (16)
 5    6   7   8 
 9   10  11  12 
(1)  14  15 (13) 
 
Moving next group of four elements of 
first cycle in counter clockwise 
 4   (8)  3   16 
 5    6   7  (15)  
(2)  10  11   12 
 1   14  (9)  13 

Moving final group of four elements of 
first cycle in counter clockwise 
 4    8  (12)  16 
 (3)  6   7    15 
 2   10   11  (14) 
 1   (5)  9    13  

Second Cycle (Involves Blue Elements)
4   8   12   16 
3  (6)  (7)  15 
2  (10) (11) 14 
1   5    9   13

Fixing second cycle
 4   8   12  16 
 3  (7) (11) 15 
 2  (6) (10) 14 
 1   5   9   13
*/


/* Reference - https://leetcode.com/problems/rotate-image/discuss/18872/A-common-method-to-rotate-the-image
 * clockwise rotate
 * first reverse up to down, then swap the symmetry 
 * 1 2 3     7 8 9     7 4 1
 * 4 5 6  => 4 5 6  => 8 5 2
 * 7 8 9     1 2 3     9 6 3

void rotate(vector<vector<int> > &matrix) {
    reverse(matrix.begin(), matrix.end());
    for (int i = 0; i < matrix.size(); ++i) {
        for (int j = i + 1; j < matrix[i].size(); ++j)
            swap(matrix[i][j], matrix[j][i]);
    }
}

*/

/*
 * anticlockwise rotate
 * first reverse left to right, then swap the symmetry
 * 1 2 3     3 2 1     3 6 9
 * 4 5 6  => 6 5 4  => 2 5 8
 * 7 8 9     9 8 7     1 4 7

void anti_rotate(vector<vector<int> > &matrix) {
    for (auto vi : matrix) reverse(vi.begin(), vi.end());
    for (int i = 0; i < matrix.size(); ++i) {
        for (int j = i + 1; j < matrix[i].size(); ++j)
            swap(matrix[i][j], matrix[j][i]);
    }
}
*/


class Solution {
    public void rotate(int[][] matrix) {
        int n = matrix.length;
        for(int row=0 ; row<n/2 ; row++){ //number of cycles in matrix
            for(int col=row ; col<n-1-row ; col++){
                int temp = matrix[col][n-1-row];
                matrix[col][n-1-row] = matrix[row][col];
                matrix[row][col] = matrix[n-col-1][row];
                matrix[n-col-1][row] = matrix[n-1-row][n-1-col];
                matrix[n-1-row][n-1-col] = temp;
            }
        }
    }
}
