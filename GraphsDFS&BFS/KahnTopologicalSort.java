//Reference - https://www.geeksforgeeks.org/topological-sorting-indegree-based-solution/

/*Algorithm - 
Step-1: Compute in-degree (number of incoming edges) for each of the vertex present in the DAG and initialize the count of visited nodes as 0.

Step-2: Pick all the vertices with in-degree as 0 and add them into a queue (Enqueue operation)

Step-3: Remove a vertex from the queue (Dequeue operation) and then.

    Increment count of visited nodes by 1.
    Decrease in-degree by 1 for all its neighboring nodes.
    If in-degree of a neighboring nodes is reduced to zero, then add it to the queue.

Step 5: Repeat Step 3 until the queue is empty.

Step 5: If count of visited nodes is not equal to the number of nodes in the graph then the topological sort is not possible for the given graph.
*/

class TopologicalSort{
    int vertices;
    ArrayList<Integer> adjList[];
    //adjList[2] signifies all the neighbours of vertex 2. The edge is directed from 2 ==> (all elements in adjList[2])
    
    topologicalSort(int n){
        vertices = n;
        adjList = new ArrayList[n];
        for(int i=0;i<n;i++) adjList[i] = new ArrayList<Integer>();
    }
    
    void topologicalSort(){
        int inDegree[] = new int[vertices]; 
        ArrayList<Integer> sortOrder = new ArrayList<>();
        Arrays.fill(inDegree,0);
        
        for(int i=0 ; i<vertices ; i++){
            ArrayList<Integer> neighbours = adjList[i];
            for(int neighbour : neighbours){
                inDegree[neighbour]++;
            }
        }
        
        Queue<Integer> inDegree0 = new LinkedList<>();
        for(int i=0;i<vertices;i++){
            if(inDegree[i]==0) inDegree0.add(i);
        }
        
        int vertexCnt = 0;
        while(!inDegree0.isEmpty()){
            int curr = inDegree0.poll();
            sortOrder.add(curr);
            ArrayList<Integer> neighbours = adjList[curr];
            for(int neighbour : neighbours){
                if(--inDegree[neighbour]==0) inDegree0.add(neighbour);
            }
            vertexCnt++;
        }
        
        if(vertexCnt!=vertices) System.out.println("Cycles in the graph");
        else{
            for(int i=0;i<vertices;i++) System.out.println(sortOrder.get(i));
        }
    }
    
}
