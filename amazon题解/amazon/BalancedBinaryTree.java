
public class BalancedBinaryTree{
	public boolean isBalanced1(TreeNode root){
		if(root == null){
			return true;
		}
		if(Math.abs(height(root.left)-height(root.right))>1){
			return false;
		}else{
			return isBalanced(root.left) && isBalanced(root.right);
		}
	}
	public int height(TreeNode node){
		if(node == null){
			return -1;
		}
		return 1 + Math.max(height(node.left),height(node.right));
	}
	public boolean isBalanced2(TreeNode root){
		return isBalanceHelper(root)!=1;
	}
	public int isBalanceHelper(TreeNode root){
		if(root == null){
			return 0;
		}
//compute depth of subtrees and check if -1 is returned
		int leftDepth = isBalanceHelper(root.left);
		if(leftDepth == -1){
			return -1;
		}
		int rightDepth = isBalanceHelper(root.right);
		if(rightDepth == -1){
			return -1;
		}
		if(Math.abs(leftDepth-rightDepth)>1){
			return -1;
		}else{
			return 1+Math.max(leftDepth,rightDepth);
		}
	}
}