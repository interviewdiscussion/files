// flatten a multi-level linkedlist (down first, next second)
public class Solution {
    private ListNode tail;

    public ListNode flatten(ListNode head) {
        if (head == null) {
            return null;
        }
        tail = head;//keep track of the tail of list
        ListNode next = head.next;//store the next
        if (head.down != null) {
            head.next = flatten(head.down);//connect all nodes in curr head's down part
        }
        if (next != null) {
            tail.next = flatten(next);//connect the tail to the next part
        }
        return head;
    }
}
1 - 2 - 3 - 4
    |
    7 -  8 - 10 - 12
    |    |    |
    9    16   11
    |    |
    14   17 - 18 - 19 - 20
    |                    |
    15 - 23             21
         |
         24
Output:
1->2->7->9->14->15->23->24->8->16->17->18->19->20->21->10->11->12->3->4


// flatten a multi-level linkedlist (level by level)
public class Solution {
    public ListNode flatten(ListNode head) {
        if (head == null) {
            return null;
        }
        ListNode curr = head;
        ListNode tail = head;
        while (tail.next != null) {
            tail = tail.next;//get the tail of first level
        }
        while (curr != tail) {//while curr hasn't reached the tail of all lists
            if (curr.down != null) {//down is not null
                tail.next = curr.down;//append down to the tail
                while (tail.next != null) {
                    tail = tail.next;//update the tail
                }
                curr.down = null;//remember to update the curr.down to null !!!
            }
            curr = curr.next;//update curr to next
        }
    }
}
10 - 5 - 12 - 7 - 11
|             |
4 - 20 - 13 - 17 - 6
    |    |    |
     2   16   9 - 8
         |    |
         3   19 - 15
Output:
10->5->12->7->11->4->20->13->17->6->2->16->9->8->3->19->15