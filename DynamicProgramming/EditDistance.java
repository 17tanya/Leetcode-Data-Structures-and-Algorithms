/*
https://www.geeksforgeeks.org/edit-distance-dp-5/
https://www.techiedelight.com/levenshtein-distance-edit-distance-problem/
*/

/*
The problem is to find the number of minimum edits required to transform one string into another.
Edit Distance is a way of quantifying how dissimilar two strings are to one another by counting minimum number of operations required to transform one string into another.

What are the subproblems?
The idea is process all characters one by one staring either from left or right of both strings.
Let us traverse from right corner, there are two possibilities for every pair of character being traversed.

n: Length of X (first string)
m: Length of Y (second string)

1. If (X[n] == Y[m]) we don't have to do anything as they are the same. Ignore the last characters and recur for X[1...n-1] and Y[1...m-1] to find their edit-distance
2. Else (If last characters are not same), we consider all operations on ‘X’, consider all three operations on last character of first string ie. X[n], recursively compute minimum cost for all three operations and take minimum of three values.
        Insert: Recur for n and m-1
        Remove: Recur for n-1 and m
        Replace: Recur for n-1 and m-1

*/

//RECURSIVE APPROACH
public int editDist(String X, String Y, int n, int m){
    //If first string is empty we need to insert all remaining characters from second string
    if(n==0) return m;
    
    //If second string is empty we need to insert all remaining characters from first string
    else if(m==0) return n;
    
    else if(X.charAt(n-1) == Y.charAt(m-1)) return editDist(X, Y, n-1, m-1);
    
    else{
        return (1 + Math.min( //one added as a cost of operation
            editDist(X, Y, n-1, m-1), //replacement of X[n] to Y[m]
            editDist(X, Y, n, m-1), //insertion of Y[m] in X
            editDist(X, Y, n-1, m) //deletion of X[n] from X
        ));
    }
}

/*
Time Complexity
Worse Case - O(3^n)
Worse Case arises when none of the characters of the two strings match. So we have to explore all three operations n times
where n is the length of the first string and the three operations are - insertion, deletion and replacement
*/


/*
DYNAMIC PROGRAMMING - BOTTOM UP APPROACH
The problem can be divided into subproblems and the solution to the main problem can be computed by solving the subproblems ==> Optimal Substructure Property
The subproblems are overlapping which can be seen by drawing the recursion call tree ==> Overlapping Subproblems Property

Hence the problem can be solved using DP.
*/

public int editDist(String X, String Y){
    int n = X.length();
    int m = Y.length();
    
    int dist[][] = new int[n+1][m+1];
    
    for(int i=0 ; i<=n ; i++){
        for(int j=0 ; j<=m ; j++){
            
            //If the first string is empty, then we need to insert all characters of the second string into the first string
            if(i==0) dist[i][j] = j; //Min Operations = j (add j characters)
            
            //If the second string is empty, then we need to delete all characters from first string
            else if(j==0) dist[i][j] = i; //Min Operations = i (remove i characters)
            
            /*
            If last characters are the same, no need for any operation. The edit-distance will remain the same as it was
            before considering X[i] and Y[j].
            */
            else if( X.charAt(i-1) == Y.charAt(j-1) ) dist[i][j] = dist[i-1][j-1];
            
            /*
            last characters of the strings do not match.
            We need to explore all three operations and choose the one which results in minimum cost.*/
            else {
                dist[i][j] = 1 + Math.min( // 1 is added as the cost of the chosen operation
                    dist[i-1][j], // delete X[i] from X
                    Math.min(dist[i][j-1], //insert Y[j] in X
                    dist[i-1][j-1]) //replace X[i] with Y[j]
                )
            }
        }
    }
    
    return dist[n][m];
    
}
/*
Time Complexity - O(n*m)
We solve each subproblem once, number of subproblems = n*m
Space Complexity - O(n*m)
*/

/*
DYNAMIC PROGRAMMING - BOTTOM UP APPROACH - SPACE OPTIMIZED
We need the values from only the previous rows so we maintain a table of size (2*)
*/

public int editDist(String X, String Y){
    int n = X.length();
    int m = Y.length();
    
    int dist[][] = new int[2][n+1];
    
    //when second string is empty ==> delete all characters from first string
    for(int i=0;i<=n;i++) dist[0][i] = i;
    
    for(int i=1;i<=m;i++){
        for(int j=0;j<=n;j++){
            if(j==0) dist[i%2][j] = i;
            else if(X.charAt(i-1) == Y.charAt(j-1)) dist[i % 2][j] = dist[(i-1) % 2][j-1];
            else{
            
                dist[i%2][j] = 1 + Math.min(
                                        dist[(i-1) % 2][j-1], //replacement
                                Math.min( dist[(i-1) % 2][j], //insertion
                                         dist[i % 2][j-1]) //deletion
                                   );
            }
        }
    }
    
    return dist[m%2][n];
}

/*
Time Complexity - O(n*m)
Space Complexity - O(2*n)
where n = length of string1
*/


