// find LCA of all deepest leaves in a tree(can be a multiway tree)
// O(n) time (n is the num of nodes in tree), O(h) space
public class Solution {
    public class TreeNode {
        int val;
        int maxDepth;//this means the maxDepth of curr treenode-rooted (sub)tree
        ArrayList<TreeNode> children;
        TreeNode(int x) {
            val = x;
            maxDepth = 0;
            children = new ArrayList<>();
        }
    }
    
    public TreeNode lowestCommonAncestor(TreeNode root) {
        if (root == null || root.children == null || root.children.isEmpty()) {//if root is null,or it's a leaf,return null
            return null;
        }
        int currMaxDepth = 0;//curr tree's deepest leaf depth
        int countMaxDepth = 0;//num of deepest leaves
        TreeNode node = null;
        for (TreeNode child : root.children) {
            TreeNode temp = lowestCommonAncestor(child);
            if (temp == null) {
                continue;
            } else if (temp.maxDepth > currMaxDepth) {//if deeper leaf found,update everything to that deeper leaf
                currMaxDepth = temp.maxDepth;
                node = temp;//update the maxDepth leaf/LCA
                countMaxDepth = 1;//reset count of maxDepth leaves
            } else if (temp.maxDepth == currMaxDepth) {
                countMaxDepth++;//more deepest leaves of curr (sub)tree found
            }
        }
        if (countMaxDepth > 1) {
            //if there're several leaves at the deepest level of curr tree,curr root is the LCA of them
            //OR if there're several LCA of several deepest leaves in curr tree,curr root is also the LCA of them
            root.maxDepth = node.maxDepth + 1;//update root's maxDepth and return it
            return root;
        } else if (countMaxDepth == 1) {
            //if there's only 1 deepest leaf or only 1 LCA of curr tree,return that leaf/LCA
            node.maxDepth++;//update node's maxDepth and return it
            return node;
        } else if (countMaxDepth == 0) {
            //if curr root's children have no children(all leaves,so all return null to temp),set root's maxDepth to 2,return
            root.maxDepth = 2;//update node's maxDepth to 2 cuz its children are leaves
            return root;
        }
    }
}
//http://www.1point3acres.com/bbs/thread-148413-1-1.html