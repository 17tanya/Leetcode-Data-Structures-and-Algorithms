//LeetCode 590. N-ary Tree Postorder Traversal
//Question - https://leetcode.com/problems/n-ary-tree-postorder-traversal/

/*
// Definition for a Node.
class Node {
    public int val;
    public List<Node> children;

    public Node() {}

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, List<Node> _children) {
        val = _val;
        children = _children;
    }
};
*/

class Solution {
    
    List<Integer> result = new ArrayList<>();
    
    public List<Integer> postorderIterative(Node root) {
        List<Integer> res = new ArrayList<>();
        if(root == null) return res;
        
        /*
        Observation - 
            Pre-order = root, left, right
            Post-order = left, right, root
            
        Reversing pre-order, we get (right, left, root). This means for each node, 
        we start from the rightmost child and move to the leftmost child and keep
        recording the values.
        
        Reversing the recorded values would yield the post-order traversal.
        */
        
        Stack<Node> stk = new Stack<>();
        Stack<Integer> order = new Stack<>();
        
        stk.push(root);
        while(!stk.isEmpty()){
            Node node = stk.pop();
            
            //recoding the values of traversal
            order.push(node.val);
            
            //iterating from leftmost child. But we are using stack, the rightmost child
            //would be on the top.
            for(Node child : node.children){
                stk.push(child);
            }
        }
        
        //reversing the recorded values
        while(!order.isEmpty()){
            res.add(order.pop());
        }
        
        return res;
    }
    
    public List<Integer> postorderRecursive(Node root) {
        if(root == null) return result;
        
        List<Node> children = root.children;
        for(Node child : children){
            postorderRecursive(child);
        }
        
        result.add(root.val);
        return result;
    }
}
