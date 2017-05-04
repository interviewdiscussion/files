package bplustree;

import java.io.*;

public class BTree{
    
    int degree;
    Node root;

    public static FileOutputStream outfile = null;
    private static int nodeNames = 1;

    public BTree (int d) {
		degree = d;
		root = null;
    }

    public static String nextNodeName() {
	    return "n" + String.valueOf(nodeNames++);
    }

    
    public void search (int val) {
	
		System.out.println("\n");
		
		System.out.println("SEARCH: ");
		
		if (root == null) 
		    System.out.println(val + " not found in empty tree.");
		else {
		    
		    Reference l = root.search (val);
		    if (l.getMatch ()) {
			     System.out.print(val + " found at index, " +l.getIndex ()+" in leaf node, ");
			l.getNode ().printNode ();
			      System.out.println(".");
		    } else
			     System.out.println(val + " not found in existing tree.");
		}
    }
  
    public void insert (int val) {
		
		System.out.println("\n");
		System.out.print("INSERT: ");
		if (root == null) {
		    
		    root = new LeafNode  (degree, val, null, null);
		    System.out.println(val);
		} else {
		
		    Reference l = root.search (val);
		   
		    
		    
		    if (l.getMatch ())
			   System.out.println(val + " is already in the tree.");
		    else {
		
			   l.getNode ().insert (val, null);
			   
			
			if (root.getParent() != null)
				
			    root = root.getParent().getNode();
			   
		    }
		}
		
	
    }   
 
    public void print () {
		if (root == null)
		    System.out.println("EMPTY TREE");
		else
		    root.print ();
    }
    
  
    public void delete (int val) {
	
		System.out.println("\n");
		System.out.println("DELETE: ");
		
		if (root == null)   
		    System.out.println(val + " not in empty tree.");
		else {
		    Reference l = root.search (val);
		    
		    
		    if (! l.getMatch ())     
				System.out.println(val + " not in tree");
		    else {
		    	
				l.getNode ().delete (l.getIndex ());
				
			if (root.getLast () == 0) {
			    root = root.getPtr (0);
			    if (root != null) root.parentref = null;
			  }
		    }
		}
		
    }
}
