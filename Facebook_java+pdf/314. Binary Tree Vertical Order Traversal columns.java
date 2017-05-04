/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
//bfs solution with O(n) time and space
public class Solution {
    private int min = 0, max = 0;
    public List<List<Integer>> verticalOrder(TreeNode root) {
        List<List<Integer>> list = new ArrayList<>();
        if(root == null)    return list;
        computeRange(root, 0);
        for(int i = min; i <= max; i++) list.add(new ArrayList<>());
        Queue<TreeNode> q = new LinkedList<>();
        Queue<Integer> idx = new LinkedList<>();
        idx.add(-min);
        q.add(root);
        while(!q.isEmpty()){
            TreeNode node = q.poll();
            int i = idx.poll();
            list.get(i).add(node.val);
            if(node.left != null){
                q.add(node.left);
                idx.add(i - 1);
            }
            if(node.right != null){
                q.add(node.right);
                idx.add(i + 1);
            }
        }
        return list;
    }
    private void computeRange(TreeNode root, int idx){
        if(root == null)    return;
        min = Math.min(min, idx);
        max = Math.max(max, idx);
        computeRange(root.left, idx - 1);
        computeRange(root.right, idx + 1);
    }
}
test case: 
min=-2 max=2
    0     4
    1   9
    2 3   01
    3   8
    4     7
    
    3
    /\
   /  \
   9   8
  /\  /\
 /  \/  \
 4  01   7
[
  [4],
  [9],
  [3,0,1],
  [8],
  [7]
]
## Idea 
* Use a HashMap to store the TreeNode and the according cloumn value
* Map's key is column, we assume the root column is zero, the left node will minus 1 ,and the right node will plus 1
//HashMap + bfs solution with O(n) time and space
public class Solution {
    public List<List<Integer>> verticalOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        HashMap<Integer, ArrayList<Integer>> map = new HashMap<>();//if use TreeMap to keep data sorted,we won't need min&max
        Queue<TreeNode> queue = new LinkedList<>();
        Queue<Integer> cols = new LinkedList<>();//if we use Pair class to store treenode and its col, we only need 1 queue
        int min = 0;//use min&max to store the range of col, so that we can iterate cols from left to right
        int max = 0;
        queue.offer(root);
        cols.offer(0);
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            int col = cols.poll();
            if (!map.containsKey(col)) {
                map.put(col, new ArrayList<>());
            }
            map.get(col).add(node.val);//let col be key,node.val be value,instead of treenode,to make res.add more convinient
            if (node.left != null) {
                queue.offer(node.left);
                cols.offer(col - 1);
                min = Math.min(min, col - 1);
            }
            if (node.right != null) {
                queue.offer(node.right);
                cols.offer(col + 1);
                max = Math.max(max, col + 1);
            }
        }
        for (int i = min; i <= max; i++) {//note it should be i <= max
            res.add(map.get(i));
        }
        return res;
    }
}

//If you wanna avoid using hashmap cuz of key conflicts,you can use doubly-linked list,each node stores a Arraylist of vals,
//then replace Queue<Integer> cols with Queue<LinkedList> cols,each time we poll,we first add it to curr node's arraylist,
//put non-null left node to a new left list(if curr.prev == head),
//put non-null right node to a new right list(if curr.next == tail),
//finally iterate all lists from head to tail