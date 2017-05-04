/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
//recursive O(n) time, O(h) space
//top down
public class Solution {
    public int maxDepth(TreeNode root) {
        if (root == null) {//corner case !!!
            return 0;
        }
        if (root.left == null && root.right == null) {
            return 1;//should be 1, not 0 !!!
        }
        if (root.left == null) {
            return maxDepth(root.right) + 1;
        }
        if (root.right == null) {
            return maxDepth(root.left) + 1;
        }
        return Math.max(maxDepth(root.left), maxDepth(root.right)) + 1;
    }
}
//bottom up
public class Solution {
    public int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int left = maxDepth(root.left);
        int right = maxDepth(root.right);
        return Math.max(left, right) + 1;
    }
}

//iterative O(n) time, O(n) space
public class Solution {
    public int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int max = 0;
        int depth = 0;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            max = Math.max(max, ++depth);
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
        }
        return max;
    }
}