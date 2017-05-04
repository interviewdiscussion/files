//recursive O(n) time, O(h) space
//top down
public class Solution {
    public int minDepth(TreeNode root) {
        if (root == null) {//corner case !!!
            return 0;
        }
        if (root.left == null && root.right == null) {
            return 1;//should be 1, not 0 !!!
        }
        if (root.left == null) {
            return minDepth(root.right) + 1;
        }
        if (root.right == null) {
            return minDepth(root.left) + 1;
        }
        return Math.min(minDepth(root.left), minDepth(root.right)) + 1;
    }
}
public class Solution {
    public int minDepth(TreeNode root) {
        if(root==null) return 0;
        if(root.left==null||root.right==null)
            return Math.max(minDepth(root.left),minDepth(root.right))+1;
        return Math.min(minDepth(root.left),minDepth(root.right))+1;
    }
}
//iterative O(n) time, O(n) space
public class Solution {
    public int minDepth(TreeNode root) {
        if (root == null) {//corner case !!!
            return 0;
        }
        int depth = 0;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            depth++;
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                if (node.left == null && node.right == null) {
                    return depth;//we should directly return the first leaf's depth, not break !!!! we have two loops!!
                }
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
        }
        return depth;
    }
}


//bottom up
public class Solution {
    public int minDepth(TreeNode root) {
        if (root == null) {//corner case !!!
            return 0;
        }
        if (root.left == null && root.right == null) {
            return 1;//should be 1, not 0 !!!
        }
        int left = Integer.MAX_VALUE;
        int right = Integer.MAX_VALUE;
        if (root.left != null) {
            left = minDepth(root.left);
        }
        if (root.right != null) {
            right = minDepth(root.right);
        }
        return Math.min(left, right) + 1;
    }
}