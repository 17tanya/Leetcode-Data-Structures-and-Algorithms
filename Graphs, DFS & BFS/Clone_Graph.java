// Leetcode 133. Clone Graph
// Question - https://leetcode.com/problems/clone-graph/

/*
// Definition for a Node.
class Node {
    public int val;
    public List<Node> neighbors;
    
    public Node() {
        val = 0;
        neighbors = new ArrayList<Node>();
    }
    
    public Node(int _val) {
        val = _val;
        neighbors = new ArrayList<Node>();
    }
    
    public Node(int _val, ArrayList<Node> _neighbors) {
        val = _val;
        neighbors = _neighbors;
    }
}
*/
class Solution {
    
    //to keep track of already explored nodes 
    HashMap<Integer,Node> map = new HashMap<Integer,Node>();
    
    public Node cloneGraph(Node node) {
        if(node==null) return null; //empty graph
        
        //if the node is already explored then return the newly created node which is stored in the map
        if(map.containsKey(node.val)) return map.get(node.val);
        
        //creating new node from the old node
        Node curr = new Node(node.val, new ArrayList<>());
        
        //adding value to the map
        //NOTE - the neighbours have not been added yet. This is because if we don't do this step here our code might get stuck in an infite recursion - this will heppen when there is a cycle.
        //example - Node 5 is neighbour of Node 6. Node 5 will call clone(Node 6) and Node(Clone 6) will call clone(Node 5) and so on...
        //to avoid this as soon as we create a new node of Node 5, we put it's object in our map. When Node 5 call clone(Node 6) and then Node 6 calls clone(Node 5) an object from the map is returned. Preventing code failure.
        map.put(node.val,curr);
        
        for(Node neighbor : node.neighbors){
            curr.neighbors.add(cloneGraph(neighbor));
        }
        
        return curr;
    }
}
