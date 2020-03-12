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
*/
