/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
// without parent pointer: 
// O(num of nodes in tree) time
// O(height of tree) space
public class Solution {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || root == p || root == q) {//if root == null or we find any one of the two, return root
            return root;
        }
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);
        if (left != null && right != null) {//if we find both of them, LCA will be curr root
            return root;
        }
        if (left != null) {//we found any one of the two, return it
            return left;
        }
        if (right != null) {//we found any one of the two, return it
            return right;
        }
        return null;//we found nothing
    }
}

/**
 * Definition of ParentTreeNode:
 * 
 * class ParentTreeNode {
 *     public ParentTreeNode parent, left, right;
 * }
 */
// with parent node
// if nodes can be not in same tree, this solution can still be used
// O(deepest depth of a and b) time, instead of O(num of nodes in tree)
// O(1) space, instead of O(height of tree)
public class Solution {
    /**
     * @param root: The root of the tree
     * @param A, B: Two node in the tree
     * @return: The lowest common ancestor of A and B
     */
    public ParentTreeNode lowestCommonAncestorII(ParentTreeNode root,
                                                 ParentTreeNode a,
                                                 ParentTreeNode b) {
        int pathA = getPathToRoot(a);//get depth
        int pathB = getPathToRoot(b);
        if (pathA > pathB) {//move up to the same level with the other node
            a = upToSameLevel(a, pathA - pathB);
        }
        if (pathB > pathA) {
            b = upToSameLevel(b, pathB - pathA);
        }
        int pathLength = Math.min(pathA, pathB);
        ParentTreeNode res = null;
        while (pathLength > 0) {//move up from a&b to root
            if (a == b) {
                res = a;
                break;//the first same node is the LCA
            }
            a = a.parent;
            b = b.parent;
            pathLength--;
        }
        return res;
    }
    
    private int getPathToRoot(ParentTreeNode node) {
        int path = 0;
        while (node != null) {//move up to the root with parent node
            path++;
            node = node.parent;
        }
        return path;
    }
    
    private ParentTreeNode upToSameLevel(ParentTreeNode node, int step) {
        while (step > 0) {//move up to the same level with the other node
            node = node.parent;
            step--;
        }
        return node;
    }
}

// if nodes can be not in curr tree, no parent node:
// O(num of nodes in tree) time
// O(height of tree) space
class ResultType {
    public boolean exist_a;//should be public cuz this class is not in the other
    public boolean exist_b;
    public TreeNode node;
    public ResultType(boolean a, boolean b, TreeNode root) {
        exist_a = a;
        exist_b = b;
        node = root;
    }
}

public class Solution {
    /**
     * @param root The root of the binary tree.
     * @param A and B two nodes
     * @return: Return the LCA of the two nodes.
     */
    public TreeNode lowestCommonAncestor3(TreeNode root, TreeNode a, TreeNode b) {
        ResultType res = helper(root, a, b);
        if (res.exist_a && res.exist_b) {//return node only when two nodes found
            return res.node;
        }
        return null;//one/both of a/b does not exist
    }
    
    private ResultType helper(TreeNode root, TreeNode a, TreeNode b) {
        if (root == null) {
            return new ResultType(false, false, null);
        }
        ResultType left = helper(root.left, a, b);
        ResultType right = helper(root.right, a, b);
        boolean exist_a = left.exist_a || right.exist_a || root == a;//found a
        boolean exist_b = left.exist_b || right.exist_b || root == b;//found b
        if (root == a || root == b || (left.node != null && right.node != null)) {
            return new ResultType(exist_a, exist_b, root);
        }//if root is a or b, or both a and b have been found from subtrees
        if (left.node != null) {
            return new ResultType(exist_a, exist_b, left.node);
        }//if only left subtree found a node
        if (right.node != null) {
            return new ResultType(exist_a, exist_b, right.node);
        }//if only right subtree found a node
        return new ResultType(false, false, null);//if none of them found
    }
}