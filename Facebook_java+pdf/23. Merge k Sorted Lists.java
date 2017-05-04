/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
// Divide and Conquer O(nlogk) time and O(logk) space:
public class Solution {
    public ListNode mergeKLists(ListNode[] lists) {
        return partion(lists,0,lists.length-1);
    }
    public ListNode partion(ListNode[] lists,int start,int end){
        if(start==end) return lists[start];
        if(start<end){
            int mid=(start+end)/2;
            ListNode l1=partion(lists,start,mid);
            ListNode l2=partion(lists,mid+1,end);
            return merge(l1,l2);
        }else return null;
    }
    public ListNode merge(ListNode l1,ListNode l2){
        ListNode dummy=new ListNode(0);
        ListNode cur=dummy;
        while(l1!=null&&l2!=null){
            if(l1.val<l2.val){
                cur.next=l1;
                l1=l1.next;
            }else{
                cur.next=l2;
                l2=l2.next;
            }
            cur=cur.next;
        }
        if(l1!=null) cur.next=l1;
        else cur.next=l2;
        return dummy.next;
    }
}
// Heap O(nlogk) time and O(k) space:
public class Solution {
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0) {
            return null;
        }
        ListNode dummy = new ListNode(0);
        ListNode tail = dummy;
        Queue<ListNode> heap = new PriorityQueue<>(lists.length, new Comparator<ListNode>(){
            public int compare(ListNode l1, ListNode l2) {
                return l1.val - l2.val;
            }
        });
        for (int i = 0; i < lists.length; i++) {
            if (lists[i] != null) {
                heap.offer(lists[i]);
            }
        }
        while (!heap.isEmpty()) {
            ListNode head = heap.poll();
            tail.next = head;
            tail = head;
            if (head.next != null) {
                heap.offer(head.next);
            }
        }
        return dummy.next;
    }
}

public class Solution {
    public ListNode mergeKLists(ListNode[] lists) {
        return partion(lists,0,lists.length-1);
    }
    public ListNode partion(ListNode[] lists,int start,int end){
        if(start==end) return lists[start];
        if(start<end){
            int mid=(start+end)/2;
            ListNode l1=partion(lists,start,mid);
            ListNode l2=partion(lists,mid+1,end);
            return merge(l1,l2);
        }else return null;
    }
    public ListNode merge(ListNode l1,ListNode l2){
        if(l1==null) return l2;
        if(l2==null) return l1;
        if(l1.val<l2.val){
            l1.next=merge(l1.next,l2);
            return l1;
        }else{
            l2.next=merge(l1,l2.next);
            return l2;
        }
    }
}