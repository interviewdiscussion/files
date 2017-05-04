/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
public class Codec {

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        if(root==null) return "";
        StringBuilder res=new StringBuilder();
        Queue<TreeNode> queue=new LinkedList<>();
        queue.offer(root);
        while(!queue.isEmpty()){
            TreeNode cur=queue.poll();
            if(cur==null){
                res.append("null ");
                continue;
            }
            res.append(cur.val+" ");
            queue.offer(cur.left);
            queue.offer(cur.right);
        }
        return res.toString();
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if(data=="") return null;
        String[] str=data.split(" ");
        TreeNode root=new TreeNode(Integer.parseInt(str[0]));
        Queue<TreeNode> queue=new LinkedList<>();
        queue.offer(root);
        for(int i=1;i<str.length;i++){
            TreeNode cur=queue.poll();
            if(!str[i].equals("null")){
                cur.left=new TreeNode(Integer.parseInt(str[i]));
                queue.offer(cur.left);
            }
            if(!str[++i].equals("null")){
                cur.right=new TreeNode(Integer.parseInt(str[i]));
                queue.offer(cur.right);
            }
        }
        return root;
    }
}
與原題不同的地方在於：規定要把 tree 轉成 "linked list" ，再把 "linked list" 轉成 tree
    inorder转化linked list，但是保留空结点，然后109convert
// Your Codec object will be instantiated and called as such:
// Codec codec = new Codec();
// codec.deserialize(codec.serialize(root));

// use dfs without any data structure to serialize and deserialize tree
public class Codec {
    private int index;
    
    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        if (root == null) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        buildString(root, sb);
        return sb.toString();//return sb.toString(), not return buildString(root, sb) !!!
    }

    private void buildString(TreeNode root, StringBuilder sb) {
        if (root == null) {
            sb.append("n ");
        } else {
            sb.append(root.val + " ");
            buildString(root.left, sb);
            buildString(root.right, sb);
        }
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {//if using data structure for dfs is allowed,use a queue here and add all split
        if (data.equals("")) {//then pass the queue in buildtree method
            return null;
        }
        index = 0;
        return buildTree(data);
    }
    
    private TreeNode buildTree(String data) {//if don't want a global variable,just cut the string after building a node
        int temp = index;
        while (data.charAt(index) != ' ') {//move index to index of next whitespace
            index++;
        }
        String val = data.substring(temp, index++);//remember to move index to next starting index of val
        if (val.equals("n")) {
            return null;
        } else {
            TreeNode node = new TreeNode(Integer.valueOf(val));
            node.left = buildTree(data);
            node.right = buildTree(data);
            return node;
        }
    }
}