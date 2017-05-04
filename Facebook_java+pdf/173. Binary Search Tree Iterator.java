/**
 * Definition for binary tree
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
//inorder iterator
      1
    2   3
  4  5 
  
  
Inorder   4 2 5 1 3 

Stack 1 2 4 
      1 2      return 4 
      1        return 2 -> return 5 
               retunr 1 -> return 3
public class BSTIterator {
    private Stack<TreeNode> stack;//O(h) space
    private TreeNode curr;

    public BSTIterator(TreeNode root) {
        stack = new Stack<>();
        curr = root;
    }

    /** @return whether we have a next smallest number */
    public boolean hasNext() {
        if (curr != null || !stack.empty()) {
            return true;
        }
        return false;
    }

    /** @return the next smallest number */
    public int next() {//amortized O(1) time
        while (curr != null) {
            stack.push(curr);
            curr = curr.left;
        }
        curr = stack.pop();
        int val = curr.val;
        curr = curr.right;//remember this
        return val;
    }
    
    public List<Integer> all() {
        List<Integer> res = new ArrayList<>();
        while (curr != null || !stack.empty()) {
            while (curr != null) {
                stack.push(curr);
                curr = curr.left;
            }
            curr = stack.pop();
            res.add(curr.val);
            curr = curr.right;
        }
        return res;
    }
}

//preorder iterator
public class preorderIterator {
    private Stack<TreeNode> stack;//O(h) space

    public preorderIterator(TreeNode root) {
        stack = new Stack<>();
    }

    /** @return whether we have a next smallest number */
    public boolean hasNext() {
        return !stack.empty();
    }

    /** @return the next smallest number */
    public int next() {
        return stack.pop().val;
    }
    
    public List<Integer> all() {
        List<Integer> res = new ArrayList<>();
        while (!stack.empty()) {
            TreeNode curr = stack.pop();
            res.add(curr.val);
            if (curr.right != null) {
                stack.push(curr.right);
            }
            if (curr.left != null) {
                stack.push(curr.left);
            }
        }
        return res;
    }
}


//postorder iterator
public class postorderIterator {
    private Stack<TreeNode> stack;//O(h) space
    private TreeNode prev;

    public postorderIterator(TreeNode root) {
        stack = new Stack<>();
        prev = null;
    }

    /** @return whether we have a next smallest number */
    public boolean hasNext() {
        return !stack.empty();
    }

    /** @return the next smallest number */
    public int next() {//amortized O(1) time
        int val = 0;
        TreeNode curr = stack.peek();
        if (prev == null || prev.left == curr || prev.right == curr) {
            if (curr.left != null) {
                stack.push(curr.left);
            } else if (curr.right != null) {
                stack.push(curr.right);
            }
        } else if (curr.left == prev) {
            stack.push(curr.right);
        } else {
            val = curr.val;
            stack.pop();
        }
        prev = curr;
        return val;
    }
    
    public List<Integer> all() {
        List<Integer> res = new ArrayList<>();
        while (!stack.empty()) {
            TreeNode curr = stack.peek();
            if (prev == null || prev.left == curr || prev.right == curr) {
                if (curr.left != null) {
                    stack.push(curr.left);
                } else if (curr.right != null) {
                    stack.push(curr.right);
                }
            } else if (curr.left == prev) {
                stack.push(curr.right);
            } else {
                res.add(curr.val);
                stack.pop();
            }
            prev = curr;
        }
        return res;
    }
}
/**
 * Your BSTIterator will be called like this:
 * BSTIterator i = new BSTIterator(root);
 * while (i.hasNext()) v[f()] = i.next();
 */