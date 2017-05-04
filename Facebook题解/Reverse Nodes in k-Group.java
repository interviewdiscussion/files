/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
// iterative: O(n) time O(1) space
public class Solution {
    public ListNode reverseKGroup(ListNode head, int k) {
        if (head == null || k < 0) {
            return head;
        }
        ListNode dummy = new ListNode(0);
        ListNode prev = dummy;//prev is the node before the old head(new tail)
        ListNode tail = head;//tail is the old head(new tail)
        dummy.next = head;
        while (true) {
            ListNode temp = head;//save the head of the list that we are going to reverse
            int i = 0;
            while (i < k && head != null) {
                head = head.next;//move head until it become the node after the old tail(new head)
                i++;
            }
            if (i != k && head == null) {//this means we reach the end, which means the length of the rest of list < k
                break;
            }
            prev.next = reverse(temp, k);//prev.next points to new head
            tail.next = head;//new tail points to the node after the old tail
            prev = tail;//update prev to new tail
            tail = head;//update tail to the node after the new tail
        }
        return dummy.next;
    }
    
    private ListNode reverse(ListNode head, int k) {
        ListNode prev = null;
        for (int i = 0; i < k; i++) {//cuz we already know how many nodes we are going to reverse, we don't need to let the
            ListNode temp = head.next;//old tail.next points to null and use curr!=null to determine whether reverse finished
            head.next = prev;
            prev = head;
            head = temp;
        }
        return prev;
    }
}

// recursive solution: O(n) time O(n/k) space
public class Solution {
    public ListNode reverseKGroup(ListNode head, int k) {
        ListNode curr = head;
        int count = 0;
        while (curr != null && count < k) {
            curr = curr.next;
            count++;
        }
        if (count == k) {//if we have k nodes to reverse
            curr = reverseKGroup(curr, k);//curr becomes next new head after current tail,if curr == null,it will return null
            while (count > 0) {//for this part, curr works like "prev"
                ListNode temp = head.next;
                head.next = curr;//old head(new tail will directly linked to next list's new head)
                curr = head;
                head = temp;
                count--;//remember to update count !!!!!!
            }
            head = curr;//remember to update head to the current new head cuz we finally return head !! works like "prev"
        }
        return head;
    }
}