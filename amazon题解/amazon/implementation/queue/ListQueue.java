
import java.util.Scanner;

/**
 * A basic implementation of a queue using an doubly linked list.
 * Jing Lu
 * jinglu@brandeis.edu
 */
public class ListQueue {
	DoublyLinkedList list;
	
	/**
	 * The default empty constructor.
	 */
	public ListQueue(){
		list = new DoublyLinkedList();
	}
	
	/**
	 * Adds an item to the queue.
	 * @param item - The item to add.
	 */
	public void enqueue(Object item){
		if(list.getSize() == 0){
			list.addFront(item);
		}else {
			list.addEnd(item);
		}
	}
	
	
	/**
	 * Dequeues the first item in the queue and returns it. Writes an error
	 * message if the queue is empty and return null.
	 * @return The dequeued item.
	 */
	public Object dequeue(){
		try{
			return list.deleteFront().item;
		}catch(NullPointerException e){
			System.out.println("The queue is empty...");
			return null;
		}
	}
	
	
	/**
	 * Gets the current size of the queue.
	 * @return The size of the queue.
	 */
	public int size(){
		return list.getSize();
	}
	
	
	/**
	 * Tests whether the queue is empty.
	 * @return true if empty, false if not
	 */
	public boolean isEmpty(){
		return list.getSize() == 0;
	}
	
	
	/**
	 * Tests whether the queue is full.
	 * @return true if empty, false if not
	 */
	public boolean isFull(){
		return list.getSize() == 0;
	}
	

	/**
	 * convert information of ListQueue to string
	 */
	public String toString(){
	    String s = list.toString();
	    return s;
	}
	
	
	/**
	 * A main with which to test the implementation of the queue.
	 * @param args
	 */
	public static void main(String[] args)
	{
		//Declare & Initialize the ListQueue
		ListQueue q = new ListQueue();
	    
	    //Create a scanner object and a string for storing the input
	    Scanner scan = new Scanner(System.in);
	    String cmd;
	    
	    //Print the program's commands to the user.
	    System.out.println("Commands:\n\tenqueue OBJ\n\tdequeue\n\tsize\n\t" +
          "isEmpty\n\tisFull\n\tquit");
	    
	    //Loop through accepting input and printing returned information until quit
	    while (!(cmd=scan.next()).equalsIgnoreCase("quit"))
	    {
	    	if (cmd.equalsIgnoreCase("enqueue"))
	    	{
	    		q.enqueue(scan.next());
	    	}
	    	else if (cmd.equalsIgnoreCase("dequeue"))
	    	{
	    		System.out.println("Dequeued: "+q.dequeue());
	    	}
	    	else if (cmd.equalsIgnoreCase("size"))
	    	{
	    		System.out.println("Size: "+q.size());
	    	}
	    	else if (cmd.equalsIgnoreCase("isEmpty"))
	    	{
	    		System.out.println("IsEmpty: "+q.isEmpty());
	    	}
	    	else if (cmd.equalsIgnoreCase("isFull"))
	    	{
	    		System.out.println("IsFull: "+q.isFull());
	    	}
	    	
	    	//Show what the queue looks like.
	    	System.out.println("Contents:\n\t"+q.toString());
	    }
	}
}

