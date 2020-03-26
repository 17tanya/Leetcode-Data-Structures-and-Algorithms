/*
https://www.techiedelight.com/check-string-interleaving-two-given-strings/
*/

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
