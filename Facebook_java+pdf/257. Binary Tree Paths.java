/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
1，最简单的解法 O(n)
    如果是print full tree O(nlogn)一条线：O(n^2) 优化：StringBuilder
public class Solution {
    public List<String> binaryTreePaths(TreeNode root) {
        List<String> res=new ArrayList<>();
        if(root==null) return res;
        helper(res,root,"");
        return res;
    }
    public void helper(List<String> res,TreeNode root,String s){
        if(root.left==null&&root.right==null) res.add(s+root.val);
        if(root.left!=null) helper(res,root.left,s+root.val+"->");
        if(root.right!=null) helper(res,root.right,s+root.val+"->");
    }
}
2,比如有一个 root to leaf path 是 1 2 5 2，target 是2，那么这个 path 就应该打印成 1 1 2 5 1 2 5 2。每次遇到 2 就把前面的路径重新 append 一下: 1 (1 2) 5 (1 2 5 2).
public class Solution {
    public List<String> binaryTreePaths(TreeNode root,int target) {
        List<String> res=new ArrayList<>();
        if(root==null) return res;
        helper(res,root,"","",target);
        return res;
    }
    public void helper(List<String> res,TreeNode root,String s,String target_s,int target){
        if(root.left==null&&root.right==null){
            if(root.val==target){
                res.add(target_s+s+root.val);
            }else res.add(target_s+root.val);
        }
        if(root.left!=null){
            if(root.val==target){
                helper(res,root.left,s+root.val+"->",target_s+s+root.val+"->",target);
            }else helper(res,root.left,s+root.val+"->",target_s+root.val+"->",target);
        }
        if(root.right!=null){
            if(root.val==target){
                helper(res,root.right,s+root.val+"->",target_s+s+root.val+"->",target);
            }else helper(res,root.right,s+root.val+"->",target_s+root.val+"->",target);
        }
    }
}
// recursive (if we only need to print, we don't need res, but we still need path)
public class Solution {
    public List<String> binaryTreePaths(TreeNode root) {
        List<String> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        helper(res, new StringBuilder(), root);
        return res;
    }
    
    private void helper(List<String> res, StringBuilder path, TreeNode root) {
        path.append(root.val + "->");
        if (root.left == null && root.right == null) {
            path.setLength(path.length() - 2);//setLength(path.length() - 2), not setLength(0, path.length() - 2) !!!
            res.add(path.toString());
            return;
        }
        int length = path.length();
        if (root.left != null) {
            helper(res, path, root.left);
            path.setLength(length);
        }
        if (root.right != null) {
            helper(res, path, root.right);
            path.setLength(length);
        }
    }
}
// iterative (dfs pre-order)
public class Solution {
    public List<String> binaryTreePaths(TreeNode root) {
        List<String> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        Stack<TreeNode> stack = new Stack<>();
        Stack<StringBuilder> paths = new Stack<>();
        stack.push(root);
        paths.push(new StringBuilder(root.val + ""));//remember to add + "" !!!
        while (!stack.empty()) {
            TreeNode node = stack.pop();
            StringBuilder path = paths.pop();
            if (node.left == null && node.right == null) {
                res.add(path.toString());
            }
            if (node.right != null) {
                stack.push(node.right);
                StringBuilder newPath = new StringBuilder(path);
                paths.push(newPath.append("->" + node.right.val));
            }
            if (node.left != null) {
                stack.push(node.left);
                StringBuilder newPath = new StringBuilder(path);
                paths.push(newPath.append("->" + node.left.val));
            }
        }
        return res;
    }
}
// iterative (bfs), use queue to store ArrayList<TreeNode>, which is the path
public class Solution {
    public List<String> binaryTreePaths(TreeNode root) {
        List<String> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        Queue<ArrayList<TreeNode>> queue = new LinkedList<>();
        queue.offer(new ArrayList<>(Arrays.asList(root)));
        while (!queue.isEmpty()) {
            ArrayList<TreeNode> path = queue.poll();
            TreeNode curr = path.get(path.size() - 1);
            if (curr.left == null && curr.right == null) {
                StringBuilder sb = new StringBuilder();
                for (TreeNode node : path) {
                    sb.append(node.val + "->");
                }
                sb.setLength(sb.length() - 2);//it's void type !!!
                res.add(sb.toString());
                continue;
            }
            if (curr.left != null) {
                ArrayList<TreeNode> newPath = new ArrayList<>(path);
                newPath.add(curr.left);
                queue.offer(newPath);
            }
            if (curr.right != null) {
                ArrayList<TreeNode> newPath = new ArrayList<>(path);
                newPath.add(curr.right);
                queue.offer(newPath);
            }
        }
        return res;
    }
}

// if we cannot use dfs(which is easy for printing paths), then use bfs, use hashmap to store parent-to-children paths
public class Solution {
    public void binaryTreePaths(TreeNode root) {
        List<String> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        HashMap<TreeNode, TreeNode> map = new HashMap<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        map.put(root, null);
        while (!queue.isEmpty()) {
            TreeNode curr = queue.poll();
            if (curr.left == null && curr.right == null) {
                String path = getPath(map, curr);//if you only want to print paths, we can use recursion here
                res.add(path);
            }
            if (curr.left != null) {
                map.put(curr.left, curr);
                queue.offer(curr.left);
            }
            if (curr.right != null) {
                map.put(curr.right, curr);
                queue.offer(curr.right);
            }
        }
        return res;
    }
    
    private String getPath(HashMap<TreeNode, TreeNode> map, TreeNode node) {
        StringBuilder sb = new StringBuilder();
        while (node != null) {//from leaf to root
            sb.append(node.val + ">-");
            node = map.get(node);
        }
        return sb.reverse().toString().substring(2);
    }
}
