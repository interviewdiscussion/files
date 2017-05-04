public class validBST{

	//recursive
	Integer pred = null;
     public boolean isValidBST(TreeNode root) { 
        if (root == null) return true;
        if (!isValidBST(root.left)) return false;
        // visit
        if (pred != null && root.val <= pred) return false;
        pred = root.val; // set
        if (!isValidBST(root.right)) return false;
        return true;
    }

     //use inorder traversal
    public boolean isValidBST(TreeNode root) {
        if (root == null) return true;
        List<Integer> result = new ArrayList<Integer>();
        inOrderList(root, result);
        for (int i = 0; i < result.size() - 1; i++) {
            if (result.get(i) >= result.get(i + 1)) {
                return false;
            }
        }
        return true;
    }
    
    public void inOrderList(TreeNode root, List<Integer> res) {
        if (root == null) return;
        inOrderList(root.left, res);
        res.add(root.val);
        inOrderList(root.right, res);
    }
}