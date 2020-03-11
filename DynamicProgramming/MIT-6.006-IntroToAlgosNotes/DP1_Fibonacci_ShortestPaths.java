//MIT OpenCourseWare - https://www.youtube.com/watch?v=OQ5jsbhAv_M&list=PLUl4u3cNGP61Oq3tWYp6V_F-5jb5L2iHb&index=19

/*
It's especially good, and intended for, optimizationproblems, things like shortest paths. You want to find the best way to do something.
Shortest path is you want to find the shortest path, the minimum-length path. You want to minimize, maximize something, that's an optimization problem
You can also think of dynamic programming as a kind of exhaustive search. Which is usually a bad thing to do because it leads to exponential time.
But if you do it in a clever way, via dynamic programming, you typically get polynomial time. 
    
DP ~ Carefull brute force
DP ~ subproblems + resuse*/

//FIBONACCI SERIES

//Naive Recursive Algorithm - Exponential in time
//T(n) = T(n-1) + T(n-2) + theta(1)
class fib{
    public int fibonacci(int n){
        int f = 0;
        if(n <= 2) f = 1;
        else f = fibonacci(n-1) + fibonacci(n-2);
        return f;
    }
}

//Memoized DP Algorithm
class fib{
    //memoization table
    HashMap<Integer,Integer> memo = new HashMap<Integer,Integer>();
    
    public int fibonnaci(int n){
        //check if subproblem is already solved
        if(memo.containsKey(n)) return memo.get(n);
        
        //new subproblem ==> has not been solved yet
        int f = 0;
        if(n <= 2) f = 1;
        else f = fibonacci(n-1) + fibonacci(n-2);
        
        //store the result of new subproblem
        memo.put(n,f);
        return f;
    }
}

/*
1. fibonnaci(k) only recurses the first time it's called for each k
2. memoized calls cost theta(1)
3. number if non-memoized calls is n ==> fibonnaci(1), fibonnaci(2),.......fibonnaci(n)
4. non-recursive work per call is theta(1)
5. time = theta(n)
*/

/*
DP ~ Memoization + Recursion
    1. memoize recursion (remember)
    2. re-use solutions to sub problems
    3. Time = (number of subproblems) * (time to solve earch subproblem)
       NOTE - Do not count memoized recursions
*/

//Bottom-up DP algorithm
class fib{
    HashMap<Integer,Integer> memo = new HashMap<Integer,Integer>();
    
    public int fibonacci(int n){
        int f = 0;
        for(int k=1; k<=n ; k++){
            if(k<=2) f = 1;
            else f = memo.get(n-1) + memo.get(n-2);
            
            memo.put(k,f);
        }
        
        return memo.get(n);
    }
}

/*
1. Bottom-up approach does the exact same computation as memoized approach.
2. Here we are doing a TOPOLOGICAL SORT of subproblem dependency DAG.
3. Often takes uses less space
*/

//SHORTEST PATHS
/*
Guessing - Don't know the answer - guess. Try ALL guesses and take the BEST ONE.
DP ~ Recursion + memoization + Guessing

Consider a graph
(S) --> () --> () --> (U) --> (V)
We have multiple incoming edges on (V)
Guess all the possible incoming edges to v, and then recursively compute the shortest path from s to u. 
And then add on the edge v. 

delta(S,V) = min(u,v) belongs to E (delta(S,U) + w(U,V))
delta(S,U) is a recursive call

Time complexity is definitely going to be exponential without memoization. But we know. We know how to make algorithms better. We memoize. 
To define the function delta of sv, you first check, is s comma v in the memo table? If so return that value.
Otherwise, do this computation where this is a recursive call and then stored it in the memo table. 

see 41:14 for the graph
This algorithm will take INFINITE time on graphs with cycle.
For Directed Acyclic Graph : theta(V + E)
time for each subproblem delta(S,V) = indegree(V) + 1
total time = summation(for all vertices) [indgree(V) + 1] = theta(V+E)

!!!!SUBPROBLEM DEPENDENCIES SHOULD BE ACYCLIC FOR MEMOIZATION TO WORK!!!! - can be related to the bottom-up approach which uses 
topological sort of subproblem dependency - DAG
*/

