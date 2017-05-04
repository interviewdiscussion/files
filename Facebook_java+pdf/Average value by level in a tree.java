102相似题 bfs O(n) time and space
public class Solution {
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<Integer> res=new ArrayList<>();
        if(root==null) return res;
        Queue<TreeNode> queue=new LinkedList<>();
        queue.offer(root);
        while(!queue.isEmpty()){
            int size=queue.size();
            int average=0;
            List<Integer> list=new LinkedList<>();
            for(int i=0;i<size;i++){
                TreeNode cur=queue.poll();
                average+=cur.val;
                if(cur.left!=null) queue.offer(cur.left);
                if(cur.right!=null) queue.offer(cur.right);
            }
            res.add(average/size);
        }
        return res;
    }
}