//reverse a linked list
public class reverseLinkedlist{
	public static void main(String[] args){
		ListNode head = new ListNode(1);
		ListNode n1 = new ListNode(2);
		ListNode n2 = new ListNode(8);
		ListNode n3 = new ListNode(4);
		head.next = n1;
		n1.next = n2;
		n2.next = n3;
		ListNode res = recursiveReverse(head);
		while(res != null){
			System.out.print(res.val + " ");
			res = res.next;
		}
	}
//iterative method
	public static ListNode reverse(ListNode head){
		if(head == null || head.next==null){
			return head;
		}
		ListNode prev = null;
		ListNode curr = head;
		ListNode next;
		while(curr != null){
			next = curr.next;
			curr.next = prev;
			prev = curr;
			curr = next;
		}
		return prev;
	}
//recursive method
	public static void recursiveReverse(ListNode head){
		if(head==null || head.next==null){
			return;
		}
		ListNode first = head;
		ListNode rest = head.next;
//reverse rest of the list
		recursiveReverse(rest);
//put first element as last element
		first.next.next = first;
//unlink
		first.next = null;
//reset head
		head = rest;
	}
}