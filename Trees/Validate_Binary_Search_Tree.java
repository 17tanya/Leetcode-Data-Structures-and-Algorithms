//Leetcode 98. Validate Binary Search Tree
//Question - https://leetcode.com/problems/validate-binary-search-tree/

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
    public boolean isValidBST(TreeNode root) {
        Stack<TreeNode> stk = new Stack<TreeNode>();
        
        TreeNode prev = null;
        
        while(root!=null || stk.size()!=0){
            
            //keep going left
            while(root!=null){
                stk.push(root);
                root = root.left;
            }
            
            root = stk.pop();
            //if(prev!=null) System.out.println(prev.val+" "+root.val);
            if(prev!=null && prev.val>=root.val) return false;
            prev = root;
            root = root.right;
            
        }
        
        return true;
        
    }
}
