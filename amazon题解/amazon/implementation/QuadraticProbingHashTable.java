//Source: Data Structure and Algorithm Analysis in Java


public class QuadraticProbingHashTable{
	private static class HashEnty<E>{
		public E element;
		public boolean isActive;

		public HashEntry(E element){
			this(element, true);
		}

		public HashEntry(E element, boolean i){
			this.element = element;
			this.isActive = i;
		}
	}




	private static final int DEFAULT_SIZE = 11;
	private HashEntry<E>[] array;
	private int currentSize;

	public QuadraticProbingHashTable(){
		this(DEFAULT_SIZE);

	}

	public QuadraticProbingHashTable(int size){
		allocateArray(size);
		makeEmpty();
	}

	public void makeEmpty(){
		currentSize = 0;
		for(int i=0; i<array.length; i++){
			array[i] = null;
		}

	}

	public boolean contains(E element){
		int currentPos = findPos(element);
		return isActive(currentPos);

	}

	public void insert(E element){
		int currentPos = findPos(element);

		if(isActive(currentPos)){
			return;
		}

		array[currentPos] = new HashEntry<>(element, true);

		if(++currentSize > array.length/2){
			rehash();
		}

	}

	public void remove(E element){
		int currentPos = findPos(element);
		if(isActive(currentPos)){
			array[currentPos].isActive = false;
		}

	}

	private void allocateArray(int arraySize){
		array = new HashEntry[nextPrime(arraySize)];

	}

	private boolean isActive(int currentPos){
		return array[currentPos]!=null && array[currentPos].isActive;

	}

	private int findPos(E element){
		int offset = 1;
		int currentPos = myhash(element);

		while(array[currentPos]!=null && !array[currentPos].element.equals(element)){
			currentPos += offset;
			offset += 2;
			if(currentPos >= array.length){
				currentPos -= array.length;
			}
		}

		return currentPos;

	}

	private void rehash(){
		HashEntry<E>[] oldArray = array;

		allocateArray(nextPrime(2*oldArray.length));
		currentSize = 0;

		for(int i=0; i<oldArray.length; i++){
			if(oldArray[i]!=null && oldArray[i].isActive){
				insert(oldArray[i].element);
			}
		}

	}

	private int myhash(E element){

	}

	private static int nextPrime(int n){

	}

	private static boolean isPrime(int n){

	}


}