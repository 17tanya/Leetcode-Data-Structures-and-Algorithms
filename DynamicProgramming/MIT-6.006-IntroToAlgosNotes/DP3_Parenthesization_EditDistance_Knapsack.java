/*
SUBPROBLEMS FOR STRINGS/SEQUENCES - 
1. suffix     x[i:]  ==> theta(n)    for all i
    If you always are plucking from the beginning, you have suffixes.
2. prefix     x[:i]  ==> theta(n)    for all i
    If you're plucking off from the end instead of the beginning, you'll end up with prefixes.
3. substring  x[i:j] ==> theta(n^2)  for all i <= j


PARENTHESIZATION
Optimal evaluation of associative expression - A0 . A1 . A2 ..... An
example - (A0.A1).(A2.A2)...An
        - ((A0.A1).A2).(A3.A4)...An
        
PARENTHESIZATION OF MATRIX MULTIPLICATION
Consider 3 matrices - A(nx1), B(1xn), C(nx1)
(A*B)*C is computed in theta(n^2) 
A*(B*C) is computed in theta(n)

DP Approach - 
1. Subproblems - Optimal evaluation of Ai....Aj-1
   number of subproblems = n^2 since we will define subproblems in terms of substring
2. Guess - We guess the outermost/last multiplication: (Ai....Ak-1).(Ak...Aj-1) - Subproblem is in the form of Substring
   number of choices for k = j-i+1 = O(n)
3. Recurrence - 
   DP(i,j) = min(DP(i,k) + DP(k,j) + cost of computing A[i:k].A[k:j], for each k in range(i+1,j))
   cost of computing A[i:k].A[k:j] can be calculated by considering the dimensions of the matrices
   time/subproblem = O(n)
4. Time - (number of subproblems)*(time/subproblems) = theta(n^3)
   Topological Order - Increasing substring size
NOTE - This DP does not require to compute the shortest path in DAG
       Base case - DP(i,i+1) = 0 - Only one matrix, no multiplication needed, cost = 0
       See 22:40
5. Overall Problem - DP(0,n)


EDIT DISTANCE
Given 2 strings x and y, what is the cheapest possible sequence of character edits to turn x --> y.
Character edits are - 
        1. insert a character c
        2. delete a character c
        3. replace a character c by c'
The cost of each of these edits may be different


DP Approach - 
Do DP simultaneoulsy on x and y. Look at SUFFIXES of x and y to make the subproblems and combine these subproblems by multiplication.
1. Subproblems - Edit Distance on x[i:] and y[j:](possibly different suffix of y)
   Number of subproblems - n^2
   If,
   length(x) = n & length(y) = n
   we have n choices for x and n choices for y. We need to consider these choices as pairs, ie n^2 pairs.
   For different lengths, number of subproblems = theta(|x|.|y|)
2. Guess - One of 3 possibilities
                i. replace x[i] --> y[j], remaining substrings are x[i+1:] and y[j+1:] 
                ii. insert y[j] in x, remaining substrings are x[i:] and y[j+1:]
                iii. delete x[i], remaining substrings are x[i+1:] and y[j:]
3. Recurrence - 
   DP(i,j) = min{ (cost of replace x[i] --> y[j]) + DP(i+1,j+1),
                  (cost of insert y[j]) + DP(i,j+1),
                  (cost of delete x[i]) + DP(i+1,j) }
   where, DP(i,j) indicates suffix x[i:] and y[j:]
4. Topological Sort - 
        Bottom up approach
        for i = |x|...0 :
            for j = |y|...0 :
    DP with shortest path in DAG, where DAG is a 2D matrix in which i represents rows and j represents columns.
    i ranges from [0...|x|] and j ranges from [0...|y|].
    each of the cells in the matrix represents a node in the DAG. The value of the node(cell) depends on 3 adjacent cells
    
        0 <-- j -->      |y|
        ____________________
     0  |  ____            |
        |  |__|<           |
     x  |    ^ \           |
        |                  |
        |__________________|
        
        where, < represents an edge from the left cell to the current cell ==> cost of insert
               \ represents an edge from the bottom left cell to the current cell ==> cost of replace
               ^ represents an edge from the below cell to the current cell ==> cost of delete
        In the matrix we need to progress towards the origin.
5. Overall Problem - DP(0,0) ==> top left cell in DAG
   Time = (num of subproblems)*(time/subproblem)
        = theta(|x|.|y|)*theta(1) because time/subproblem is constant
        = theta(|x|.|y|)
   We can improve the space complexity further, we only need the last row or the last column. So store only that.
   The above described approach is the best in terms of time ==> Quadratic in time and space
   Better approach ==> Quadratic time and linear space

EDIT DISTANCE CAN BE USED FOR LCS(LEAST COMMON SUBSEQUENCE) AND DNA MUTATION


KNAPSACK PROBLEM
DP Approach - 
1. Subproblems - suffix[i:] of items. Each subproblem needs to have 2 kinds of information - item index and remaining capacity.
   number of subproblems = theta(nS)
   n ==> num of items
   S ==> capacity of knapsack
2. Guessing - is item i in knapsack or not? we have 2 choices.
3. Recurrence - 
   DP(i,X) = max{ DP(i+1,X), DP(i+1,X-Si) + Vi }
   Time = theta(nS)
   NOT POLYNOMIAL TIME ==> PSUEDOPOLYNOMIAL TIME
*/
