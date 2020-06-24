//LeetCode 589. N-ary Preorder Traversal
//Question - https://leetcode.com/problems/n-ary-tree-preorder-traversal/

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

    public List<Integer> preorderIterative(Node root) {
        List<Integer> res = new ArrayList<>();
        if(root == null) return res;
        
        Stack<Node> stk = new Stack<>();
        stk.add(root);
        
        while(!stk.isEmpty()){
            Node node = stk.pop();
            res.add(node.val);
            
            List<Node> children = node.children;
            /*
            In pre-order traversal we travel from ==> root, left, right
            We simulate recursion using stack. Stack follows LIFO and so we must add
            the children from RIGHT to LEFT. This way the leftmost child remains at the top.
            */
            for(int i = children.size() - 1 ; i >= 0 ; i--){
                stk.push(children.get(i));
            }
        }
        return res;
    }
    
    public List<Integer> preorderRecursive(Node root) {
        if(root == null) return result;
        
        result.add(root.val);
        
        List<Node> children = root.children;
        for(Node child : children){
            preorderRecursive(child);
        }
        return result;
    }
}
