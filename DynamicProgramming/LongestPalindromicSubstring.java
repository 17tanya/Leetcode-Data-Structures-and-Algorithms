//LeetCode 5. Longest Palindromic Susbtring
//Question - https://leetcode.com/problems/longest-palindromic-substring/

class Solution {
    public String longestPalindrome(String s) {
        int n = s.length();
        if(n == 0) return s;
        
        //isPalin[i][j] indicates if the substring s(i...j) is a palindrome or not
        boolean isPalin[][] = new boolean[n][n];
        /*
        A string is a palindrome if - 
            1. Length of string is 0 or 1
            2. If length of string is 2 then s[i] == s[j]
            3. If length of string is greater than or equal to 3, check
                3.1. if s[i] == s[j]
                3.2. if dp[i+1][j-1] is true, ie. s[i+1...j-1] is a palindrome or not
        
        For the 3rd condition it can be seen that for a problem s[i...j] the subproblem is s[i+1...j-1]
        To solve the problem, subproblem must be pre-computed.
        So, the outer loop starts in a reverse way, ie. from n...0 (i+1 for each i)
        The inner loop starts from i and moves up to n, ie. i...n
        
        Using a nested loop we are considering all substrings.
        */
        String res = null;
        
        for(int i = n - 1 ; i >= 0 ; i--){
            for(int j = i ; j < n ; j++){
                //condition (2) and (3)
                isPalin[i][j] = (s.charAt(i) == s.charAt(j)) && ((j - i + 1 < 3) || isPalin[i+1][j-1]);
                
                //storing the longest palindromic substring
                if(isPalin[i][j] && (res == null || j - i + 1 > res.length())){
                    res = s.substring(i, j+1);
                }
            }    
        }
        
        return res;
    }
}
