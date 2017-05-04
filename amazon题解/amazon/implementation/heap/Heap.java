
/**
 * This class implements Heap
 * which will be used in the implementation of priority queue
 * @author jinglu
 *
 */
public class Heap {
    Process[] array;
    int currentSize;
    
    /**
     * default constructor with size 10
     */
    public Heap(){
    	this.currentSize = 0;
    	this.array = new Process[10];
    }
    
    /**
     * constructor when user specifies the size
     * @param size
     */
    public Heap(int size){
    	this.currentSize = 0;
    	this.array = new Process[size];
    }
    
    /**
     * constructor when user specifies all the processes to store in an array
     * @param input
     */
    public Heap(Process[] input){
    	this.currentSize = input.length;
    	this.array = new Process[currentSize];
    	int i = 0;
    	//insert process into the heap and build heap
    	for(Process item: input){
            array[i] = item;
            i++;
    	}
    	buildHeap();
    }
    
    /**
     * get the size of the heap
     * @return size of the heap
     */
    public int getSize(){
    	return this.currentSize;
    }
    
    /**
     * get the index of the parent
     * @param i
     * @return index of the parent
     */
    public int parentIndex(int i){
    	return (int)Math.floor(i/2);
    }
    
    /**
     * get the index of the left child
     * @param i
     * @return index of left child
     */
    public int leftIndex(int i){
    	return 2*i;
    }
    
    /**
     * get the index of the right child
     * @param i
     * @return index of right child
     */
    public int rightIndex(int i){
    	return (2*i+1);
    }
    
    /**
     * heapify down for a min-heap
     * swap with left child if children have same priority
     * @param i
     * @throws ArrayIndexOutOfBoundsException
     */
    public void heapifyDown(int i) throws ArrayIndexOutOfBoundsException{
    
    	int l = leftIndex(i);
    	int r = rightIndex(i);
    	int smallest;
    	
    	//find index of smallest key in the children
    	if(l<=getSize()-1 && array[l].getPriority()<=array[i].getPriority()){
    		 smallest = l;
    	}else{
    		 smallest = i;
    	}
    	if(r<=getSize()-1 && array[r].getPriority()<array[smallest].getPriority()){
    		smallest = r;
    	}
    	
    	//swap with child with smallest key if found
    	if(smallest != i){
    		Process temp = array[i];
    		array[i] = array[smallest];
    		array[smallest] = temp;
    		heapifyDown(smallest);
    	} 	
    }
    
    /**
     * heapify up for a min-heap
     * @param i
     */
    public void heapifyUp(int i){
    	//keep going up if key is smaller than its parent
    	while(i>0 && array[i].getPriority() < array[parentIndex(i)].getPriority()){
    		Process temp = array[i];
    		array[i] = array[parentIndex(i)];
    		array[parentIndex(i)] = temp;
    		i = parentIndex(i);
    	}
    }
    
    /**
     * build a min-heap
     */
    public void buildHeap(){
    	for(int i = this.currentSize/2; i>=0; i--){
    		heapifyDown(i);
    	}
    }
    
    /**
     * remove process with smallest key
     * @return process with smallest key
     */
    public Process removeMin(){
    	if(getSize()<1){
    		System.out.println("no element to remove");
    		return null;
    	}
    	Process p = array[0];
    	array[0] = array[getSize()-1];
    	this.currentSize --;
    	heapifyDown(0);
    	return p;
    }
	
    /**
     * decrease the key value of a given process with index i to a new value
     * @param i index of the process
     * @param newKey new key value
     */
	public void decreasePriority(int i, int newKey){
		if(newKey > array[i].getPriority()){
			System.out.println("new key is larger than current key");
		}else{
			array[i].setPriority(newKey);
			heapifyUp(i);
		}
		
	}
	
	/**
	 * insert a new process into the heap
	 * @param p
	 * @throws NullPointerException
	 */
	public void insert(Process p) throws NullPointerException{
		//if heap is not large enough, enlarge the heap
		if(this.currentSize==this.array.length-1){
			enlargeHeap(currentSize);
		}
		this.currentSize ++;
		//choose a big number as a tempory key value
		int inf = 100000000;
		int target = p.getPriority();
		array[getSize()-1] = p;
		array[getSize()-1].setPriority(inf);
		int i = getSize()-1;
		decreasePriority(i,target);
		
	}
	
	/**
	 * enlarge the heap by doubling the size
	 * @param size
	 */
	public void enlargeHeap(int size){
		Process[] oddArray = this.array;
		this.array = new Process[2*size];
		int i = 0;
		//store old process into the new heap
		for(Process p: oddArray){
			this.array[i] = p;
			i ++;
		}
	}


}
