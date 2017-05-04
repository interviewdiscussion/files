//print binary tree's boundary in clock-wise order
public class Solution {
    public static List<Integer> printBinaryTreeBoundary(TreeNode root) {
        if (root == null) {
            return new ArrayList<>();
        }
        List<TreeNode> leaves = new ArrayList<>();
        List<TreeNode> right = new ArrayList<>();
        List<TreeNode> left = new ArrayList<>();
        getLeaves(leaves, root);//get leaves from rightmost to leftmost

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            left.add(queue.peek());//put each level's leftmost node to left
            for (int i = 1; i <= size; i++) {
                TreeNode curr = queue.poll();
                if (curr.left != null) {
                    queue.add(curr.left);
                }
                if (curr.right != null) {
                    queue.add(curr.right);
                }
                if (i == size) {//put each level's rightmost node to right
                    right.add(curr);
                }
            }
        }

        Collections.reverse(left);//reverse left cuz we need left boundary's nodes to be in bottom-up order
        right.addAll(leaves);//put leaves after right boundary's nodes
        right.addAll(left);//put left boundary's nodes after leaves
        List<Integer> res = new ArrayList<>();
        HashSet<TreeNode> visited = new HashSet<>();
        for (TreeNode node : right) {
            if (visited.contains(node)) {
                continue;//sometimes leftmost node==rightmost node(only 1 node in the level),we only print rightmost one
            }
            visited.add(node);
            res.add(node.val);
        }
        return res;
    }

    private static void getLeaves(List<TreeNode> leaves, TreeNode root) {
        if (root == null) {
            return;
        }
        if (root.left == null && root.right == null) {
            leaves.add(root);
            return;
        }
        getLeaves(leaves, root.right);//put right leaves at the front
        getLeaves(leaves, root.left);//left leaves at the back
    }
}
//test case:
        /**
         * *        7
         *      3      6
         *    2   4
         *  1  5    8
         *  */
        TreeNode root = new TreeNode(7);
        root.right = new TreeNode(6);
        root.left = new TreeNode(3);
        root.left.left = new TreeNode(2);
        root.left.left.left = new TreeNode(1);
        root.left.right = new TreeNode(4);
        root.left.right.right = new TreeNode(8);
        root.left.left.right = new TreeNode(5);
        List<Integer> list = printBinaryTreeBoundary(root);
        for (int i : list) {
            System.out.print(i + " ");
        }