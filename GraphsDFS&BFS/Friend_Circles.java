//Leetcode 547. Friend Circles
// Question - https://leetcode.com/problems/friend-circles/


//This problem is similar to 'find the number of islands'. In that problem we were supposed to find the number of islands and the input was clearly given to us in the form of a matrix. We had to traverse the entire matrix looking for a 1 and then do DFS on all it's neighbours. Each time we find a 1 we will know that it forms a new island because if it was a part of another island it would already have been visited.

//Here the input matrix given has a different interpretation - but the question remains the same. Here we search through all the nodes(friends) to perform DFS whereas in the earlier question we searched throught the entire matrix to perform DFS.

//In the Earlier question each cell of the matrix was a node. 
//In this question the input matrix represents only the connection between nodes and not the physical graph. The nodes in this question are the total number of friends.

//We always perform DFS on nodes.
//Earlier question ==> nodes = each cell ==> DFS on each cell
//This question ==> nodes = num of friends ==> DFS for each friend (search for neighbours in matrix)


class Solution {
    public int findCircleNum(int[][] M) {
        int circle = 0;
        boolean visit[] = new boolean[M.length];
        Stack<Integer> friends = new Stack<Integer>();
        
        for(int i=0;i<M.length;i++){
            if(!visit[i]){
                circle++;
                friends.push(i);
                
                
                while(!friends.isEmpty()){
                    int current = friends.pop();
                    visit[current] = true;
                    
                    for(int j=0;j<M.length;j++){
                        if(!visit[j] && M[current][j]==1) friends.push(j);
                    }
                }
            }
        }
        return circle;
    }
}
