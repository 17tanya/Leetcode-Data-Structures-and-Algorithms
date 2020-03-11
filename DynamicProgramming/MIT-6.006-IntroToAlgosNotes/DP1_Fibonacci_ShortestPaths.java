/*It's especially good, and intended for, optimizationproblems, things like shortest paths. You want to find the best way to do something.
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

