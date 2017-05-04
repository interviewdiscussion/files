/**
 * Definition for binary tree with next pointer.
 * public class TreeLinkNode {
 *     int val;
 *     TreeLinkNode left, right, next;
 *     TreeLinkNode(int x) { val = x; }
 * }
 */
public class Solution {
    public void connect(TreeLinkNode root) {
        while (root != null && root.left != null) {//make sure both two level are not null
            TreeLinkNode curr = root;//root indeed is used to record the leftmost node, curr traverses all nodes in this level
            while (curr != null) {//curr traverse horizontally
                curr.left.next = curr.right;//connect left child
                curr.right.next = curr.next == null ? null : curr.next.left;//connect right child
                curr = curr.next;//curr traverse horizontally
            }
            root = root.left;//root traverse vertically
        }
    }
}
//this is kind of like level order traversal, it traverse curr level to connect left&right and right&root.next.right(or null)
//you can use recursion as well, but that takes logn space