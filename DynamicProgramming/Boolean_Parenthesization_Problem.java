/*
https://www.geeksforgeeks.org/boolean-parenthesization-problem-dp-37/

NOTE - The problem is solved similar to Longest Palindromic Subsequence. Only the space above the principal diaognal is utilized

Given a boolean expression with following symbols.

Symbols
    'T' ---> true 
    'F' ---> false 

And following operators filled between symbols

Operators
    &   ---> boolean AND
    |   ---> boolean OR
    ^   ---> boolean XOR 

Count the number of ways we can parenthesize the expression so that the value of expression evaluates to true.


Let the input be in form of two arrays one contains the symbols (T and F) in order and other contains operators (&, | and ^}

Examples:

Input: symbol[]    = {T, F, T}
       operator[]  = {^, &}
Output: 2
The given expression is "T ^ F & T", it evaluates true
in two ways "((T ^ F) & T)" and "(T ^ (F & T))"

Input: symbol[]    = {T, F, F}
       operator[]  = {^, |}
Output: 2
The given expression is "T ^ F | F", it evaluates true
in two ways "( (T ^ F) | F )" and "( T ^ (F | F) )". 

Input: symbol[]    = {T, T, F, T}
       operator[]  = {|, &, ^}
Output: 4
The given expression is "T | T & F ^ T", it evaluates true
in 4 ways ((T|T)&(F^T)), (T|(T&(F^T))), (((T|T)&F)^T) 
and (T|((T&F)^T)). 




Let T(i, j) represents the number of ways to parenthesize the symbols between i and j (both inclusive) such that the subexpression between i and j evaluates to true.
1. Using a variable gap we fix the number of symbols we want to evaluate.
2. Then, we fix the start and end of the window which is slid by one each time the internal processing is done.
3. Internal Processing - we take a variable to divide the symbols between start and end, to consider all possible combinations.
                         This variable represents the input operators.
    - - [start-] k [- - - - - - end]- - 
    - - [start- -] k [- - - - - end]- - 
    - - [start- - -] k [- - - - end]- -
    .....
    
Formula to compute T(i,j)
T(i,j) = Summation of k(i .... j-1) | T(i,k) * T(k+1,j)                             if operator[k] = &
                                    | Total(i,k)*Total(k+1,j) - F(i,k)*F(k+1,j)     if operator[k] = |
                                    | T(i,k)*F(k+1,j) + F(i,k)*T(k+1,j)             if operator[k] = ^
         where Total(i,k) = T(i,k) + F(i,k)

Let F(i, j) represents the number of ways to parenthesize the symbols between i and j (both inclusive) such that the subexpression between i and j evaluates to false.

Formula to compute F(i,j)
F(i,j) = Summation of k(i .... j-1) | Total(i,k)*Total(k+1,j) - T(i,k)*T(k+1,j)     if operator[k] = &
                                    | F(i,k)*F(k+1,j)                               if operator[k] = |
                                    | T(i,k)*T(k+1,j) + F(i,k)*F(k+1,j)             if operator[k] = ^
         where Total(i,k) = T(i,k) + F(i,k)

*/


public int countBooleanParenthesization(char sym[], char op[], int n){
    int T[][] = new int[n][n];
    int F[][] = new int[n][n];
    
    for(int i=0;i<n;i++){
        if(sym[i] == 'T') T[i][i] = 1;
        else T[i][i] = 0;
        
        if(sym[i] == 'F') F[i][i] = 1;
        else F[i][i] = 0;
    }
    
    
    for(int gap = 1 ; gap < n ; gap++){
    
        for(int start = 0, end = gap ; end < n ; start++ , end++){
        
            T[start][end] = F[start][end] = 0;
            
            for(int k = start ; k < end ; k++){
                
                int tot_start_k = T[start][k] + F[start][k];
                int tot_k_end = T[k+1][end] + F[k+1][end];
                
                if(op[k] == '&'){
                    T[start][end] += (T[start][k] * T[k+1][end]);
                    F[start][end] += (tot_start_k * tot_k_end - T[start][k] * T[k+1][end]);
                }
                
                else if(op[k] == '|'){
                    T[start][end] += (tot_start_k * tot_k_end - F[start][k] * F[k+1][end]);
                    F[start][end] += (F[start][k] * F[k+1][end]);
                }
                
                else{ //op[k] == '^'
                    T[start][end] += (T[start][k] * F[k+1][end]) + (F[start][k] * T[k+1][end])
                    F[start][end] += (F[start][k] * F[k+1][end]) + (T[start][k] * T[k+1][end]);
                }
                
            }
        }
    }
    
    return T[0][n-1];
} 

/*
Time Complexity - O(n^3)
Number of subproblems - n*n
Time required to solve each subproblem - n

Space Complexity - O(n^2)
*/

