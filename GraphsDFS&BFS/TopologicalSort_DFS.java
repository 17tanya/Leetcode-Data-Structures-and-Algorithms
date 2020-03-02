//Reference - https://www.geeksforgeeks.org/topological-sorting/

/*Algorithms - 
1. Maintain a visited set and a stack to record the order of vertices
2. Starting from a vertex loop through all the vertices
  2.1 check if the vertex is present in the visited set
    (i) vertex in visited set ==> continue the loop
    (ii) vertex not in visited set ==> explore children of the current vertex recursivley ==> in a Depth First manner
         Exploring a vertex implies visiting it's children. When there are no children to explore push the vertex to the stack  
*/

class topologicalSort{
    int vertices;
    ArrayList<Integer> adjList[]; //adjacency list
    
    topologicalSort(int n){
        vertices = n;
        adjList = new ArrayList[n];
        for(int i=0;i<n;i++) adjList[i] = new ArrayList<Integer>();
    }
    
    void topo(){
        boolean viisted[] = new boolean[vertices]; //keep track of visited vertices
        Arrays.fill(visited,false);
        
        Stack<Integer> stk = new Stack<Integer>(); //keep track of the order
        
        for(int i=0;i<vertices;i++){
            if(!visited[i]) topoUtil(i,visited,stk);
        }
        
        //print out the topological sort
        while(!stk.isEmpty()){
            System.out.println(stk.pop());
        }
    }
    
    void topoUtil(int v,boolean visited[],Stack<Integer> stk){
        visited[v] = true;
        ArrayList<Integer> neighbours = adjList[v];
        
        for(int i=0 ; i<neighbours.size() ; i++){ //exploring neighbours
            int neighbour = neighbours.get(i);
            if(!visited[neighbour]) topoUtil(neighbour,visited,stk); //exploring neighbours in Depth First Manner
        }
        stk.push(v);
    }
    
}
