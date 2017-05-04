
/**
 * A class which joins two circular doubly linked lists.
 * Jing Lu
 * jinglu@brandeis.edu
 */
public class JoinLists {
	
	/**
	 * The main used for testing the implementation of JoinLists in DoublyLinkedList
	 * @param args
	 */
	public static void main(String[] args){
		System.out.println("Start test for lists with same length...");
	    testSameLength();
	    System.out.println();
	    
	    System.out.println("Starting the first test for lists with different length...");
	    testDifferentLength1();
	    System.out.println();
	    
	    System.out.println("Starting the second test for lists with different length...");
	    testDifferentLength2();
	}
	
	/**
	 * test for listOdds and listEvens with same length
	 */
	public static void testSameLength(){
		//initialize listOdds and listEvens
		DoublyLinkedList list1 = new DoublyLinkedList();
		DoublyLinkedList list2 = new DoublyLinkedList();
		//Add values to the first list
		list1.addEnd("a");
		list1.addEnd("b");
		list1.addEnd("c");
		list1.addEnd("d");
				
		//Add values to the second list
		list2.addEnd(1);
		list2.addEnd(2);
		list2.addEnd(3);
		list2.addEnd(4);
		
		//make two lists circular
		list1.makeCircular();
		list2.makeCircular();
		
		System.out.println("listOdds is: " + list1.toString());
		System.out.println("listEvens is: " + list2.toString());
		
		//join lists and print result
		DoublyLinkedList list3 = DoublyLinkedList.joinLists(list1, list2);
		System.out.println("Merged list is: " + list3.toString());	
		
	}
	
	/**
	 * first test for listOdds and listEvens with different length
	 */
	public static void testDifferentLength1(){
		//initialize listOdds and listEvens
		DoublyLinkedList list1 = new DoublyLinkedList();
		DoublyLinkedList list2 = new DoublyLinkedList();
		//Add values to the first list
		list1.addEnd("a");
		list1.addEnd("b");
		list1.addEnd("c");
		list1.addEnd("d");
				
		//Add values to the second list
		list2.addEnd(1);
		list2.addEnd(2);
		
		//make lists circular
		list1.makeCircular();
		list2.makeCircular();
		
		System.out.println("listOdds is: " + list1.toString());
		System.out.println("listEvens is: " + list2.toString());
		
		//join list and print result
		DoublyLinkedList list3 = DoublyLinkedList.joinLists(list1, list2);
		System.out.println("Merged list is: " + list3.toString());	
	}
	
	/**
	 * second test for listOdds and listEvens with different length
	 */
	public static void testDifferentLength2(){
		//initialize listOdds and listEvens
		DoublyLinkedList list1 = new DoublyLinkedList();
		DoublyLinkedList list2 = new DoublyLinkedList();
		//Add values to the first list
		list1.addEnd("a");
		list1.addEnd("b");
		list1.addEnd("c");
		list1.addEnd("d");
				
		//Add values to the second list
		list2.addEnd(1);
		list2.addEnd(2);
		
		//make lists circular
		list1.makeCircular();
		list2.makeCircular();
		
		System.out.println("listOdds is: " + list2.toString());
		System.out.println("listEvens is: " + list1.toString());
		
		//join list and print result
		DoublyLinkedList list3 = DoublyLinkedList.joinLists(list2, list1);
		System.out.println("Merged list is: " + list3.toString());		
	}
	
}
