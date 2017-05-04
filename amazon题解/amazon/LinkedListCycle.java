public class LinkedListCycle{
	public boolean hasCycle(ListNode head){
		//no node or one node
		if(head==null || head.next==null){
			return false;
		}
		//fast runner is twice faster
		ListNode fast = head;
		ListNode slow = head;

		while(fast!=null && fast.next!=null){
			fast = fast.next.next;
			slow = slow.next;
			//cycle detected
			if(fast==slow){
				return true;
			}
		}
		return false;
	}

	public ListNode detectCycle(ListNode head){
		if(head==null || head.next==null){
			return true;
		}
		ListNode fast = head;
		ListNode slow = head;
        //moving runners
		while(fast!=null && fast.next!=null){
			fast = fast.next.next;
			slow = slow.next;
			if(fast == slow){
				break;
			}
		}
		if(fast==null || fast.next==null){
			return null;
		}else{
			//move one runner to head, after same steps, we will reach the loop head
			fast = head;
			while(fast != slow){
				fast = fast.next;
				slow = slow.next;
			}
			return fast;
		}
	}
}