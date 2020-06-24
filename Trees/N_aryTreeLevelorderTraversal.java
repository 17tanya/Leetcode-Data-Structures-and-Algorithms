//LeetCode 429. N-ary Tree Level Order Traversal
//Question - https://leetcode.com/problems/n-ary-tree-level-order-traversal/

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
    public List<List<Integer>> levelOrder(Node root) {
        List<List<Integer>> res = new ArrayList<>();
        if(root == null) return res;
        
        Queue<Node> q = new LinkedList<>();
        q.offer(root);
        
        while(!q.isEmpty()){
            int layerSize = q.size();
            List<Integer> l = new ArrayList<>();
            
            for(int i = 0 ; i < layerSize ; i++){
                Node node = q.poll();
                l.add(node.val);
                
                for(Node child : node.children){
                    q.offer(child);
                }
            }
            res.add(l);
        }
        
        return res;
    }
}
