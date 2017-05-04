// O(h) time and O(1) space
public class Solution {
    public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
        TreeNode successor = null;
        while (root != null) {
            if (root.val <= p.val) {//<=, not <
                root = root.right;
            } else {
                successor = root;
                root = root.left;
            }
        }
        return successor;
    }
}