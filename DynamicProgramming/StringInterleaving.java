/*
https://www.techiedelight.com/check-string-interleaving-two-given-strings/
https://github.com/mission-peace/interview/blob/master/src/com/interview/dynamic/TwoStringInterleavingToFormThird.java


a) If first character of S matches with first character of X, we move one character ahead in X and S and recursively check.
b) If first character of S matches with first character of Y, we move one character ahead in Y and S and recursively check.

*/

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
    
    int T[][] = new int[X.length()+1][Y.length()+1];
    
    for(int i=0 ; i < X.length() ; i++){
        for(int j=0 ; j < Y.length() ; j++){
            if(i==0 && j==0) T[i][j] = true;
            
            else if(i==0){
                if(Y.charAt(j-1) == S.charAt(j-1)) T[i][j] = T[i][j-1];
            }
            
            else if(j==0){
                if(X.charAt(i-1) == S.charAt(i-1)) T[i][j] = T[i-1][j];
            }
            else{
                T[i][j] = (X.charAt(i-1) == S.charAt(i-1) ? T[i-1][j] : false) || (Y.charAt(j-1) == S.charAt(j-1) ? T[i][j-1] : false);
            }
        }
    }
    
    return T[X.length()][Y.length()];
}
