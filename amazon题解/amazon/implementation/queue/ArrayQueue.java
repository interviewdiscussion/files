
import java.util.Scanner;

/**
 * A basic implementation of a queue using an array.
 * Jing Lu
 * jinglu@brandeis.edu
 */
public class ArrayQueue {
	//this array will store the data in the queue.
	private Object[] array;
	//front and rear used to record data distribution in the queue
	private int front;
	private int rear;
	private int maxSize;
	
	/**
	 * The default empty constructor. This initializes the max capacity
	 * of the queue to be 10.
	 */
	public ArrayQueue(){
		this.array = new Object[4];
		this.front = 0;
		this.rear = 0;
		this.maxSize = 4;
	}

	/**
	 * The default empty constructor. This initializes the max capacity
	 * of the queue to be the specified capacity.
	 * @param capacity - The capacity of the queue.
	 */
	public ArrayQueue(int capacity){
		this.maxSize = capacity;
		this.array = new Object[maxSize];
		this.rear = 0;
		this.front = 0;
	}
	
	/**
	 * Adds an item to the queue. Writes an error message if the queue is full
	 * @param item - The item to add.
	 */
	public void enqueue(Object item){	
		if(isFull() == true){
			System.out.println("The queue is full...");
		}else {
			this.array[this.rear] = item;
			//use mod to make full use of available space
			this.rear = (this.rear+1) % this.maxSize;
		}
	}

	
	/**
	 * Dequeues the first item in the queue and returns it. Writes an error
	 * message if the queue is empty.
	 * @return The dequeued item.
	 */
	public Object dequeue(){
		if(isEmpty() == true){
			System.out.println("The queue is empty...");
			return null;
		}else {
			Object target = this.array[this.front];
			//use mod to handle circular queue situation
			this.front = (this.front+1) % this.maxSize;
			return target;
		}
	}
		
	/**
	 * Gets the current size of the queue.
	 * @return The size of the queue.
	 */ 
	public int size(){	
		if(this.front < this.rear){
			return this.rear-this.front;
		}else if(this.front > this.rear){
			//circular queue situation
			return maxSize-this.front+this.rear;
		}else {
			//the following handles front=rear in circular and non-circular situation
			//if front=rear and nothing in front or rear, then queue is empty
            if(this.array[this.front] == null){
            	return 0;
            }else{
            	//if front=rear and something in front or rear, then queue is full
            	return this.maxSize;
            }
		}
	}
		
	/**
	 * Tests whether the queue is empty.
	 * @return true if empty, false if not
	 */
	public boolean isEmpty(){
		return size() == 0;
	}
		
	/**
	 * Tests whether the queue is full.
	 * @return true if empty, false if not
	 */
	public boolean isFull(){
		return size() == this.maxSize;
	}
	
	/**
	 * convert element information in the queue to string
	 * @return information of each element in the queue
	 */
	public String toString(){
		String s = "";
		int pointer =  this.front;
		//print every element in the queue
		if(isEmpty() == false){
		    for (int i=1;i<=size();i++){
			    s += "["+array[pointer].toString()+"]";
			    pointer = (pointer+1) % this.maxSize;
		    }
		    //new line and indent
		    s += "\n\t"; 
		    //add information about current queue size, front and rear
		    s += "The current size of the quence is " + size() + ".";
		    s += "The front is at index " + this.front + " and the rear is at index " + this.rear + ".";
		}else{
			s += "The queue is empty...";
		}
		return s;
	}
	
	/**
	 * A main with which to test the implementation of the queue.
	 * @param args - the capacity of the queue
	 */
	public static void main(String[] args){
		//Declare the ArrayQueue
		ArrayQueue q;
		
		//Initialize the capacity from the args
	    if (args.length > 0)
	    	q = new ArrayQueue(Integer.parseInt(args[0]));
	    else
	    	q = new ArrayQueue();
	    
	    //Create a scanner object and a string for storing the input
	    Scanner scan = new Scanner(System.in);
	    String cmd;
	    
	    //Print the program's commands to the user.
	    System.out.println("Commands:\n\tenqueue OBJ\n\tdequeue\n\tsize\n\t" +
          "isEmpty\n\tisFull\n\tquit");
	    
	    //Loop through, accepting input and printing returned information until quit
	    while (!(cmd=scan.next()).equalsIgnoreCase("quit")){
	    	if (cmd.equalsIgnoreCase("enqueue")){
	    		q.enqueue(scan.next());
	    	}
	    	else if (cmd.equalsIgnoreCase("dequeue")){
	    		System.out.println("Dequeued: "+q.dequeue());
	    	}
	    	else if (cmd.equalsIgnoreCase("size")){
	    		System.out.println("Size: "+q.size());
	    	}
	    	else if (cmd.equalsIgnoreCase("isEmpty")){
	    		System.out.println("IsEmpty: "+q.isEmpty());
	    	}
	    	else if (cmd.equalsIgnoreCase("isFull")){
	    		System.out.println("IsFull: "+q.isFull());
	    	}
	    	
	    	//Show what the queue looks like
	    	System.out.println("Contents:\n\t"+q.toString());
	    }
	}
}
