public class LRUCache {
    class Node {
        int key;
        int value;
        Node prev;
        Node next;
        
        public Node(int key, int value) {
            this.key = key;//this.key = key, not key = this.key !!!!
            this.value = value;//this.value = value, not value = this.value !!!!
            prev = null;
            next = null;
        }
    }
    
    private int capacity;
    private HashMap<Integer, Node> map;//Integer,Node, not Integer,Integer !!!
    private Node head = new Node(-1, -1);
    private Node tail = new Node(-1, -1);
    
    public LRUCache(int capacity) {
        this.capacity = capacity;
        map = new HashMap<>();
        head.next = tail;
        tail.prev = head;
    }
    
    public int get(int key) {
        if (!map.containsKey(key)) {
            return -1;
        }
        Node curr = map.get(key);
        curr.next.prev = curr.prev;
        curr.prev.next = curr.next;//cut off its current references
        moveToTail(curr);//update the LRU, insert it at the tail
        return curr.value;//return the node's value, not the node itself
    }
    
    public void set(int key, int value) {
        if (get(key) != -1) {//if the key is already in the LRU (you can use map.containsKey(key) here too)
            map.get(key).value = value;//directyly modify the value
            return;//remember to return !
        }
        if (map.size() == capacity) {//if the key is not in the LRU, first check map.size() reach the LRU's capacity
            map.remove(head.next.key);//remove head.next.key,not head.next!!! remove least recently used element at the head
            head.next = head.next.next;//cut off its current references
            head.next.prev = head;
        }
        Node insert = new Node(key, value);
        map.put(key, insert);//note that map's key is key, map's value is Node
        moveToTail(insert);//update the LRU, insert it at the tail
    }
    
    private void moveToTail(Node curr) {
        curr.prev = tail.prev;
        tail.prev = curr;
        curr.prev.next = curr;
        curr.next = tail;
    }
}