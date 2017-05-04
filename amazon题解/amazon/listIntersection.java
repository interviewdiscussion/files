//find the intersection of two lists
//sol1: compute length difference and move pointers on two lists to the same front
//sol2: make a circle on one list, then reduce the problem to find the loop head
// if intersection exists
public class listIntersection{
	public ListNode getIntersectionNode(ListNode headA, ListNode headB){
		if(headA==null || headB==null){
			return null;
		}
        //compute length of each list
		ListNode iterA = headA;
		ListNode iterB = headB;
		int lenA = 0;
		int lenB = 0;
		while(iterA!=null && iterB!=null){
			iterA = iterA.next;
			lenA++;
			iterB = iterB.next;
			lenB++;
		}
		while(iterA!=null){
			iterA = iterA.next;
			lenA++;
		}
		while(iterB!=null){
			iterB = iterB.next;
			lenB++;
		}
        //compute difference in length
		iterA = headA;
		iterB = headB;
		int diff = Math.abs(lenA-lenB);
        //move pointers so that two lists have same length
		if(lenA > lenB){
			while(diff>0){
				iterA = iterA.next;
				diff--;
			}
		}else{
			while(diff>0){
				iterB = iterB.next;
				diff--;
			}
		}
        //move two pointers at same speed and check if they meet
		while(iterA!=null){
			if(iterA==iterB){
				return iterA;
			}
			iterA = iterA.next;
			iterB = iterB.next;
		}
		return null;
	}
	public ListNode getIntersectionNode(ListNode headA, ListNode headB){
		if(headA==null || headB==null){
			return null;
		}
         //traverse list A to the end and count length
		ListNode iterA = headA;
		ListNode last = null;
		int lenA = 0;
		while(iterA!=null){
			if(iter.next==null){
				last = iter;
			}
			iterA = iterA.next;
			lenA++;
		}
        //make a circle in list A
		last.next = headA;
		ListNode walker1 = headB;
        //travel lenA steps
		while(lenA>0){
			if(walker1==null){
				last.next = null;
				return null;
			}
			walker1 = walker1.next;
			lenA--;
		}
		ListNode walker2 = headB;
		while(walker1!=null && walker2!=null){
			if(walker1==walker2){
				last.next = null;
				return walker1;
			}
			walker1 = walker1.next;
			walker2 = walker2.next;
		}
		last.next = null;
		return null;
	}
}