package bplustree;
import java.io.*;
import java.lang.Math.*;
import java.util.*;

public class InternalNode extends Node{
    
    
    public InternalNode (int d, Node p0, int k1, Node p1, Node n, Node p){
	
		super (d, n, p);
		ptrs [0] = p0;
		keys [1] = k1;
		ptrs [1] = p1;
		lastindex = 1;
		
		if (p0 != null) p0.setParent (new Reference (this, 0, false));
		if (p1 != null) p1.setParent (new Reference (this, 1, false));
    }
    
    
    public int minkeys () {
    	//if node is the root, minkey is 1
		if(getParent()==null){
			return 1;
		}else{
			return (int) (Math.ceil(degree/2.0)-1);
		}
    }
    
    
    public boolean combinable (Node other) {
	    int keyNum = getLast();
	    int otherKeyNum = other.getLast();
	    
	    //check if the sum of number of keys exceeds maxkeys
	    return (keyNum+otherKeyNum+1<=maxkeys());
	  
    }
    
    
    public void combine () {
    	
    	//get the reference index of current node
    	int parentIndex = this.getNext().getParent().getIndex();
    	//get the value at this index in the parent node
    	int parentVal = this.getNext().getParent().getNode().getKey(parentIndex);
    	
    	
    	//add parentVal the current node
    	this.keys[this.getLast()+1] = parentVal;
    	//add the first ptr of next node to current node
    	this.ptrs[this.getLast()+1] = this.getNext().getPtr(0);
    	//set parent reference for the new ptr
    	this.ptrs[this.getLast()+1].setParent(new Reference(this,this.getLast()+1,false));
    	


      //advance last index
      this.lastindex ++;
    	
    	//move the keys and ptrs from next node to current node
    	for(int k=1; k<=this.getNext().getLast(); k++){
    		//add key
    		this.keys[this.getLast()+k] = this.getNext().getKey(k);
    		//add ptr
    		this.ptrs[this.getLast()+k] = this.getNext().getPtr(k);
    		//set parent reference for the new ptr
    		this.ptrs[this.getLast()+k].setParent(new Reference(this,this.getLast()+k,false));
    	}
    	this.lastindex = this.getLast() + this.getNext().getLast();
    	
       

    	//set next node of current node
    	this.setNext(this.getNext().getNext());	
    	//if next node is not null, set its prev to be current node
    	if(this.getNext()!=null){
    		this.getNext().setPrev(this);
    	}
 		
 	    //delete keys in parent node because of the combine of two child nodes
 		this.getParent().getNode().delete(this.getParent().getIndex() + 1);
 		
    }
    
    
    public int redistribute () {
    	int parentVal;
    	//if the first ptr in next node is null
    	//then we know redistribution is used for insertion
    	//otherwise, it is used for deletion
    	if(this.getNext().getPtr(0) == null){
    		parentVal = insertionRedistribute();
    	}else{
    		parentVal = deletionRedistribute();
    	}
    	//return value to be updated in the parent
    	return parentVal;
            
    }
    
    
    public int insertionRedistribute(){
    	//get val and ptr from newly created node
    	int val = this.getNext().getKey(1);
    	Node ptr = this.getNext().getPtr(1);
    	
       boolean stop = false;
       int p = 1;
       while(p<=this.getLast() && !stop){
     
           stop = this.getKey(p)>this.getNext().getKey(1);
           if(!stop){
             p++;
           }
       }


		//create two arrays to store keys and ptrs
		int size = this.getLast()+this.getNext().getLast()+1;
		int[] allKeys = new int[size];
		Node[] allPtrs = new Node[size];
		
		//store keys and ptrs of current node at position 1 to p-1 into array
		allPtrs[0] = this.getPtr(0);
		for(int k=1; k<p; k++){
			allKeys[k] = this.getKey(k);
			allPtrs[k] = this.getPtr(k);
		}
		
		//store first key and ptr of next node
		allKeys[p] = val;
		allPtrs[p] = ptr;
		
		//store keys and ptrs of current node at position p to lastindex into array
		for(int k=p; k<=this.getLast(); k++){
			allKeys[k+1] = this.getKey(k);
			allPtrs[k+1] = this.getPtr(k);
		}
        
		//decide middle position and new size for current and next node
		int middle = (int)Math.ceil((size)/2.0);
		int newSize = middle - 1;
		int newSizeNext = size - 1 - middle;
		
		//reset lastindex of current node and store keys and ptrs into current node
		this.lastindex = newSize;
		this.ptrs[0] = allPtrs[0];
		this.ptrs[0].setParent(new Reference(this, 0, false));
		for(int k=1; k<=this.getLast(); k++){
			this.keys[k] = allKeys[k];
			this.ptrs[k] = allPtrs[k];
			this.ptrs[k].setParent(new Reference(this, k, false));
		}
		
		//reset lastindex of next node and store keys and ptrs into next node
		this.getNext().lastindex = newSizeNext;
		this.getNext().ptrs[0] = allPtrs[middle];
		this.getNext().ptrs[0].setParent(new Reference(this.getNext(), 0, false));
		for(int k=1; k<=this.getNext().getLast(); k++){
			this.getNext().keys[k] = allKeys[middle+k];
			this.getNext().ptrs[k] = allPtrs[middle+k];
			this.getNext().ptrs[k].setParent(new Reference(this.getNext(), k, false));
		}
		
		//return value to be updated in the parent
		return allKeys[middle];
    }
    
 
    public int deletionRedistribute(){
    	//decide middle position and new size of current and next node
    	int size = this.getLast()+this.getNext().getLast()+2;
    	int middle = (int)Math.ceil(size/2.0);
    	int newSize = middle - 1;
    	int newSizeNext = size - 1 - middle;
    	
    	//get special key and val to be inserted into array
    	int val = this.getNext().getParent().getNode().getKey(this.getNext().getParent().getIndex());
    	Node ptr = this.getNext().getPtr(0);
    	
    	//create two arrays to store keys and ptrs
    	int[] allKeys = new int[size];
    	Node[] allPtrs = new Node[size];
    	
    	//store keys and ptrs from current node
    	allPtrs[0] = this.getPtr(0);
 	    for(int k=1; k<=this.getLast(); k++){
 	    	allKeys[k] = this.getKey(k);
 	    	allPtrs[k] = this.getPtr(k);
 	    }
 	    
 	    //insert special key and ptr
 	    allKeys[this.getLast()+1] = val;
 	    allPtrs[this.getLast()+1] = ptr;
 	    
 	    //store keys and ptrs from next node
 	    for(int k=1; k<=this.getNext().getLast(); k++){
 	    	allKeys[this.getLast()+1+k] = this.getNext().getKey(k);
 	    	allPtrs[this.getLast()+1+k] = this.getNext().getPtr(k);
 	    }
 	    
 	    //update lastindex of current and next node
 	    this.lastindex = newSize;
 	    this.getNext().lastindex = newSizeNext;
 	    
 	    //update keys and ptrs in current node
 	    this.ptrs[0] = allPtrs[0];
 	    this.ptrs[0].setParent(new Reference(this, 0, false));
 	    for(int k=1; k<=this.getLast(); k++){
 	    	this.keys[k] = allKeys[k];
 	    	this.ptrs[k] = allPtrs[k];
 	    	this.ptrs[k].setParent(new Reference(this, k, false));
 	    }
 	    
 	    //update parent
 	    this.getNext().getParent().getNode().keys[this.getNext().getParent().getIndex()] = allKeys[middle];
 	    
 	    //update keys and ptrs in next node
 	    this.getNext().ptrs[0] = allPtrs[middle];
 	    this.getNext().ptrs[0].setParent(new Reference(this.getNext(), 0, false));
 	    for(int k=1; k<=this.getNext().getLast(); k++){
 	    	this.getNext().keys[k] = allKeys[middle+k];
 	    	this.getNext().ptrs[k] = allPtrs[middle+k];
 	    	this.getNext().ptrs[k].setParent(new Reference(this.getNext(), k, false));
 	    }
 	    
 	    //return value to be updated in the parent
 	    return this.getKey(middle);
    }

    
    public void insertSimple (int val, Node ptr, int i) {
    	//declare two lists to store keys and ptrs
		List<Integer> tempKeys = new ArrayList<Integer>();
		List<Node> tempPtrs = new ArrayList<Node>();
		
		//store keys and ptrs into list
		for(int j=i; j<=this.getLast(); j++){
			tempKeys.add(this.getKey(j));
			tempPtrs.add(this.getPtr(j));
		}
		
		//set keys and ptrs at position i
		this.keys[i] = val;
		this.ptrs[i] = ptr;
		//set parent reference
		this.ptrs[i].setParent(new Reference(this,i,false));
		
		
		int end = tempKeys.size();
		//add keys and ptrs to current node from previous lists
		for(int j=i+1; j<=i+end; j++){
			this.keys[j] = tempKeys.get(j-i-1);
			this.ptrs[j] = tempPtrs.get(j-i-1);
			//set parent reference
			this.ptrs[j].setParent(new Reference(this,j,false));
		}
		
		//increase lastindex because of insertion
		this.lastindex ++;
    }
    
   
    public void deleteSimple (int i) {
    	
    	//shift keys and ptrs after position i to left by one unit
    	for(int k=i; k<=this.getLast()-1; k++){
    		this.keys[k] = this.keys[k+1];
    		this.ptrs[k] = this.ptrs[k+1];
    		//set parent reference
    		this.ptrs[k].setParent(new Reference(this, k, false));
    	}
    	
    	//decrement lastindex because of deletion
    	this.lastindex --;
    	
    }
    
    
   
    public Reference search (int val) {
    	
    	//find index of target node for next round search
    	int p = this.findPtrIndex(val);
    	Node target = this.getPtr(p);
    	
    	//call itself recursively
    	Reference result = target.search(val);
    	
    	return result;
    }
    
    
    public void insert (int val, Node ptr) {
       //find correct position to insert
       int p = this.findKeyIndex(val);
       
       //if node is not full
       if(!full()){
    	   //if val is larger than last key, increment p
    	   if(val > this.getKey(this.getLast())){
    		   p++;
    	   }
    	   //insertion for simple case
    	   this.insertSimple(val, ptr, p);   
       }else{
    	   //create a neighbor node with val and ptr
    	   Node split = new InternalNode(degree, null, val, ptr, this.getNext(), this);
    	   //set split to be next node of current node
    	   this.setNext(split);
    	   //get val to be inserted into parent
    	   int parentVal = this.redistribute();
    	   
    	   //if current node has a parent, insert value into parent
    	   if(this.getParent() != null){
    		   this.getParent().getNode().insert(parentVal, split);
    	   //if no parent for current node, create a new root
    	   }else{
    		   Node newRoot = new InternalNode(degree, this, parentVal, split, null, null);
    		   //set parent references
    		   this.setParent(new Reference(newRoot, 0, false));
    		   split.setParent(new Reference(newRoot, 1, false));
    	   }
       }
	
    }
    
    
    
    void printNode () {
	
		int i, j, ind;
		Reference par;
		
		System.out.print("[");
		for (j = 0; j <= lastindex; j++) {
		    if (ptrs [j] == null) {
				par = null; 
				ind = -1;
		    } else {
			par = ptrs [j].getParent ();
			if (par == null)
			    ind = -1;
			else
			    ind = ptrs [j].getParent ().getIndex ();
		    }

		    if (j == 0)
			System.out.print (" * ");
		    else
			System.out.print(keys[j] + " * ");

		    if (j == lastindex)
			System.out.print ("]");
		}
	 }
    
    
}

