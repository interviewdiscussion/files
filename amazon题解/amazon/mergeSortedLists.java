//merge k sorted list

//sol1: use merge sort, time O(nklogk), stack space O(logk)
//sol2: use heap(priority queue), time O(nklogk), space O(k)

import java.util.*;
public class mergeKSortedLists{

	public ListNode mergeKLists(ArrayList<ListNode> lists){
		if(lists==null || lists.size()==0){
			return null;
		}
		//sort
		return sort(lists,0,lists.size()-1);
	}

	public ListNode sort(ArrayList<ListNode> lists, int left, int right){
		if(left < right){
			int mid = (left+right)/2;
			//sort left and right lists and then merge
			return mergeTwoLists(sort(lists,left,mid),sort(lists,mid+1,right));
		}
		return lists.get(left);
	}
    
    //merge two sorted lists
	public ListNode mergeTwoLists(ListNode l1, ListNode l2){
		if(l1==null && l2==null){
			return null;
		}else if(l1==null){
			return l2;
		}else if(l2==null){
			return l1;
		}
		//dummy head
		ListNode res = new ListNode(0);
		ListNode resCurr = res;
		//part with common length
		while(l1!=null && l2!=null){
			if(l1.val < l2.val){
				resCurr.next = l1;
				l1 = l1.next;
			}else{
				resCurr.next = l2;
				l2 = l2.next;
			}
			resCurr = resCurr.next;
		}
        //left-over
		while(l1!=null){
			resCurr.next = l1;
			l1 = l1.next;
		}
		while(l2!=null){
			resCurr.next = l2;
			l2 = l2.next;
		}
		return res.next;
	}



	public ListNode mergeKLists2(ArrayList<ListNode> lists){
		if(lists==null || lists.size()==0){
			return null;
		}
		int k = lists.size();
		//create priority with node comparator
		PriorityQueue<ListNode> q 
		        = new PriorityQueue<ListNode>(k, new Comparator<ListNode>(){
		        	public int compare(ListNode n1, ListNode n2){
		        		return n1.val - n2.val;
		        	}
		        });

		//put initial elements
		//time O(k)
		for(int i=0; i<k; i++){
			if(lists.get(i)!=null){
				q.offer(lists.get(i));
			}
		}


        ListNode res = null;
        ListNode resCurr = null;
        //time O(nklogk)
		while(!q.isEmpty()){
			ListNode n = q.poll();
			if(res == null){
				res = n;
				resCurr = res;
			}else{
				resCurr.next = n;
				resCurr = resCurr.next;
			}
			//put next element in the list if possible
			if(n.next !=null){
				q.offer(n.next);
			}
		}
		return res;
	}
}