//Leetcode 207. Course Schedule
//Question - https://leetcode.com/problems/course-schedule/
//Used Kahn's Topological Sort

class Solution {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        
        /*Input Description - 
            numCourses ==> number of nodes in the graph
            prerequisites ==> 1. each row is a pair of vertices
                              2. represents edges in the graph
                              3. edge direction is prerequisites[row][1] --> prerequisites[row][0]
                              4. NOTE - (prerequisites.length != numCourses) because (prerequisites.length==number of edges) & (numCourses==vertices)
        */
        
        
        int inDegree[] = new int[numCourses]; //keep track of indegrees of all nodes
        Arrays.fill(inDegree,0);
        
        for(int i=0;i<prerequisites.length;i++){
            inDegree[prerequisites[i][0]]++;
        }
        
        Queue<Integer> inDegree0 = new LinkedList<>(); //to add vertices of inDegree 0 
        for(int i=0;i<numCourses;i++){
            if(inDegree[i]==0) inDegree0.add(i);
        }
        
        int vertexCnt = 0; //needed to check if cycle present or not
        
        /*for a cyclic component in a garph the inDegree will never be 0 ==> vertices in that component will never be added in the queue ==>
        vertexCnt will not increment for those vertices ==> (vertexCnt != numCourses)
        */
        
        while(!inDegree0.isEmpty()){
            int curr = inDegree0.poll();
            ArrayList<Integer> neighbours = findNeighbours(prerequisites,curr); //removing this vertex from graph
            
            for(int neighbour : neighbours){
            //reflecting the change after removing the vertex
                if(--inDegree[neighbour]==0) inDegree0.add(neighbour);
            }
            
            vertexCnt++;
        }
        
        if(vertexCnt==numCourses) return true; //no cycles in graph
        else return false; //cycles in garph
        
    }
    
    public ArrayList<Integer> findNeighbours(int[][] prerequisites,int v){
        ArrayList<Integer> neighbour = new ArrayList<>();
        for(int i=0 ; i<prerequisites.length ; i++){
            if(prerequisites[i][1]==v) neighbour.add(prerequisites[i][0]);
        }
        return neighbour;
    }
}
