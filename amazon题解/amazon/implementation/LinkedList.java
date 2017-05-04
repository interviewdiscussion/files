//Source: Data Structure and Algorithm Analysis in Java

public class LinkedList<E> implements Iterable<E>{
	private static class Node<E>{
		public E data;
		public Node<E> prev;
		public Node<E> next;

		public Node(E data, Node<E> prev, Node<E> next){
			this.data = data;
			this.prev = prev;
			this.next = next;
		}
	}

	private int size;
	private int changeCount = 0;
	private Node<E> begin;
	private Node<E> end;

	public LinkedList(){
		clearList();
	}

	public void clear(){
		clearList();
	}

	private void clearList(){
		begin = new Node<E>(null, null, null);
		end = new Node<E>(null, begin, null);
		begin.next = end;
		size = 0;
		changeCount ++;
	}

	public int size(){
		return size;
	}

	public boolean isEmpty(){
		return size()==0;
	}

	public boolean add(E item){
		add(size(), item);
		return true;
	}

	public void add(int index, E item){
		addBefore(getNode(index, 0, size()), item);
	}

	private void addBefore(Node<E> p, E item){
		Node<E> newNode = new Node<>(item, p.prev, p);
		newNode.prev.next = newNode;
		p.prev = newNode;
		size ++;
		changeCount ++;
	}

	public E get(int index){
		return getNode(index).data;
	}

	private Node<E> getNode(int index){
		return getNode(index, 0, size()-1);
	}

	private Node<E> getNode(int index, int low, int high){
		Node<E> p;
		if(index<low || index>high){
			throw new IndexOutOfBoundsException();
		}

		if(index < size()/2){
			p = begin.next;
			for(int i=0; i<index; i++){
				p = p.next;
			}
		}else{
			p = end;
			for(int i=size(); i>index; i--){
				p = p.prev;
			}
		}
		return p;
	}

	public E set(int index, E newItem){
		Node<E> e = getNode(index);
		E oldData = e.data;
	    e.data = newItem;
	    return oldData;
	}

	public E remove(int index){
		return remove(getNode(index));
	}

	private E remove(Node<E> p){
		p.next.prev = p.prev;
		p.prev.next = p.next;
		size --;
		changeCount --;
		return p.data;
	}

	public java.util.Iterator<E> iterator(){
		return new LinkedListIterator();
	}

	private class LinkedListIterator implements java.util.Iterator<E>{
		private Node<E> curr = begin.next;
		private int expectedChangeCount = changeCount;
		//set to true if next has been performed without subsequent remove
		private boolean toRemove = false;

		public boolean hasNext(){
			return curr != end;
		}

		public E next(){
			if(changeCount != expectedChangeCount){
				throw new java.util.ConcurrentModificationException();
			}
			if(!hasNext()){
				throw new java.util.NoSuchElementException();
			}

			E nextData = curr.data;
			curr = curr.next;
			toRemove = true;
			return nextData;
		}

		public void remove(){
			if(changeCount != expectedChangeCount){
				throw new java.util.ConcurrentModificationException();
			}
			if(!toRemove){
				throw new IllegalStateException();
			}
			LinkedList.this.remove(curr.prev);
			expectedChangeCount ++;
			toRemove = false;
		}

	}



}