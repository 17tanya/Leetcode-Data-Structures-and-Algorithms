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
    public int kthSmallest(TreeNode root, int k) {
        Stack<TreeNode> stk = new Stack<>();
        while(root!=null || !stk.isEmpty()){
            
            while(root!=null){
                stk.push(root);
                root = root.left;
            }
            
            root = stk.pop();
            if(--k==0) return root.val;
            root = root.right;
        }
        return -1;
    }
}