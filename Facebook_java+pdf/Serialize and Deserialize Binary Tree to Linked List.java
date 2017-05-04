class TreeNode {
      char val;
      TreeNode left;
      TreeNode right;
      TreeNode(char x) { val = x; }
}
// Encodes a tree to a single string.
    public TreeNode serialize(TreeNode root) {
        TreeNode dummy = new TreeNode('0');
        TreeNode head = dummy;

        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while ( !stack.isEmpty() ) {
            TreeNode cur = stack.pop();
            head.right = cur;
            head = head.right;

            if ( cur.right != null ) stack.push(cur.right);
            else {
                head.right = new TreeNode('N');
                head = head.right;
            }
            if ( cur.left != null ) stack.push(cur.left);
            else {
                head.right = new TreeNode('N');
                head = head.right; 
            }

        }

        return dummy.right;
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(TreeNode head) {
        Deque<Character> deque  = new LinkedList<>();
        while( head != null){
            deque.offerLast(head.val);
            head = head.right;
        }        
        return buildTree(deque);
    }
    private TreeNode buildTree(Deque<Character> deque){
        Character root = deque.remove();
        if(root.equals(nn)) return null;
        else{ 
            TreeNode node = new TreeNode(root);
            node.left = buildTree(deque);
            node.right = buildTree(deque);
            return node;
        }
    }
}



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