package bplustree;
import java.io.*;
import java.lang.Math.*;
import java.util.*;

public class LeafNode extends Node {
    
    
    public LeafNode (int d, int k, Node n, Node p){
      	super (d, n, p);
      	keys [1] = k;
      	lastindex = 1;
    }      
    
    
    public int minkeys () {
    	//if root, return 1
    	if(this.getParent()==null){
			return 1;
		}else{
			return (int) (Math.ceil((degree-1)/2.0));
		}
      	
    }
    
   
    public boolean combinable (Node other){
    	int keyNum = getLast();
	    int otherKeyNum = other.getLast();
	    
	    //check if total num of keys exceed maxkeys
	    return (keyNum+otherKeyNum<=maxkeys());
	    
    }

    
    public void combine (){
    	//add keys to current node
    	for(int k=1; k<=this.getNext().getLast(); k++){
    		this.keys[this.getLast()+k] = this.getNext().getKey(k);
    	}
    	this.lastindex = this.getLast() + this.getNext().getLast();
    	
    
    	//set next node of current node
    	this.setNext(this.getNext().getNext());	
    	//if next node is not null, set its prev to be current node
    	if(this.getNext()!=null){
    		this.getNext().setPrev(this);
    	}
    	
    	//delete key in parent node because of the combine
    	this.getParent().getNode().delete(this.getParent().getIndex()+1);
 
    }

    public int redistribute (){  
    	//decide the new size of current node
    	int newSize = (int)Math.ceil((this.getLast()+this.getNext().getLast())/2.0);
    	//create a list to store all keys of current node and next node
    	List<Integer> allKeys = new ArrayList<Integer>();
    	
    	//add keys of current node into the list
    	for(int k=1; k<=this.getLast(); k++){
    		allKeys.add(this.getKey(k));		
    	}
    	
    	//add keys of next node into the list
    	for(int k=1; k<=this.getNext().getLast(); k++){
    		allKeys.add(this.getNext().getKey(k));
    	}
    	
    	//sort the list
    	Collections.sort(allKeys);
    	
    	//reset lastindex of current node to zero
    	this.lastindex = 0;
    	//add keys to current node and keep incrementing lastindex
    	for(int k=1; k<=newSize; k++){
    		this.keys[k] = allKeys.get(k-1);
    		this.lastindex ++;
    	}
    	
    	//reset lastindex of next node to zero
    	this.getNext().lastindex = 0;
    	//add keys to next node and keep incrementing lastindex
    	for(int k=1; k<=allKeys.size()-newSize; k++){
    		this.getNext().keys[k] = allKeys.get(newSize+k-1);
    		this.getNext().lastindex ++;
    	}
    	
    	//decide val to be inserted into parent
    	int parentVal = this.getNext().getKey(1);
    	
    	//if parent is not null, update val in the parent
    	if(this.getNext().getParent()!=null){
    		int parentIndex = this.getNext().getParent().getIndex();
    		this.getParent().getNode().keys[parentIndex] = parentVal;
    	}
    	//return val to be inserted into parent
    	return parentVal;
    }
   
    public void insertSimple (int val, Node ptr, int i){
    	//creaet a list to store keys
        List<Integer> tempKeys = new ArrayList<Integer>();
        
        //add keys after position i to the list
        for(int k=i; k<=this.getLast(); k++){
        	tempKeys.add(this.getKey(k));
        }
        
        //insert new value
        this.keys[i] = val;
   
        //add keys to current node from previous list
        int end = tempKeys.size();
        for(int j=i+1; j<=i+end;j++){
        	this.keys[j] = tempKeys.get(j-i-1);
        }

        //increment lastindex because of insertion
        this.lastindex ++;   

    }
    
   
    public void deleteSimple (int i){
        //create a list to store keys
        List<Integer> tempKeys = new ArrayList<Integer>();
        //store keys after position i into list
        for(int k=i+1; k<=this.getLast(); k++){
        	tempKeys.add(this.getKey(k));
        }
        
        //add keys to current node from list
        int end = tempKeys.size();
        for(int k=i; k<i+end; k++){
        	int rem = tempKeys.get(k-i);
        	this.keys[k] = rem;
        }
        //set last key to be 0
        this.keys[this.getLast()] = 0;
        //decrement lastindex because of deletion
        this.lastindex --;
        
        
        //if we delete the first key, we need to update value in its predecessor
        if(i == 1){
        	//use target,p, stop to keep track
        	Node target = this;
        	int p = 0;
        	boolean stop = false;
        	//keep going up in the tree until reach the top or index into parent is not zero
        	while(target.getParent()!=null && !stop){
        		//update index p and target node
        		p = target.getParent().getIndex();
        		target = target.getParent().getNode();
        		//check if we need to continue
        		stop = (p!=0);
        	}
        	
        	//we stop because index into parent is not zero
        	if(stop){
        		//if no keys in current node, 
        		//we update the value in the target node by the first key of next node of current node
        		if(this.getLast() == 0){
        			//check if next node of current node exists
        			if(this.getNext() != null){
        			   target.keys[p] = this.getNext().getKey(1);
        			}
        		}else{
        			//if current node has keys, update value in the target node by the first key of current node
        			target.keys[p] = this.getKey(1);
        		}
        	}
        }
        
   
    } 
    
   
    public Reference search (int val){
      	int i = findKeyIndex(val);
      	//if value is found, set match to true
      	if(this.getKey(i) == val){
      		return new Reference(this, i, true);
      	//if not found, set match to false
      	}else{
      		return new Reference(this, i, false);
      	}
    }
    
    public void insert (int val, Node ptr){
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
     	   Node split = new LeafNode(degree, val, this.getNext(), this);
     	   //set split to be next node of current node
     	   this.setNext(split);
     	   //get val to be inserted into parent
     	   int parentVal = this.redistribute();
     	   
     	   
     	   
     	   //if current node has a parent, insert value into parent
     	   if(this.getParent() != null){
     		   split.setParent(new Reference(this.getParent().getNode(), -1, false));
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
    
    void printNode (){
    	System.out.print ("[");
    	for (int i = 1; i < lastindex; i++) 
    	    System.out.print (keys[i]+" ");
    	System.out.print (keys[lastindex] + "]");
    }
}
