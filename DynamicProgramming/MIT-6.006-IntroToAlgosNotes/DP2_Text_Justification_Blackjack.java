//MIT OpenCourseWare - https://www.youtube.com/watch?v=ENyox7kNKeY&list=PLUl4u3cNGP61Oq3tWYp6V_F-5jb5L2iHb&index=21&t=107s

/*
REVISION
How to interpret DP?
1. DP ~ Carefull Brute Force
2. DP ~ Guessing + Recursion + Memoization
        Guessing ==> Carefull brute force - to pick the best one
        Recursion ==> express solution of the problem in terms of solution of sub-problems.
                      Look for SUBSTRUCTURES in the problem to find the recursive relation.
        Memoization ==> Recursion is exponential in time, using memoization we make it polynomial time.
                        Memoization avoids recomputation.
3. DP ~ Shortest path in Directed Acyclic Graph
Time  = (number of subproblems)*(time per subproblem)


5 steps to DP:
  1. Define SUBPROBLEMS ==> number of subproblems
  2. Guess (Part of Solution) ==> number of choices
  3. Relate subproblem solutions (usually with a recurrence) ==> time per subproblem [similar to num of choices]
  4. Recurse and memoize ==> check subproblem reccurence [in terms of formula/min or max optimization]
            OR           ==> check subproblem reccurence is acyclic ie. has topological order
     Build DP table Bottom-up (using loops - more practical and faster)
  5. Solve orignal problem ==> total time
  
  
Mapping Relation with examples
                                    Fibonacci                              Shortest Paths
1. Subproblems                  F(k) for k=1....n           delta(k)(s,v) for v belonds to V, 0<=k<=|V|
   number of subproblems                n                                      V^2
2. Guess                             nothing                           edge into v (if any)
   number of choices                    1                                 indegree(v)+1
3. Recurrence                   F(k)=F(k-1)+F(k-2)          delta(k)(s,v)=min{delta(k-1)(s,u) + w(u,v) | (u,v) belongs to E}
   time/subproblem                  theta(1)                             theta(indegree(v)+1)
4. Topological Order              for k=1.....n                for k=0,1....|V|-1 for v belongs to V
*/


//TEXT JUSTIFICATION
/*
Problem - Split text into "good" lines
          text is a list of words
          badness(i,j) ==> indicates how BAD would it be to use the words[i,j-1] in one line.
          badness(i,j) = (page width - total width)^3 or infinity

We can use a Greedy approach but this is not optimal because the first few lines might look good but the last few lines
might look bad. in the greedy algorithm, you make the first line as good as you can. But it might actually be better to leave out
some of the words that would fit here in order to make the next line better. 

example - 
aaaaaa aaaaaa aaaaaa aaaaaa
aaaaaa aaaaaa aaa aaaa aa
aaaaaaaaaaaa         aaaaaa
aa                       aa

Solution would be to minimize the sum of the badnesses of the lines.

BRUTE FORCE APPROACH - Exponential time
For each word, guess if it starts a line or not. For n words, we will have 2^n different splits.
For every word I say yes or no, does this is begin a line? So what I'd like to figure out is where those lines begin. 

DP Solution for Text Justification
1. Subproblems - suffix[i:] ==> indicates that word i is used as the first word of the current line and the remaining words from                        i onwards can be considered to chose the first word for the next line.
number of subproblems = n
2. Guesses - Where to start the next line
number of guesses <= n-i = O(n)
3. Recurrence (This recurrence returns the COST and not the actual word - for that we use Parent Pointers)
DP(i) = min(DP(j) + badness(i,j), for j in range(i+1,n+1))
time/subproblem = O(n)
4. Topological Order - n,n-1,n-2,......0
Total time = (num. of subproblems)*(time/subproblem) = theta(n^2)
5. Original Problem - DP(0)

Parent Pointers to store the solution which gave us the minimum cost
These help us to remember which guess was the best.
parent[i] = argmin(......) = j

to see the line-break words we call ==> 0 --> parent[0] --> parent[parent[0]] --> .....
*/
