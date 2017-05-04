1,任意
//longest path in tree, any to any, dfs, recursive
public class Solution {
    private int max;
    
    public int longestPath(TreeNode root) {//divide and conquer solution, use a max to store the max path sum of whole tree
        max = Integer.MIN_VALUE;
        helper(root);
        return max;
    }
    
    private int helper(TreeNode root) {//a path must start at a node and end at a node(can be itself),the value that helper
        if (root == null) {//returns is the max path sum that pass curr root, but the maxValue we update is the max path sum
            return 0;//that connects curr root and left&right paths
        }
        int left = helper(root.left);
        int right = helper(root.right);
        max = Math.max(max, (left + right + 1));//a path connects left path and right path must pass through the root
        return Math.max(left, right) + 1;//return a max path that pass through curr root(assume root has a parent)
    }
}
2，叶子到叶子
public class Solution {
    private int max;
    public int longestPath(TreeNode root) {//divide and conquer solution, use a max to store the max path sum of whole tree
        max = Integer.MIN_VALUE;
        helper(root);
        return max;
    }
    
    private int helper(TreeNode root) {//a path must start at a node and end at a node(can be itself),the value that helper
        if (root == null) {//returns is the max path sum that pass curr root, but the maxValue we update is the max path sum
            return 0;//that connects curr root and left&right paths
        }
        int left = helper(root.left);
        int right = helper(root.right);
        max = Math.max(max, (left + right + 1));//a path connects left path and right path must pass through the root
        return Math.max(left, right) + 1;//return a max path that pass through curr root(assume root has a parent)
    }
}