//Leetcode 210. Course Schedule II
//Question - https://leetcode.com/problems/course-schedule-ii/
//Kahn's Topological Sort

class Solution {
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        int result[] = new int[numCourses];
        int cntVertex = 0;
        Queue<Integer> inDegree0 = new LinkedList<>();
        int inDegree[] = new int[numCourses];
        Arrays.fill(inDegree,0);
        
        for(int i=0 ; i<prerequisites.length ; i++){
            inDegree[prerequisites[i][0]]++;
        }
        
        for(int i=0 ; i<numCourses ; i++){
            if(inDegree[i]==0) inDegree0.add(i);
        }
        
        while(!inDegree0.isEmpty()){
            int curr = inDegree0.poll();
            ArrayList<Integer> neighbours = findNeighbours(prerequisites,curr);
            result[cntVertex] = curr;
            
            for(int neighbour : neighbours){
                if(--inDegree[neighbour]==0) inDegree0.add(neighbour);
            }
            cntVertex++;
        }
        
        if(cntVertex!=numCourses) return (new int[0]);
        else return result;
    }
    
    public ArrayList<Integer> findNeighbours(int[][] prerequisites, int v){
        ArrayList<Integer> neighbours = new ArrayList<Integer>();
        for(int i=0;i<prerequisites.length;i++){
            if(prerequisites[i][1]==v) neighbours.add(prerequisites[i][0]);
        }
        return neighbours;
    }
}
