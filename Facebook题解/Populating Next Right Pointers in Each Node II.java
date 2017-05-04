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
        TreeLinkNode dummy = new TreeLinkNode(0);//let dummy.next points to leftmost node in the lower level (can be null)
        dummy.next = root;
        while (dummy.next != null) {//dummy will move horizontally (move along the left boundary)
            TreeLinkNode curr = dummy.next, prev = dummy;//curr:curr node in the upper level,prev:prev node in the lower level
            dummy.next = null;//if in upper level all curr's left&right == null, dummy.next will be == null after this loop
            while (curr != null) {
                if (curr.left != null) {
                    prev = prev.next = curr.left;//prev.next = curr.left; prev = prev.next;
                }
                if (curr.right != null) {
                    prev = prev.next = curr.right;//prev.next = curr.right; prev = prev.next;
                }
                curr = curr.next;//move curr horizontally
            }
        }
    }
}