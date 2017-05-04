/**
 * Problem: find the first common ancestor of two nodes in a binary tree
 *          avoid store additional nodes in data stucture
 * Solution 1: use links to parents
 * Solution 2: if no link to parents, keep branching from the root until
 *             two given nodes are on different subtree
 * Solution 3: if no link to parents, define a function that 
 *             a) return x(y) if subtree has x but not y(x)
 *             b) return null if subtree doesn't have both x and y
 *             c) otherwise, return common ancestor
 */

public class commonAncestor{
	public static void main(String[] args){
		TreeNode[] nodes = new TreeNode[8];
		for(int i=0; i<nodes.length; i++){
			nodes[i] = new TreeNode(i);
		}
		nodes[0].setChild(nodes[1],nodes[2]);
		nodes[1].setChild(nodes[3],nodes[4]);
		nodes[4].setChild(nodes[5],nodes[6]);
        TreeNode ancestor = ancestor2(nodes[0],nodes[3],nodes[6]);
        if(ancestor != null){
        	System.out.println(ancestor.print());
        }
	}
    
    //assuming tree node has a pointer to its parent
	public static TreeNode ancestor1(TreeNode root, TreeNode x, TreeNode y){
		if(root == null){
			return null;
		}
		if(root==x || root==y){
			return root;
		}
		while(x.parent!=null || y.parent!=null){
			if(x.parent != null){
				if(x.parent.visited){
					return x.parent;
				}else{
					x.parent.visit();
					x = x.parent;
				}
			}
			if(y.parent != null){
				if(y.parent.visited){
					return y.parent;
				}else{
					y.parent.visit();
					y = y.parent;
				}
			}
		}
		return null;
	}

	//keep branching until two given nodes are on differnt subtree
	public static TreeNode ancestor2(TreeNode root, TreeNode x, TreeNode y){
		if(root == null){
			return null;
		}
		if(root==x || root==y){
			return root;
		}
		boolean xleft = inTree(root.left, x);
		boolean yleft = inTree(root.left, y);
		//x and y are in diffferent subtree
		if(xleft != yleft){
			return root;
		}
		//determine which subtree to branch on
		if(xleft){
			root = root.left;
		}else{
			root = root.right;
		}
		//branch on the subtree if x and y are on same side
		return ancestor2(root,x,y);
	}
    

    //determine if a node is in the tree
	public static boolean inTree(TreeNode root, TreeNode n){
		if(root == null){
			return false;
		}
		if(root == n){
			return true;
		}
		//node is in tree if it is either in left/right subtree
		return inTree(root.left,n) || inTree(root.right, n);
	}
    

	public static TreeNode ancestor3(TreeNode root, TreeNode x, TreeNode y){
		//first check if x and y are in the tree
		if(inTree(root,x) && inTree(root,y)){
			return ancestorHelper(root,x,y);
		}
		return null;
	}

	public static TreeNode ancestorHelper(TreeNode root, TreeNode x, TreeNode y){
		if(root == null){
			return null;
		}
		if(root==x && root==y){
			return root;
		}
		TreeNode a = ancestorHelper(root.left,x,y);
		//find ancestor
		if(a!=null && a!=x && a!=y){
			return a;
		}
		TreeNode b = ancestorHelper(root.right,x,y);
		//find ancestor
		if(b!=null && b!=x && b!=y){
			return b;
		}
		//root is ancestor
		if(a!=null && b!=null){
			return root;
		}else if(root==x || root==y){
			return root;
		}else{
			//return non-null element
			if(a!=null){
				return a;
			}else{
				return b;
			}
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



}