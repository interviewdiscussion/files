/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
public class Solution {
    public boolean isValidBST(TreeNode root) {
        if(root==null) return true;
        return helper(root,null,null);
    }
    public boolean helper(TreeNode root,Integer max,Integer min){
        if(root==null) return true;
        if(max!=null&&root.val>=max) return false;//等号切记
        if(min!=null&&root.val<=min) return false;
        return helper(root.left,root.val,min)&&helper(root.right,max,root.val);
    }
}
//iterative: O(n) time O(n) space
public class Solution {
    public boolean isValidBST(TreeNode root) {
        if (root == null) {
            return true;
        }
        TreeNode prev = null;
        Stack<TreeNode> stack = new Stack<>();
        while (root != null || !stack.empty()) {
            while (root != null) {
                stack.push(root);
                root = root.left;
            }
            root = stack.pop();
            if (prev != null && root.val <= prev.val) {
                return false;
            }
            prev = root;//remember to update the prev !!!
            root = root.right;
        }
        return true;
    }
//Binary Tree Paths, 基本上也是原题，稍微有一点点改动，说是还给了一个 target value，如果遇到 target 就重复打印出到 target 的路径，这个可能不太好理解，我举个例子，比如有一个 root to leaf path 是 1 2 5 2，target 是2，那么这个 path 就应该打印成 1 1 2 5 1 2 5 2。每次遇到 2 就把前面的路径重新 append 一下: 1 (1 2) 5 (1 2 5 2)
http://www.1point3acres.com/bbs/forum.php?mod=viewthread&tid=216917&extra=page%3D1%26filter%3Dsortid%26sortid%3D311%26searchoption%5B3090%5D%5Bvalue%5D%3D2%26searchoption%5B3090%5D%5Btype%5D%3Dradio%26searchoption%5B3046%5D%5Bvalue%5D%3D2%26searchoption%5B3046%5D%5Btype%5D%3Dradio%26sortid%3D311
public class Solution {
    public List<String> binaryTreePaths(TreeNode root) {
        List<String> res=new ArrayList<>();
        if(root==null) return res;
        helper(res,root,"",target);
        return res;
    }
    public void helper(List<String> res,TreeNode root,String s,int target,String temp){
        if(root.left==null&&root.right==null){
            if(target==root.val){
                res.add(s+temp+root.val);
            }else{
                res.add(s+root.val);
            }
        }
        if(root.left!=null){
            if(target==root.val){
                helper(res,root.left,s+temp+root.val+"->",temp+root.val+"->");
            }else{
                helper(res,root.left,s+root.val+"->",temp+root.val+"->");
            }
        }
        if(root.right!=null) helper(res,root.right,s+root.val+"->"){
            if(target==root.val){
                helper(res,root.right,s+temp+root.val+"->",temp+root.val+"->");
            }else{
                helper(res,root.right,s+root.val+"->",temp+root.val+"->");
            }
        }
    }
}