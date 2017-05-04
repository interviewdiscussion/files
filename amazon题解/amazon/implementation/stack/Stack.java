

/**
 * A basic implementation of a Stack
 * Jing Lu
 * jinglu@brandeis.edu
 */

public class Stack {
	//this array will store the data in the stack
	private Object[] array;
	private int top;
	private int maxSize;
		
	/**
	 * The default empty constructor. This initializes the max capacity
	 * of the stack to be 10.
	 */
	public Stack() {
		this.array = new Object[10];
		this.top = -1;
	}
		
	/**
	 * This constructor initializes the max capacity of the stack to
	 * be the specified value.
	 * @param capacity - The capacity of the stack.
	 */
	public Stack(int capacity){
		this.maxSize = capacity;
		this.array = new Object[this.maxSize];
		this.top = -1;
	}
	
	/**
	 * Pushes a new item onto the top of the stack
	 * @param item - the item to push
	 */
	public void push(Object item){
		// print error message if stack is full
		if(this.top >= this.maxSize-1){
			System.out.println("Stack Overflow!");
		}else{
			this.top += 1;
			this.array[this.top] = item;
		}
	}
		
	/**
	 * Pops an item from the stack.
	 * @return the item that was previously on top of the stack
	 */
	public Object pop(){
		// print error message if stack is empty and return null
		if(isEmpty() == true){
			System.out.println("Stack Underflow.");
			return null;
		}else{
			this.top -= 1;
			return this.array[this.top+1];
		}
	}
	
	/**
	 * Peeks at the top item on the stack without popping it.
	 * @return the top item of the stack
	 */
	public Object peek(){
		// print message if stack empty and return null
		if(isEmpty() == true){
			System.out.println("Stack Empty.");
			return null;
		}else{
			return this.array[this.top];
		}
	}

	/**
	 * Tests whether the stack is empty.
	 * @return true if empty, false if not
	 */
	public boolean isEmpty(){
		return this.top == -1;
	}
}
