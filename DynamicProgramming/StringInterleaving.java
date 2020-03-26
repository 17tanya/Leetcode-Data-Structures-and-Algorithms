/*
https://www.techiedelight.com/check-string-interleaving-two-given-strings/
https://github.com/mission-peace/interview/blob/master/src/com/interview/dynamic/TwoStringInterleavingToFormThird.java


a) If first character of S matches with first character of X, we move one character ahead in X and S and recursively check.
b) If first character of S matches with first character of Y, we move one character ahead in Y and S and recursively check.

*/

//For only recursion --> Worst Case Time Complexity - O(2^(n+m)) --> When both strings are the same
//n and m are lengths of the two strings

//MEMOIZED RECUSRION
public boolean isInterleaving(String X, String Y, String S, HashMap<String, Boolean> map){
    if(X.length() == 0 && Y.length() == 0 && S.length() == 0) return true;
    else if( X.length() + Y.length() != S.length() ) return false;
    
    String key = X + "|" + Y + "|" + S;
    
    if(!map.containsKey(key)){
        boolean b1 = (X.length()!=0 && X.charAt(0)==S.charAt(0)) && isInterleaving(X.substring(1), Y, S.substring(1), map);
        boolean b2 = (Y.length()!=0 && Y.charAt(0)==S.charAt(0)) && isInterleaving(X, Y.substring(1), S.substring(1), map);
        map.put(key, (b1 || b2));
    }
    return map.get(key);
}

//BOTTOM UP DP
public boolean isInterleaving(String X, String Y, String S){
    
    if(X.length()+Y.length() != S.length()) return false;
    
    //T[i][j] indicates if S( 0....(i+j-1) ) is an interleaving of X(0...i) and Y(0...j)
    int T[][] = new int[X.length()+1][Y.length()+1];
    
    for(int i=0 ; i < X.length() ; i++){
        for(int j=0 ; j < Y.length() ; j++){
            
            //total length of S to be checked for interleaving when first i charachters from X and first j characters from Y are considered
            int l = i + j - 1;
            
            //is interleaving of two empty strings also an empty string? ==> True
            if(i==0 && j==0) T[i][j] = true;
            
            //when X is an empty string ==> Only check Y and S
            else if(i==0){
                //Check is the current characters match? ==> If True, check the subproblem ==>
                //==> does Y(0...j-1) form an interleaving in S(0...j-1)
                //solution to current problem is true only when - 
                //  1. current characters match
                //  2. the subproblem also forms an interleaving ie. the subproblem returns true
                if(Y.charAt(j-1) == S.charAt(l)) T[i][j] = T[i][j-1];
            }
            
            else if(j==0){
                if(X.charAt(i-1) == S.charAt(l)) T[i][j] = T[i-1][j];
            }
            //checks if either of the current characters X[i-1] or Y[j-1] matches the current character from interleaved string S[l]
            else{
                //If there is a match of current characters, solve the subproblem ie. T[i-1][j] or T[i][j-1]
                T[i][j] = (X.charAt(i-1) == S.charAt(l) ? T[i-1][j] : false) || (Y.charAt(j-1) == S.charAt(l) ? T[i][j-1] : false);
            }
        }
    }
    
    return T[X.length()][Y.length()];
}

/*
Time Complexity and Space Complexity - O(X.length()*Y.length())

*/
