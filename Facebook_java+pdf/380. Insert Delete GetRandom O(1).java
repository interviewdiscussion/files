
* Time complexity requirments O(1)
* Use ArrayList to store all variables
* Use HashMap to store values and their locations
* Every insert : location + 1
* Every remove : Put the last value in the removed locatio and remove the last one 
//no dups
public class RandomizedSet {
    HashMap<Integer, Integer> map;
    ArrayList<Integer> list;
    Random rand;

    /** Initialize your data structure here. */
    public RandomizedSet() {
        map = new HashMap<>();
        list = new ArrayList<>();
        rand = new Random();
    }
    
    /** Inserts a value to the set. Returns true if the set did not already contain the specified element. */
    public boolean insert(int val) {
        if (map.containsKey(val)) {
            return false;
        }
        map.put(val, list.size());//key is the val, value is the insert position of val in array
        list.add(val);
        return true;
    }
    
    /** Removes a value from the set. Returns true if the set contained the specified element. */
    public boolean remove(int val) {
        if (!map.containsKey(val)) {
            return false;
        }
        int index = map.remove(val);//remove the mapping of val
        int lastVal = list.remove(list.size() - 1);//remove lastVal and,if index!=listsize,put lastVal to prev position of val
        if (index != list.size()) {//if index == list.size(), we shouldn't try to put it back to array!
            list.set(index, lastVal);//put the lastVal to the previous position of val
            map.put(lastVal, index);//remember to reset the index of the lastVal !!!
        }
        return true;
    }
    
    /** Get a random element from the set. */
    public int getRandom() {
        return list.get(rand.nextInt(list.size()));
    }
}

/**
 * Your RandomizedSet object will be instantiated and called as such:
 * RandomizedSet obj = new RandomizedSet();
 * boolean param_1 = obj.insert(val);
 * boolean param_2 = obj.remove(val);
 * int param_3 = obj.getRandom();
 */

//dups allowed
//Also see http://www.1point3acres.com/bbs/thread-156459-1-1.html
public class RandomizedCollection {
    HashMap<Integer, HashSet<Integer>> map;//use hashset inside,don't use arraylist,so we can add/remove an index in O(1) time
    ArrayList<Integer> list;
    Random rand;

    /** Initialize your data structure here. */
    public RandomizedCollection() {
        map = new HashMap<>();
        list = new ArrayList<>();
        rand = new Random();
    }
    
    /** Inserts a value to the collection. Returns true if the collection did not already contain the specified element. */
    public boolean insert(int val) {
        boolean contain = map.containsKey(val);
        if (!contain) {
            map.put(val, new HashSet<>());
        }
        map.get(val).add(list.size());
        list.add(val);
        return !contain;
    }
    
    /** Removes a value from the collection. Returns true if the collection contained the specified element. */
    public boolean remove(int val) {
        if (!map.containsKey(val)) {
            return false;
        }
        int index = map.get(val).iterator().next();//get an index of val from its hashset of indices
        map.get(val).remove(index);//remove that index of val from its hashset
        if (map.get(val).size() == 0) {//if the hashset is empty now, remove the mapping
            map.remove(val);
        }
        int lastVal = list.remove(list.size() - 1);//remove lastVal and,if index!=listsize,put lastVal to prev position of val
        if (index != list.size()) {//if index == list.size(), we shouldn't try to put it back to array!
            list.set(index, lastVal);//put the lastVal to the previous position of val
            map.get(lastVal).remove(list.size());//remember to reset the index of the lastVal !!!
            map.get(lastVal).add(index);//remove the previous index of lastVal, then add curr index
        }
        return true;
    }
    
    /** Get a random element from the collection. */
    public int getRandom() {
        return list.get(rand.nextInt(list.size()));
    }
    
    public int removeRandom() {
        int num = list.get(rand.nextInt(list.size()));
        remove(num);
        return num;
    }
}