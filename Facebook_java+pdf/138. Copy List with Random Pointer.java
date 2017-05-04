/**
 * Definition for singly-linked list with a random pointer.
 * class RandomListNode {
 *     int label;
 *     RandomListNode next, random;
 *     RandomListNode(int x) { this.label = x; }
 * };
 */
## Idea 
* Itertaion 
* Associate the orgianl node with its copy node in a single linked list
* First, make copy of each node 1 - 2 - 3 -> 1 - 1 - 2 - 2 - 3 -3 
* Link them together in a single list
* Second, assign random pointers for the copied nodes

## Time 
* O(n)
 //follow up问有没有其他bad solutions或者其他好的solutions，想了半天就说了用额外空间无脑存储指针标记的办法，然后问了一下这个的复杂度。
public class Solution {
    public RandomListNode copyRandomList(RandomListNode head) {
        RandomListNode iter = head, next;
    
    	// First round: make copy of each node,
    	// and link them together side-by-side in a single list.
    	while (iter != null) {
    		next = iter.next;
    
    		RandomListNode copy = new RandomListNode(iter.label);
    		iter.next = copy;
    		copy.next = next;
    
    		iter = next;
    	}
    	// Second round: assign random pointers for the copy nodes.
    	iter = head;
    	while (iter != null) {
    		if (iter.random != null) {
    			iter.next.random = iter.random.next;
    		}
    		iter = iter.next.next;
    	}
    	// Third round: restore the original list, and extract the copy list.
    	iter = head;
    	RandomListNode pseudoHead = new RandomListNode(0);
    	RandomListNode copy, copyIter = pseudoHead;
    	while (iter != null) {
    		next = iter.next.next;
    
    		// extract the copy
    		copy = iter.next;
    		copyIter.next = copy;
    		copyIter = copy;
    
    		// restore the original list
    		iter.next = next;
    
    		iter = next;
    	}
    
    	return pseudoHead.next;
    }
}
## Idea ( hashmap )

## Code 

```
public class Solution {
    public RandomListNode copyRandomList(RandomListNode head) {
        if( head == null ) return null;
        Map<RandomListNode,RandomListNode> map = new HashMap<RandomListNode,RandomListNode>();
        // loop 1 : copy all node
        RandomListNode cur = head;
        while( cur != null ){
            map.put(cur, new RandomListNode(cur.label));
            cur = cur.next;
        }
        // loop 2 : assign next and random pointers
        cur = head;
        while( cur != null ){
            map.get(cur).next = map.get(cur.next);
            map.get(cur).random = map.get(cur.random);
            cur = cur.next;
        }
        return map.get(head);
    }
}
```
