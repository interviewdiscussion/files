
/**
 * This class implements a hash table to store process
 * and avoid duplicate
 * 
 * NOTE: a duplicate is considered as a process with
 * the same id as another process already in the hash table
 * 
 * @author jinglu
 *
 */
public class HashTable {
    public Bucket[] buckets;
    public int entryNum;
    public static final int DEFAULT_SIZE = 2029;
    
    /**
     * default constructor with size 2029
     */
    public HashTable(){
    	buckets = new Bucket[DEFAULT_SIZE];
    	entryNum = 0;
    }
	
    /**
     * constructor for hash table with a given size
     * @param size
     */
    public HashTable(int size){
    	buckets = new Bucket[size];
    	entryNum = 0;
    }
    
    /**
     * hash function using double hashing
     * for the collision resolution function, we choose
     * hash_2(k) = R - (k mod R) 
     * where R is a prime number that is smaller than the size of the table
     * since we know the size of hash table will grow larger than the default size
     * as we keep inserting, we can choose R to be 2027 
     * 
     * We choose this collision resolution function because it never evaluates to zero and 
     * all cells in the table can be probed.
     * This function is also easy and quick to compute and achieves even distribution
     * Also, the lecture slide says it is a popular hash function for double addressing
     * @param b
     * @param j
     * @return value of hash function
     */
    public int hashFunction(Bucket b, int j){
    	int i = b.getKey();
    	int m = buckets.length;
    	int hash_1 = i % m;
    	int hash_2 = 2027 - (i % 2027);
    	return (hash_1 + j*hash_2) % m;
    }
    
    /**
     * insert a process as a bucket
     * @param b
     * @return index of the inserted item
     *         -1 if it is a duplicate
     */
    public int insert(Bucket b){
    	//if table not large enough, do rehasing
    	if(1.0*entryNum/buckets.length >= 0.5){
    		rehash();
    	}
    
    	int i = 0;
    	do{
    		int j = hashFunction(b,i);
    		//find duplicate
    		if(buckets[j] != null){
    			if(b.getKey() == buckets[j].getKey()){
    				return -1;
    			}
    		}
    		
    		//find right position to insert
    		if(buckets[j]==null || buckets[j].getLive()==false){
    			buckets[j] = b;
    			entryNum ++;
    			return j;
    		}
    		//keep probing
    		else{
    			i ++;
    		}
    	}while(i!=buckets.length-1);
    	return -1;
    }
    
    /**
     * remove process from hash table
     * @param b
     */
    public void remove(Bucket b){
    	//find the index of the process to be removed
    	int index = findIndex(b);
    	//set the bucket to dead
    	if(isLive(index)){
    		buckets[index].setLive(false);
    		entryNum --;
    	}
    }
    
    
    /**
     * search for a process in the hash table
     * @param b
     * @return index of process to be searched and -1 otherwise
     */
    public int search(Bucket b){
    	int i = 0;
    	int j = hashFunction(b,i);
    	while(buckets[j]!=null || i!=buckets.length-1){
    		if(b.equals(buckets[j])){
    			return j;
    		}else{
    			i ++;
    			j = hashFunction(b,i);
    		}	
    	}
    	return -1;
    }
    
   
    /**
     * find the index of a given bucket
     * @param b
     * @return index of the given bucket
     */
    public int findIndex(Bucket b){
    	int i = 0;
  
    	int curr = hashFunction(b,i);
    	while(buckets[curr]!=null & !b.equals(buckets[curr])){
    		i ++;
    		curr = hashFunction(b,i);
    	}
    	return curr;
    }
    

    
    /**
     * check if the bucket is live
     * @param i
     * @return true if the bucket is live and false otherwise
     */
    public boolean isLive(int i){
    	if(buckets[i]!=null && buckets[i].getLive()){
    		return true;
    	}else{
    		return false;
    	}
    }
    
    /**
     * make hash table empty
     */
    public void makeEmpty(){
    	entryNum = 0;
    	for(int i=0; i<buckets.length; i++){
    		buckets[i] = null;
    	}
    }
    
    /**
     * rehashing the table
     */
    public void rehash(){
    	Bucket[] oldBuckets = buckets;
    	//enlarge the table
    	//the new size is the next prime larger than twice the old size
    	buckets = new Bucket[2*oldBuckets.length];
    	entryNum = 0;
    	//insert old buckets into the new hash table
    	for(int i=0; i<oldBuckets.length; i++){
    		if(oldBuckets[i]!=null && oldBuckets[i].getLive()){
    			insert(oldBuckets[i]);
    		}
    	}
    }
    
    
    /**
     * get the size of hash table
     * @return size of hash table
     */
    public int getSize(){
    	return entryNum;
    }
   
    

}
