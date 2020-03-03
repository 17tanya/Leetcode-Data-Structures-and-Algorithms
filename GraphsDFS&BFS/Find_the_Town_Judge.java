//Leetcode 997. Find the Town Judge
//Question - https://leetcode.com/problems/find-the-town-judge/

class Solution {
    public int findJudge(int N, int[][] trust) {
        ArrayList<ArrayList<Integer>> list = new ArrayList<ArrayList<Integer>>();
        
        int n = trust.length;
        
        for(int i=0;i<=N;i++){
            list.add(new ArrayList<Integer>());
        }
        
        for(int i=0;i<n;i++){
            int u = trust[i][0];
            int v = trust[i][1];
            
            list.get(u).add(v);
            
        }
        
        ArrayList<Integer> inDegZero = new ArrayList<Integer>();
        for(int i=1;i<=N;i++){
            if(list.get(i).size()==0){
                inDegZero.add(i);
            }
        }
        
        int flag = 0;
        int curr = -1;
        for(int i=0;i<inDegZero.size();i++){
            curr = inDegZero.get(i);
            flag = 0;
            for(int j=1;j<=N;j++){
                if(list.get(j).contains(curr)) flag++;
            }
            if(flag==N-1) break;
        }
        if(flag==N-1) return curr;
        else return -1;
    }
}
