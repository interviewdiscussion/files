package bplustree;
import java.lang.*;

public class Reference{
    
    private Node node;               
    private int index;                 
 
    private boolean match;               
   
    public Reference (Node p, int i, boolean m){
      	node = p;
      	index = i;
      	match = m;
    }
    
 
    public Node getNode () {return node;}
   
    public int getIndex () {return index;}
   
    public boolean getMatch () {return match;}
 
    void setIndex (int i) {index = i;}
    
    public void increaseIndex () {index++;}
  
    public void decreaseIndex () {index--;}
}
