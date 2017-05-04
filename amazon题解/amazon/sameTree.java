public class sameTree{
	public boolean isSameTree1(TreeNode p, TreeNode q){
		if(p==null && q==null){
			return true;
		}
		if(p==null || q==null){
			return false;
		}else{
			return (p.val==q.val) && isSameTree(p.left,q.left) && isSameTree(p.right,q.right);
		}
	}

	public boolean isSameTree2(TreeNode p, TreeNode q){
		Queue<TreeNode> q1 = new LinkedList<TreeNode>();
		Queue<TreeNode> q2 = new LinkedList<TreeNode>();

		q1.offer(p);
		q2.offer(q);

		while(!q1.isEmpty() && !q2.isEmpty()){
			TreeNode x = q1.poll();
			TreeNode y = q2.poll();

			if(x == null){
				if(y != null){
					return false;
				}else{
					continue;
				}
			}

			if(y == null || x.val!=y.val){
				return false;
			}

			q1.offer(x.left);
			q1.offer(x.right);
			q2.offer(y.left);
			q2.offer(y.right);
		}
		return true;
	}
}