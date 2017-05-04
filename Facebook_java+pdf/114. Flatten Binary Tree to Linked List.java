public class Solution {
    public void flatten(TreeNode root) {
        while (root != null) {
            if (root.left == null) {
                root = root.right;//skip all the nodes that don't have left subtree
                continue;
            }
            TreeNode left = root.left;//if root has left subtree, get the left subtree
            while (left.right != null) {//find the rightmost node of left subtree
                left = left.right;
            }
            left.right = root.right;//connect the right pointer of the rightmost node in left subtree to the right subtree
            root.right = root.left;//root.left, not left !!! we insert entire left subtree between root and right subtree
            root.left = null;//let the left pointer become null
            root = root.right;//move to the next right node
        }
    }
}
        ==>       ==>
    1         1       1
 2   5         2       2
  3   6         3       3
 4             4 5       4
                  6       5
                            6
// if we don't need to make the list circular
// preorder traversal: O(n) time, O(h) space
public class Solution {
    TreeNode prev = null;
    
    public void flatten(TreeNode root) {
        if (root == null) {
            return;
        }
        if (prev != null) {//skip the first null prev node
            prev.right = root;//we move curr node to the prev node's right
            prev.left = null;//don't need to worry about losing original prev.right cuz we've already store it to "right"
        }
        prev = root;
        TreeNode right = root.right;//save root's right before flattening
        flatten(root.left);
        flatten(right);
    }
}

//iterative O(n) time, O(n) space
public class Solution {
    public void flatten(TreeNode root) {
        if(root==null) return;
        Stack<TreeNode> stack=new Stack<>();
        stack.push(root);
        while(!stack.isEmpty()){
            TreeNode cur=stack.pop();
            if(cur.right!=null) stack.push(cur.right);
            if(cur.left!=null) stack.push(cur.left);
            if(!stack.isEmpty()){
                cur.right=stack.peek();
            }
            cur.left=null;
        }
    }
}

// postorder traversal: O(n) time, O(h) space
public class Solution {
    private TreeNode prev = null;

    public void flatten(TreeNode root) {
        if (root == null) {
            return;
        }
        flatten(root.right);
        flatten(root.left);
        root.right = prev;
        root.left = null;
        prev = root;
    }
}


// preorder traversal, if we need to make the list circular: O(n) time, O(h) space
public class Solution {
    TreeNode prev = null;
    
    public void flatten(TreeNode root) {
        if (root == null) {
            return;
        }
        helper(root);
        prev.right = root;//make it circular
    }
    
    public void helper(TreeNode root) {
        if (root == null) {
            return;
        }
        if (prev != null) {//skip the first null prev node
            prev.right = root;//we move curr node to the prev node's right
            prev.left = null;//don't need to worry about losing original prev.right cuz we've already store it to "right"
        }
        prev = root;
        TreeNode right = root.right;//save root's right before flattening
        helper(root.left);
        helper(right);
    }
}