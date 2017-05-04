
/**
 * This class implements a priority queue
 * based on the implementation of the hep
 * 
 * @author jinglu
 *
 */
public class MinPriorityQueue {
    public Heap q;
    
    /**
     * constructor for a min priority queue of a given size
     * @param size
     */
    public MinPriorityQueue(int size){
    	q = new Heap(size);
    }
    
    /**
     * constructor for a min priority queue when
     * user specifies all the process to store in an array
     * @param task
     */
    public MinPriorityQueue(Process[] task){
    	q = new Heap(task);
    }
    
    /**
     * enqueue operation to insert a process
     * @param p
     */
    public void enqueue(Process p){
    	q.insert(p);
    }
    
    /**
     * dequeue operation to remove a process
     * @return removed process
     */
    public Process dequeue(){
    	return q.removeMin();
    }
    
    /**
     * get the size of priority queue
     * @return size of queue
     */
    public int getSize(){
    	return q.getSize();
    }
    
    /**
     * check if priority queue is empty
     * @return true if queue is empty
     */
    public boolean isEmpty(){
    	return q.getSize()==0;
    }
    
}
