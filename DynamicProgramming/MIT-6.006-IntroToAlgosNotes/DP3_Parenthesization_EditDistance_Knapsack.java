/*
SUBPROBLEMS FOR STRINGS/SEQUENCES - 
1. suffix     x[i:]  ==> theta(n)    for all i
2. prefix     x[:i]  ==> theta(n)    for all i
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
*/
