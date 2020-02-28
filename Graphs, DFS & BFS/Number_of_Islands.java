// Leetcode 200. Number of Islands
// Question - https://leetcode.com/problems/number-of-islands/ 

class Solution {
    public int numIslands(char[][] grid) {
        int ans = 0;
        for(int i=0;i<grid.length;i++){
            for(int j=0;j<grid[0].length;j++){
                if(grid[i][j]=='1'){
                    ans++;
                    dfs(grid,i,j);
                }
            }
        }
        
        return ans;
    }
    
    void dfs(char[][] grid, int x, int y){
        int rows = grid.length;
        int cols = grid[0].length;
        
        //check for validity of x and y
        //continue DFS only if the current cell holds a value '1'
        if(x<0 || y<0 || x>=rows || y>=cols || grid[x][y]!='1') return;
        
        //make changes inplace instead of using a separate array to save memory
        grid[x][y] = '*';
        
        dfs(grid,x+1,y);
        dfs(grid,x-1,y);
        dfs(grid,x,y+1);
        dfs(grid,x,y-1);
    }
}
