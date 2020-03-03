//Leetcode 1042. Flower Planting With No Adjacent
//Question - https://leetcode.com/problems/flower-planting-with-no-adjacent/

class Solution {
    
    public int[] gardenNoAdj(int N, int[][] paths) {
        ArrayList<ArrayList<Integer>> list = new ArrayList<ArrayList<Integer>>();
        int n = paths.length;
        
        int sol[] = new int[N];
        
        for(int i=0;i<=N;i++){
            list.add(new ArrayList<Integer>());
        }
        
        for(int i=0;i<n;i++){
            int u = paths[i][0];
            int v = paths[i][1];
            
            list.get(u).add(v);
            list.get(v).add(u);
        }
        
        int colors[] = {1,2,3,4};
        sol[0] = 1;
        
        for(int i=2;i<=N;i++){
            ArrayList<Integer> illegal = new ArrayList<Integer>();
            
            for(int j=0 ; j < list.get(i).size() ; j++){
                illegal.add(sol[list.get(i).get(j)-1]);
            }
            
            for(int c=0; c<4 ; c++){
                if(illegal.contains(colors[c])==false){
                    sol[i-1] = colors[c];
                    break;
                }
            }
        }
        return sol;
    }
    
}
