
import java.util.Scanner;

/**
 * A basic implementation of a doubly linked list
 * Jing Lu
 * jinglu@brandeis.edu
 */
public class DoublyLinkedList {
	//head refers to the first element in the DoublyLinkedList
	private Node head;
	private int size;
	
	/**
	 * The node class used to store the nodes of the 
	 * doubly linked list.
	 */
	public class Node{
		//item stores an object
		//prev refers to the previous node of current node
		//next refers to the next node of current node
		public Object item;
		public Node prev = null;
		public Node next = null;
		
		/**
		 * default constructor
		 */
		public Node(){
			this.item = null;
		}
		
		/**
		 * construct a node with item
		 * @param item the item to store in the node
		 */
		public Node(Object item){
			this.item = item;
		}
		
		/**
		 * @return information of node data as string
		 */
		public String toString(){
			return this.item.toString();
		}
	}
		
	/**
	 * default constructor of DoublyLinkedList
	 * set head to null and size to zero
	 */
	public DoublyLinkedList(){
		this.head = null;
		this.size = 0;
	}
	
	
	/**
	 * Adds an item to the front of the doubly linked list
	 * @param item - the item to add
	 */
	public void addFront(Object item){
		//create a new node storing the item
		Node addNode = new Node(item);
		addNode.next = this.head;
		//when head is null, skip this step
		if(this.head != null){
			this.head.prev = addNode;
		}
		this.head = addNode;
		addNode.prev = null;
		this.size += 1;
	}

	
	
	
	/**
	 * Inserts an item into a specified position in the doubly linked list
	 * To clarify the meaning of adding item into a position, for example:
	 * if the list is [a,b,c], addMid(d,2) will give [a,d,b,c]
	 * @param item - the item to add
	 * @param position - the position in the list where the item should be inserted
	 */
	public void addMid(Object item, int position){
		//create a new node storing the item to add
		Node addNode = new Node(item);
		//find the node after which the new node will be added
		Node y = this.head;
		for(int i=1; i<position-1; i++){
			y = y.next;
		}
		//insert the new node after node y
		addNode.next = y.next;
		if(addNode.next != null){
			(addNode.next).prev = addNode;
		}
		y.next = addNode;
		addNode.prev = y;	
		this.size += 1;
	}

	
	
	/**
	 * Adds an item to the end of the doubly linked list.
	 * @param item - the item to add
	 */
	public void addEnd(Object item){
		//if list is empty, addEnd is equal to addFront
		if(this.size==0){
			addFront(item);
		}else {
			//find the last node of the list
			Node lastNode = this.head;
			for(int i=1; i<this.size; i++){
				lastNode = lastNode.next;
			}
			//create the new node which will be added at the end
			Node newLastNode = new Node(item);
			lastNode.next = newLastNode;
			newLastNode.prev = lastNode;
			newLastNode.next = null;
			this.size += 1;
		}
	}
	
	
	
	/**
	 * Deletes the first item of the list. Writes an error message if the list is empty.
	 * @return a reference to the new first node of the list.
	 */
	public Node deleteFront(){
		try{
            this.head = this.head.next;
            this.size -= 1;
            //return the new first node
            return this.head;
		}catch(NullPointerException e){
			System.out.println("The list is empty...");
			return null;
		}
	}

	
	
	/**
	 * Deletes the last item of the list. Writes an error message if the list is empty.
	 */
	public void deleteEnd(){
		try{
		    //find the second last node of the list
		    Node secondLastNode = this.head;
		    for(int i=1; i<this.size; i++){
			    secondLastNode = secondLastNode.next;
		    }
		    //delete the last node
		    secondLastNode.next = null;
		    this.size -= 1;
		}catch(NullPointerException e){
			System.out.println("The list is empty...");
		}
	}

	
	
	
	/**
	 * Deletes the first occurrence of the specified item from the list. Writes an error
	 * message if the list doesn't contain the item.
	 * @param item - the item to delete
	 */
	public void delete(Object item){
		if(contains(item) == false){
			System.out.println("The list doesn't contain the item");
		}else {
			//foundOne ensures that we only delete the first occurrence
			boolean foundOne = false;
            //the node we use to test if it contains the item
			Node deleteNode = this.head;
			while(foundOne == false){
				//if a node contains the item we want, delete the node
				if(deleteNode.item.equals(item)){		
				    if(deleteNode.prev != null){
					    (deleteNode.prev).next = deleteNode.next;
				    }else{
					    this.head = deleteNode.next;
				    }	    
				    if(deleteNode.next != null){
					    (deleteNode.next).prev = deleteNode.prev;
				    }
				    //set foundOne to true so that we don't need to continue
				    foundOne = true;
				    this.size -= 1;
				}
				//continue to go through the list if item is not found yet
				deleteNode = deleteNode.next;
			}
			
		}
	}

				
			
	
	/**
	 * Deletes all occurrences of the specified item from the list. Writes an error
	 * message if the list doesn't contain the item.
	 * @param item - the item to delete
	 */
	public void deleteAll(Object item){
		if(contains(item) == false){
			System.out.println("The list doesn't contain the item");
		}else{
			//delete specified item using delete(item) method until item doesn't exist any more
			while(contains(item) == true){
				delete(item);
			}
		}
	}
	
	
	
	/**
	 * Searches for the specified item in the list.
	 * @param item - the item to search for.
	 * @return true if the item is in the list, false otherwise.
	 */
	public boolean contains(Object item){
		//the node to check if it contains the item
		Node checkNode = this.head;
		for(int i=0; i<this.size; i++){
			if(checkNode.item.equals(item)){
				return true;
			}
			//if item not found, continue to go through the list
			checkNode = checkNode.next;
		}
		return false;
	}
	
	
	
	/**
	 * Makes this list a circular one by having the last node refer
	 * back to the first.
	 */
	public void makeCircular(){
		//print error message if list is empty
		if(this.size == 0){
			System.out.println("The list is empty.");
		}else if(this.size == 1){
			this.head.next = this.head;
		}else{
			//find the last node
			Node lastNode = this.head;
			for(int i=1; i<this.size; i++){
				lastNode = lastNode.next;
			}
			lastNode.next = this.head;
		}
	}
	
	
	
	/**
	 * Returns the first node of the list.
	 * @return the first node.
	 */
	public Node getFront(){
		return this.head;
	}
	
	/**
	 * return the size of doublylinkedlist
	 * @return size of the list
	 */
	public int getSize(){
		return this.size;
	}
	
	/**
	 * Joins two circular lists by making the elements alternate.
	 * The first list will be in the odd positions and the second will
	 * be in the even positions. Do this without creating a new list or
	 * making new nodes.
	 * 
	 * For example:
	 *     listOdds = 1 2 3 4 (circular)
	 *     listEvens = a b c d (circular)
	 *     
	 *     returns 1 a 2 b 3 c 4 d (circular)
	 * @param listOdds - the list to merge into the odd positions.
	 * @param listEvens - the list to merge into the even positions. 
	 * @return the the merged list.
	 * 
	 * NOTE:this method also handles situation when the lengths of listOdds and listEvens are different
	 * we always move elements from list with shorter length to list with longer length
	 * For example:
	 *    listOdds = 1 2 (circular)
	 *    listEvens = a b c d (circular)
	 *    return the merged list as 1 a 2 b c d (circular)
	 *    
	 *    listOdds = a b c d (circular)
	 *    listEvens = 1 2 (circular)
	 *    return the merged list as a 1 b 2 c d (circular)
	 */
	public static DoublyLinkedList joinLists(DoublyLinkedList listOdds, DoublyLinkedList listEvens){
		if(listEvens.size <= listOdds.size){
			//in this case, we move elements from listEvens to listOdds
			//and put them in even positions
			//finally, return listOdds as merged list
		    for(int i=1; i<=listEvens.size; i++){
		    	//get each element from listEvens, put them in correct position
		    	//then delete the element
			    listOdds.addMid(listEvens.getFront().item, 2*i);
			    listEvens.deleteFront();
			    //keep list size to its original value
			    //because a constant value is need in the loop condition to go through every element
			    listEvens.size += 1;
		    }
		    //make merged list circular and return the list
		    listOdds.makeCircular();
		    return listOdds;
		}else {
			//in this case, we move elements from listOdds to listEvens
			//and put them in odd positions
			//finally return listEvens as merged list
			
			//handle the first element
			listEvens.addFront(listOdds.getFront().item);
			listOdds.deleteFront();
			listOdds.size += 1;
			for(int i=2; i<=listOdds.size; i++){
				//get each element of the rest from listOdds, and put them in correct position
				//then delete the element
				listEvens.addMid(listOdds.getFront().item, 2*i-1);
				listOdds.deleteFront();
				//keep list size to its original value
			    //because a constant value is need in the loop condition to go through every element
				listOdds.size += 1;
			}
			listEvens.makeCircular();
			return listEvens;
		}
	}
	
	
	
	public String toString() {
		//This procedure should visualize the contents of the list 
		//as a string. Refer to a similar procedure in ArrayQueue.
		//replace the following line with desired output
		//Account for the fact that the list may be circular
		String s = "";
		if(this.size == 0){
			System.out.println("The list is empty.");
		}else {
			Node currentNode = this.head;
			for(int i=0; i<this.size; i++){
		        s += "[" + currentNode.item.toString() + "]";
				currentNode = currentNode.next;
			}
		}
		//new line and indent
		s += "\n\t"; 
		//DO: Add the values of the other fields to the string
		s += "The current size of is " + this.size + ".";
		return s;
	}
	
	
	
	/**
	 * A main with which to test the implementation of the queue.
	 * @param args
	 */
	public static void main(String[] args)
	{
		//Initialize the list
		DoublyLinkedList dll = new DoublyLinkedList();
	    
	    //Create a scanner object and a string for storing the input
	    Scanner scan = new Scanner(System.in);
	    String cmd;
	    
	    //Print the program's commands to the user.
	    System.out.println("Commands:\n\taddFront OBJ\n\taddEnd OBJ" +
          "\n\taddMid OBJ POSITION\n\tdeleteFront\n\tdeleteEnd"+
          "\n\tdelete OBJ\n\tdeleteAll OBJ\n\tcontains OBJ\n\tquit");
	    
	    //Loop through accepting input and printing returned information until quit
	    while (!(cmd=scan.next()).equalsIgnoreCase("quit"))
	    {
	    	if (cmd.equalsIgnoreCase("addFront"))
	    	{
	    		dll.addFront(scan.next());
	    	}
	    	else if (cmd.equalsIgnoreCase("addEnd"))
	    	{
	    		dll.addEnd(scan.next());
	    	}
	    	else if (cmd.equalsIgnoreCase("addMid"))
	    	{
	    		dll.addMid(scan.next(), scan.nextInt());
	    	}
	    	else if (cmd.equalsIgnoreCase("deleteFront"))
	    	{
	    		dll.deleteFront();
	    	}
	    	else if (cmd.equalsIgnoreCase("deleteEnd"))
	    	{
	    		dll.deleteEnd();
	    	}
	    	else if (cmd.equalsIgnoreCase("delete"))
	    	{
	    		dll.delete(scan.next());
	    	}
	    	else if (cmd.equalsIgnoreCase("deleteAll"))
	    	{
	    		dll.deleteAll(scan.next());
	    	}
	    	else if (cmd.equalsIgnoreCase("contains"))
	    	{
	    		System.out.println("Contains: "+dll.contains(scan.next()));
	    	}else {
	    		System.out.println("Wrong Command.");
	    		System.exit(0);
	    	}
	    	
	    	//Show what the list looks like
	    	System.out.println("Contents:\n\t"+dll.toString());
	    }
	}
}
