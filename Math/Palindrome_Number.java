//LeetCode 9. Palindrome Number
//Question - https://leetcode.com/problems/palindrome-number/

class Solution {
    public boolean isPalindrome(int x) {
        if(x < 0 || (x != 0 && x % 10 == 0)) return false;
        
        int rev = 0;
        while(x > rev){
            rev = rev * 10 + (x % 10);
            x = x / 10;
        }
        
        /*
        Two conditions to check if the number is a palindrome - 
        1. even length number ==> x == rev
        2. odd length number ==> x == rev/10
        */
        return (x == rev || x == rev / 10);
    }
}