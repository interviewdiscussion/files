/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
public class Solution {
    private int count=0;
    private int res=0;
    public int kthSmallest(TreeNode root, int k) {
        count=k;
        helper(root);
        return res;
    }
    public void helper(TreeNode root){
        if(root.left!=null) helper(root.left);
        count--;
        if(count==0){
            res=root.val;
            return;
        }
        if(root.right!=null) helper(root.right);
    }
    
}