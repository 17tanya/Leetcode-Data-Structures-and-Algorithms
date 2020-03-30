/*
https://www.techiedelight.com/find-minimum-cuts-needed-palindromic-partition-string/
https://youtu.be/WPr1jDh3bUQ
*/

//Bottom Up Dynamic Programming

//Pre Computation - Bottom Up DP to find if substring is a palindrome or not
public void findAllPlindromes(String s, boolean isPalin[][]){
    //We only use the values above the principal diagonal
    //isPalin[i][j] stores if s[i..j] is a palindrome or not
    
    //we start from the end because we require the value of (i+1).  
    for(int i = s.length() - 1 ; i >= 0 ; i--){
        for(int j = i ; j < s.length() ; j++){
            
            //single character is always a palindrome
            if(i == j) isPalin[i][j] = true;
            
            //if the border characters match --> check the inner string
            else if(s.charAt(i) == s.charAt(j)){
                //if the length of the substring being considered is 2 --> directly put true
                //else check if the inner string [i+1 ... j-1] is a palindrome or not
                isPalin[i][j] = (j-i == 1) ? true : isPalin[i+1][j-1];
            }
            
            //when the border characters don't match --> not possible to be a palindrome
            else isPlain[i][j] = false;
        }
    }
    
}

public int minCutsPlainPartition(String s, boolean isPlain[][]){
    int n = s.length();
    
    //cuts[i] indicates the minimum cuts required in s[0...i] to form palindrome partition
    int cuts[] = new int[n];
    
    for(int i=0 ; i < n ; i++){
        //check if the current substring is partition --> if it is a palindrome we need 0 cuts
        if(isPlain[0][i]) cuts[i] = 0;
        else{
            int minCuts = Integer.MAX_VALUE;
            
            //we partition the substring s[0...i] at j into two substrings --> s[0...j] and s[j+1...i]
            //since we need to make palindromic partitions, we check if the 2nd substring s[j+1...i] is a palindrome or not
            //we are basically CHOOSING A PARTITION(j) WHICH KEEPS THE 2nd SUBSTRING AS IT IS(bacuse it is a palindrome) AND 
            //DECOMPOSES THE 1ST SUBSTRING INTO SUBPROBLEMS TO FIND MINIMUM CUTS
            for(int j=0 ; j < i ; j++){ //j creates the partition
            
                //1. Check if 2nd substring is a palindrome
                //2. solve the subproblem --> we are using the bottom up appraoch, subproblem is already solved
                //   subproblem is s[0...j] --> we solve it by considering cuts[j]
                //3. Consider all valid partitions and chose the cheapest
                if(isPalin[j+1][i] && minCuts > cuts[j]+1){
                    minCuts = cuts[j] + 1;
                }
            }
            cuts[i] = minCuts;
        }
    }
    
    //result for minimum cuts of s[0...n-1]
    return cuts[n-1];
}

/*
Time Complexity - O(n^2)
Space Complexity - O(n^2) [n^2 to store isPlain and n to store cuts --> isPlain is more]
*/

