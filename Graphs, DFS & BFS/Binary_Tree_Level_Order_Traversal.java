// Leetcode 102. Binary Tree Level Order Traversal
// Question - https://leetcode.com/problems/binary-tree-level-order-traversal/

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if(root==null) return result;
        Queue<TreeNode> q = new LinkedList<TreeNode>();
        
        TreeNode curr = null;
        q.add(root);
        
        while(q.isEmpty()==false){
            int nodesAtCurrLevel = q.size();
            //System.out.println(nodesAtCurrLevel);
            List<Integer> layer = new ArrayList<>();
            for(int i = 0 ; i < nodesAtCurrLevel ; i++){
                curr = q.poll();
                layer.add(curr.val);
                if(curr.left!=null) q.add(curr.left);
                if(curr.right!=null) q.add(curr.right);
            }
            result.add(layer);
        }
        
        return result;
        
    }
}
