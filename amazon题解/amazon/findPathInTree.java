public class findPathInTree{
	public static void main(String[] args){

	}
    
    public static ArrayList<TreeNode> findPath(TreeNode root, TreeNode x, TreeNode y){
    	TreeNode lca = lca(root,x,y);
    	ArrayList<TreeNode> l1 = new ArrayList<TreeNode>();
    	ArrayList<TreeNode> l2 = new ArrayList<TreeNode>();
    	addToPath(l1, lca, x);
    	addToPath(l2, lca, y);

    	for(int i=l2.size()-1; i>=1; i--){
    		l1.add(l2.get(i));
    	}

    }


    //time O(n), space O(1)
	public static TreeNode lca(TreeNode root, TreeNode x, TreeNode y){
		if(root == null){
			return null;
		}

		if(root==x || root==y){
			return root;
		}

		TreeNode left_lca = lca(root.left, x, y);
		TreeNode right_cla = lca(root.right, x, y);
		if(left_lca!=null && right_lca!=null){
			return root;
		}
		return left_lca!=null ? left_lca : right_lca;
	}
    
    //time O(n)
	public static boolean addToPath(ArrayList<TreeNode> res, TreeNode root, TreeNode n){
		if(root == null){
			return false;
		}
		if(root==n || addToPath(res, root.left, n) || addToPath(res, root.right, n)){
			res.add(root);
			return true;
		}
		return false;
	}




}