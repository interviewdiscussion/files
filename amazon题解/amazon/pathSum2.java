/**
 * Definition for binary tree
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
public class pathSum2{
    public ArrayList<ArrayList<Integer>> pathSum(TreeNode root, int sum){
		ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();
		ArrayList<Integer> path = new ArrayList<Integer>();
		pathSumHelper(root, sum, result, path);
		return result;
	}

	public void pathSumHelper(TreeNode root, int sum, ArrayList<ArrayList<Integer>> result, ArrayList<Integer> path){
		if(root == null){
			return;
		}
        //reach a leaf
		if(root.left==null && root.right==null){
			//find path sum
			if(root.val == sum){
				path.add(root.val);
				result.add(new ArrayList<Integer>(path));
				path.remove(path.size()-1);
			}
			return;
		}
        //branch on subtrees
		path.add(root.val);
		pathSumHelper(root.left, sum-root.val, result, path);
		pathSumHelper(root.right, sum-root.val, result, path);
		path.remove(path.size()-1);
	}
}