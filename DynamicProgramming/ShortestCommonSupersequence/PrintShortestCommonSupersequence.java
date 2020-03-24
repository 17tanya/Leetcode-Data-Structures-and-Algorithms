/*
https://www.techiedelight.com/shortest-common-supersequence-finding-scs/
*/

//Find one Shortest Common Supersequence
// length(X) = m and length(Y) = n

public String findSCS(String X, String Y, int m, int n,int T[][]){

    if(m == 0) return Y.substring(0,n);
    
    else if(n == 0) return X.substring(0,m);
    
    else if( X.charAt(n-1) == Y.charAt(m-1) ){
        return findSCS(X, Y, m-1, n-1, T) + X.charAt(n-1);
    }
    else if( T[m-1][n] < T[m][n-1] ){
        return findSCS(X, Y, m-1, n, T) + X.charAt(m-1);
    }
    else{
        return findSCS(X, Y, m, n-1, T) + Y.charAt(n-1);
    }
}

//Print all Shortest Common Supersequence
// length(X) = m and length(Y) = n

public List<String> findSCS(String X, String Y, int m, int n, int T[][]){
    if( m == 0){
        List<String> l = new ArrayList<>();
        l.add(Y.substring(0,n));
        return l;
    }
    else if( n == 0){
        List<String> l = new ArrayList<>();
        l.add(X.substring(0,m));
        return l;
    }
    else if( X.charAt(m-1) == Y.charAt(n-1) ){
        List<String> scs = findSCS(X, Y, m-1, n-1, T);
        List<String> res = new ArrayList<>();
        for(String s : scs){
            res.add(s+X.charAt(m-1));
        }
        return res;
    }
    else if( T[m-1][n] < T[m][n-1] ){
        List<String> scs = findSCS(X, Y, m-1, n, T);
        List<String> res = new ArrayList<>();
        for(String s : scs){
            res.add(s+X.charAt(m-1));
        }
        return res;
    }
    else if( T[m-1][n] > T[m][n-1] ){
        List<String> scs = findSCS(X, Y, m, n-1, T);
        List<String> res = new ArrayList<>();
        for(String s : scs){
            res.add(s+Y.charAt(n-1));
        }
        return res;
    }
    else{ //T[m-1][n] == T[m][n-1]
        List<String> top = findSCS(X, Y, m-1, n, T);
        List<String> left = findSCS(X, Y, m, n-1, T);
        List<String> res = new ArrayList<>();
        for(String s : top){
            res.add(s + X.charAt(m-1));
        }
        for(String s : left){
            res.add(s + Y.charAt(n-1));
        }
        return res;
    }
    
}


// Function to fill lookup table by finding length of SCS of
// sequences X[0..m-1] and Y[0..n-1]
public static void SCSLength(String X, String Y, int m, int n, int[][] T)
{
	// initialize first column of the lookup table
	for (int i = 0; i <= m; i++) {
		T[i][0] = i;
	}

	// initialize first row of the lookup table
	for (int j = 0; j <= n; j++) {
		T[0][j] = j;
	}

	// fill the lookup table in bottom-up manner
	for (int i = 1; i <= m; i++)
	{
		for (int j = 1; j <= n; j++)
		{
			// if current character of X and Y matches
			if (X.charAt(i - 1) == Y.charAt(j - 1)) {
				T[i][j] = T[i - 1][j - 1] + 1;
			}
			// else if current character of X and Y don't match
			else {
				T[i][j] = Integer.min(T[i - 1][j] + 1, T[i][j - 1] + 1);
			}
		}
	}
}
