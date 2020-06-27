//LeetCode 79. Word Search
//Question - https://leetcode.com/problems/word-search/

class Solution {
    public boolean exist(char[][] board, String word) {
        
        for(int i = 0 ; i < board.length ; i++){
            for(int j = 0 ; j < board[0].length ; j++){
                if(helper(board, i, j, word, 0)) return true;
            }
        }
        return false;
    }
    
    public boolean helper(char[][] b, int x, int y, String s, int end){
        //Base Cases
        if(end == s.length()) return true;
        
        if(x < 0 || x >= b.length || y < 0 || y >= b[0].length) return false;
        if(b[x][y] != s.charAt(end)) return false;
        
        //Mark as visited
        b[x][y] = '#';
        
        //go deeper into recursion
        boolean res = helper(b, x+1, y, s, end+1) ||
                      helper(b, x-1, y, s, end+1) ||
                      helper(b, x, y+1, s, end+1) ||
                      helper(b, x, y-1, s, end+1);
        //backtarck
        b[x][y] = s.charAt(end);
        
        return res;
    }
}
