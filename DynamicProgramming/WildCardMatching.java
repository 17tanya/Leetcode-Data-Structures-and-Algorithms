/*
https://github.com/mission-peace/interview/blob/master/src/com/interview/dynamic/WildCardMatching.java
https://www.techiedelight.com/wildcard-pattern-matching/

Implement wildcard pattern matching with support for '?' and '*'
'?' matches any single character
'*' matches zero or more characters

*/

//Bottom Up Dynamic Programming

public boolean isMatch(String str, String pattern){
    char s[] = str.toCharArray();
    char p[] = pattern.toCharArray();
    
    //match[i][j] indicates if str[0...i] matches with pattern[0...j]
    boolean match[][] = new boolean[str.length() + 1][pattern.length() + 1];
    
    //empty string matches with empty pattern
    match[0][0] = true;
    
    //when the string is empty, only one character can match it ie.'*'
    //check if the first element of the pattern is '*' to match with an empty string
    if(pattern.length() > 0 && p[0] == '*') match[0][1] = true;
    
    for(int i=1 ; i < str.length() ; i++){
        for(int j=1 ; j < pattern.length() ; j++){
        
            //If the current elements of the pattern and the string match, we check the result of the subproblems
            //the subproblem in this case is --> string and pattern without considering the current elements
            //Also, if current pattern element is '?', it can match with any ONE character of the string. 
            //the subproblem again becomes the same
            if(p[j-1] == s[i-1] || p[j-1]=='?'){
                match[i][j] = match[i-1][j-1];
            }
            
            //'*' matches with 0 or more characters 
            //when we consider '*' to match with 0 characters, we basically imply '*' doesn't exsist in the pattern -->
            //subproblem becomes match[i][j-1]
            //'*' matches with one or more characters
            //consider '*' to match with current character, we need to find out does '*' match with the remaining characters of 
            //the string?? --> subproblem becomes match[i-1][j]
            else if(p[j-1] == '*'){
                match[i][j] = (match[i-1][j] || match[i][j-1]);
            }
        }
    }
    
    return match[str.length()][pattern.length()];
}

//Bottom up Optimized Approach
/*
Since the pattern might contain a long chain of continuous '*'s, we will reduce the pattern size. 
multiple '*'s are equaivalent to a single '*'
*/

public boolean isMatch(String str, String pattern){
    char s[] = str.toCharArray();
    char p[] = pattern.toCharArray();
    
    int index = 0;
    boolean isFirst = true;
    for(int i=0 ; i < p.length ; i++){
        if(p[i] == '*'){
            if(isFirst){
                p[index++] = p[i];
                isFirst = false;
            }
        }
        else{
            p[index++] = p[i];
            isFirst = false;
        }
    }
    
    boolean match[][] = new boolean[s.length + 1][index + 1];
    
    match[0][0] = true;
    
    if(index > 0 && p[0] == '*') match[0][1] = true;
    
    for(int i=1 ; i < str.length() ; i++){
        for(int j=1 ; j < match[0].length ; j++){
        
            if(p[j-1] == s[i-1] || p[j-1]=='?'){
                match[i][j] = match[i-1][j-1];
            }
            
            else if(p[j-1] == '*'){
                match[i][j] = (match[i-1][j] || match[i][j-1]);
            }
        }
    }
    
    return match[s.length][index];
    
}

//This code can be further optimized - Instead of using a (n*m) matrix we can use a (2*m) matrix
//where n = length(string) and m = length(pattern)


/*
Time and Space Complexity - O(n*m)
*/

