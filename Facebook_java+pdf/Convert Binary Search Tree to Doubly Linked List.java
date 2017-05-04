/**
 * Definition of TreeNode:
 * public class TreeNode {
 *     public int val;
 *     public TreeNode left, right;
 *     public TreeNode(int val) {
 *         this.val = val;
 *         this.left = this.right = null;
 *     }
 * }
 */ 
// We don't need to create DoublyListNode, we only modify left->prev,right->next
public class Solution {
    /**
     * @param root: The root of tree
     * @return: the head of doubly list node
     */
    private TreeNode prev;
    
    public TreeNode bstToDoublyList(TreeNode root) {  
        TreeNode dummy = new TreeNode(0);
        prev = dummy;
        helper(root);
        // if circular, add:
        // prev.right = dummy.right;
        // dummy.right.left = prev;
        return dummy.right;
    }
    
    private void helper(TreeNode root) {
        if (root == null) {
            return;
        }
        helper(root.left);
        root.left = prev;//connect curr node with prev node
        prev.right = root;
        prev = root;//update prev
        helper(root.right);
    }
}
// recursive and iterative are all O(n) time, O(h) space
public class Solution {
    /**
     * @param root: The root of tree
     * @return: the head of doubly list node
     */
    
    public TreeNode bstToDoublyList(TreeNode root) {  
        TreeNode dummy = new TreeNode(0);
        TreeNode prev = dummy;
        Stack<TreeNode> stack = new Stack<>();
        while (root != null || !stack.empty()) {
            while (root != null) {
                stack.push(root);
                root = root.left;
            }
            root = stack.pop();
            prev.right = root;
            root.left = prev;
            prev = root;//remember to update the prev !!!
            root = root.right;//we should root=root.right even if it's null!!!
        }
        // if circular, add:
        // prev.right = dummy.right;
        // dummy.right.left = prev;
        return dummy.right;
    }
}