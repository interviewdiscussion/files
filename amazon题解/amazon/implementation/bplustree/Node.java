package bplustree;
import java.io.*;

public abstract class Node{
    int degree;                
    int lastindex;             
    
    Node[] ptrs;    
    
    
    int[] keys;      
    
    Node next, prev;   

    
    Reference parentref;     
    
    
    public String myname; 
    
    protected Node (int d, Node n, Node p){
      	degree = d;
      	ptrs = new Node[degree]; 
      	keys = new int[degree];
      	next = n;
      	prev = p;
      	
      	if (p != null) p.next = this;
      	if (n != null) n.prev = this;
      	
      	lastindex = 0;
      	parentref = null;
      	
      	myname = BTree.nextNodeName();
    }
      

    public int getLast () {
        return lastindex;
    }
    
  
    public Node getPtr (int i) {
      	return ptrs [i];
    }
 
    public int getKey (int i) {
	      return keys [i];
    }
 
    public Node getNext () {
	      return next;
    }
    
   
    public Node getPrev () {
	      return prev;
    }
    
  
    public Reference getParent () {
	      return parentref;
    }
 
    int maxkeys () {
	    return degree - 1;
    }

    int getLevelKeyNumber() {
      	int i = lastindex;

      	Node p = this;
      	while ((p = p.prev) != null) i += p.getLast();
      	
      	p = this;
      	while ((p = p.next) != null) i += p.getLast();
      	
      	return i;
    }
 
    int getMaxLevelKeyNumber(){
      	int i = 1;
      	int total = 1;
      	int level = 1;
      	Node p = this;

      	while(p.getParent() != null){
      	    p = p.getParent().getNode();
      	    level++;
      	}
      	
      	while (i < level) {
      	    total *= degree;
      	    i++;
      	}
      	
      	return total * (degree -1);
    }
    

    public boolean full () {
	      return lastindex == degree - 1;
    }
    

    public boolean siblings (Node other){
    	  
	      return ((other != null) && (parentref.getNode () == other.getParent ().getNode ()));
	
    }

    public void setParent (Reference l) {
        parentref = l;
    }
    
 
    public void setNext (Node n) {
        next = n;
    }
 

    public void setPrev (Node p) {
        prev = p;
    }
    

    void setInitPtr (Node p) {
       ptrs [0] = p;
    }
    
 
    public int findKeyIndex (int val){
        //return lastindex if val>=last key
    	if(val >= this.keys[this.lastindex]){
    		return this.lastindex;
    	}
    	//return i such that keys[i-1]<val<=keys[i]
    	for(int i=2; i<= this.lastindex; i++){
    		if(this.keys[i-1]<val && val<=this.keys[i]){
    			return i;
    		}
    	}
        //return 1 if val<keys[1]
    	return 1;
    }
    
  
    public int findPtrIndex (int val){	
    	//return 0 if val<keys[1]
    	if(val<keys[1]){
    		return 0;
    	}
    	//return lastindex if val>=keys[lastindex]
    	if(val >= keys[this.getLast()]){
    		return this.getLast();
    	}
    	//return i such that keys[i]<=val<keys[i+1]
	    for(int i=1; i<this.getLast(); i++){
	    	if(keys[i]<=val && val<keys[i+1]){
	    		return i;
	    	}
	    }
	    
	    return 0;
    }
    

    void shiftleft (int i){
      	ptrs [0] = ptrs [i];

      	if (ptrs [0] != null) ptrs [0].getParent ().setIndex (0);

      	for (int j = i+1; j <= lastindex; j++) {
      	    ptrs [j - i] = ptrs [j];
      	    keys [j - i] = keys [j];
      	    if (ptrs [j - i] != null) ptrs [j - i].getParent ().setIndex (j - i);
      	}

      	lastindex -= i;
    }
    
  
    void print (){
      	int indent = getDepth()* degree * 3 + (1 - getLevelKeyNumber()/getMaxLevelKeyNumber()) * 3 ;
      	
      	for (int i = 0; i < indent; i++){
      	    System.out.print(" ");
      	}
      	printLevel ();
      	if (ptrs [0] != null) ptrs [0].print ();
    }

    void printLevel (){
      	printNode ();
      	if (next != null) {
      	    if ( siblings (next))
      		System.out.print ("--");
      	    else 
      		System.out.print ("   ");
      	    next.printLevel ();
      	}else{
      	    System.out.println ();
      	}
    }
    
   
    int getDepth(){
      	int i = 0;
      	Node p = this;

      	while ((p = p.ptrs[0]) != null)i++;
      	return i;
    }
  
    public void delete (int i){
    	//first perform deletesimple
    	this.deleteSimple(i);
    	//check to see if deleted node is underfull
    	if(getParent() != null && this.getLast() < this.minkeys()){
    		if(siblings(this.getNext()) && combinable(this.getNext())){
    			//combine with next sibling
    			this.combine();
    		}else if(siblings(this.getPrev()) && combinable(this.getPrev())){  
    			//combine with prev sibling
    			this.getPrev().combine();
    		}else if(siblings(this.getNext())){
    			//redistribute with next sibling
    			this.redistribute();
    		}else if(siblings(this.getPrev())){
    			//redistribute with prev sibling
    			this.getPrev().redistribute();
    		}
    	}
    	
    }
    
  
    public abstract int minkeys ();

    abstract void printNode ();
    
    public abstract boolean combinable (Node other);
    
    public abstract void combine ();
  
    public abstract int redistribute ();
 
    public abstract void insertSimple (int val, Node ptr, int i);
      
    public abstract void deleteSimple (int i);
    
    public abstract Reference search (int val);
    
    public abstract void insert (int val, Node ptr);
    
}
