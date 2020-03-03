//Leetcode 100. Same Tree
//Question - https://leetcode.com/problems/same-tree/

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
    public boolean isSameTree(TreeNode p, TreeNode q) {
        if(p==null && q==null) return true;
        else if((p==null && q!=null) || (p!=null && q==null)) return false;
        else if(p.val==q.val){
            boolean b1 = isSameTree(p.left,q.left);
            boolean b2 = isSameTree(p.right,q.right);
            return (b1 & b2);
        }
        else return false;
    }
}
