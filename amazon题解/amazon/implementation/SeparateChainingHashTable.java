//Source: Data Structure and Algorithm Analysis in Java


//hash table using separate hash table

import java.util.*;
public class SeparateChainingHashTable{
	private static final int DEFAULT_SIZE = 101;
	private List<E>[] lists ;
	private int currentSize;

	public SeparateChainingHashTable(){
		this(DEFAULT_SIZE);
	}

	public SeparateChainingHashTable(int size){
		lists = new LinkedList[nextPrime(size)];
		for(int i=0; i<lists.length; i++){
			lists[i] = new LinkedList<>();
		}


	}

	public void insert(E element){
		List<E> whichList = lists[myhash(element)];
		if(!whichList.contains(element)){
			whichList.add(element);

			//rehash if needed
			if(++currentSize > lists.length){
				rehash();
			}
		}

	}

	public void remove(E element){
		List<E> whichList = lists[myhash(element)];
		if(whichList.contains(element)){
			whichList.remove(element);
			currentSize --;
		}

	}

	public boolean contains(E element){
		List<E> whichList = lists[myhash(element)];
		return whichList.contains(element);
	}

	public void makeEmpty(){
		for(int i=0; i<lists.length; i++){
			lists[i].clear();
		}
		currentSize = 0;

	}

	private void rehash(){
		List<E>[] oldLists = lists;

		lists = new List[nextPrime(2*oldLists.length)];
		for(int i=0; i<lists.length; i++){
			lists[i] = new LinkedList<>();
		}

		currentSize = 0;
		for(int i=0; i<oldLists.length; i++){
			for(E item: oldLists[i]){
				insert(item);
			}
		}

	}
    
    //this means that this hash table only works for objects that are comparable
    //objects must provide equals() and hashCode() method
	private int myhash(E element){
		//use hash code
		int hashVal = element.hashCode();
		hashVal %= lists.length;
         
        //make positive
		if(hashVal < 0){
			hashVal += lists.length;
		}

		return hashVal;

	}

	private static int nextPrime(int n){

	}

	private static boolean isPrime(int n){

	}


}