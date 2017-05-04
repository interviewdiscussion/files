//Source: Data Structure and Algorithm Analysis in Java

public class ArrayList<E> implements Iterable<E>{
	private static final int CAPACITY=20;

	private int size;
	private E[] items;

	public ArrayList(){
		clearList();
	}

	public void clear(){
		clearList();
	}

	private void clearList(){
		size = 0;
		makeCapacity(CAPACITY);
	}

	public int size(){
		return size;
	}

	public boolean isEmpty(){
		return size()==0;
	}

	public void trimToSize(){
		makeCapacity(size());
	}

	public E get(int index){
		if(index<0 || index>=size()){
			throw new ArrayIndexOutOfBoundsException();
		}
		return items[index];
	}

	public E set(int index, E newItem){
		if(index<0 || index>=size()){
			throw new ArrayIndexOutOfBoundsException();
		}
		E oldItem = items[index];
		items[index] = newItem;
		return oldItem;
	}

	public void makeCapacity(int capacity){
		if(capacity < size){
			return;
		}
		E[] temp = items;
		items = (E[])new Object[capacity];
		for(int i=0; i<size(); i++){
			items[i] = temp[i];
		}

	}

	public boolean add(E item){
		add(size(), item);
		return true;
	}

	public void add(int index, E item){
		if(items.length == size()){
			makeCapacity(2*size()+1);
		}
		for(int i=size; i>index; i--){
			items[i] = items[i-1];
		}
		items[index] = item;
		size++;
	}

	public E remove(int index){
		E removed = items[index];
		for(int i=index; i<size()-1; i++){
			items[i] = items[i+1];
		}
		size --;
		return removed;
	}

	public java.util.Iterator<E> iterator(){
		return new ArrayListIterator();
	}

	private class ArrayListIterator implements java.util.Iterator<E>{
		private int currIdx = 0;

		public boolean hasNext(){
			return currIdx<size();
		}

		public E next(){
			if(!hasNext()){
				throw new java.util.NoSuchElementException();
			}
			return items[currIdx++];
		}

		public void remove(){
			ArrayList.this.remove(--currIdx);
		}
	}
}