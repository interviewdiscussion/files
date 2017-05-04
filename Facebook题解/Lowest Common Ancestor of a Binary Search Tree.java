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
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || p == null || q == null) return null;//to avoid overflow, use (long)
        while ((root.val - (long)p.val) * (root.val - (long)q.val) > 0) {//check the position of p&q related to root
            root = root.val > p.val ? root.left : root.right;//see p&q are in the left/right subtree
        }
        return root;
    }
}
//Walk down from the whole tree's root as long as both p&q are in the same subtree (which means both p&q are < or > root.val)
//Once root is between p&q, or root becomes one of p&q, the product of the diff will be <= 0
// iterative
// O(smallest depth of a and b) time, instead of O(num of nodes in tree)
// O(1) space, instead of O(height of tree)

// recursive O(smallest depth of a and b) space
public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
    return (root.val - p.val) * (root.val - q.val) < 1 ? root :
           lowestCommonAncestor(p.val < root.val ? root.left : root.right, p, q);
    }
}