# List

## Iterator

- if structural change is made(add, remove, clear), iterator is no longer valid
- if iterator invokes its remove method, then iterator is still valid
- main advantage of remove method from iterator is that Collection's remove method must first find the item to remove

## ArrayList

- advantage
  + calls to get and set take constant time
- disadvantage
  + insertion and removal might be expensive unless changes are made at the end of arraylist
  + inefficient for search
  + notation of capacity(size of underlying array)
- operation
  + get, set: O(1)
  + insertion(add to front) and removal: O(n)


## LinkedList
- advantage
  + insertion and removal is cheap(constant time), provided the position of the changes is known
- disadvantage
  + not easily indexable
  + calls to get and set might be expensive
  + inefficient for search
- operation
  + get, set: O(n)
  + insertion, removal: O(1)

## Stack
- operation
  + push: O(1)
  + pop: O(1)
- implementation
  + list: push is to add node to the front of list and pop is to remove from front of list
  + array: topOfStack is initialized to -1, when push, arr[topOfStack++]=element; when pop, return arr[--topOfStack]; use topOfStack==-1 to check if emtpy
- applications
  + balance symbols
  + postfix expression
  + infix to postfix conversion
  + method calls

## Queue
- idea
  + insertion is done at one end
  + deletion is doen at the other end
- operation
  + enqueue: O(1)
  + dequeue: O(1)


## Nested Class vs. Inner Class
- nested class
  + when make something a nested class, it is placed inside of another class which is the outer class
  + must use `static` to signify it is nested; without `static`, it would be inner class
  + nested class can be made private so that it is inaccessible from classes outside outer class
- inner class
  + when declare inner class, compiler adds implicit reference to outer class that caused the inner class's construction
  + The inner class is useful in a situation in which each inner class object is associated with exactly one instance of an outer class object. In such a case, the inner class object can never exist without having an outer class object with which to be associated
  

# Tree

## General trees
- representation
  + first child + next sibling for tree node
  
  ``` 
      class TreeNode{
         Object element;
         TreeNode firstChild;
         TreeNode nextSibling;
  ```
- traversal
  + preorder traversal
    - root->left->right
    - application in file system(list all files in directory)
    
     ```
     public void listAll(){
        listAll(0);
     }
     
     public void listAll(int depth){
        printFileName(depth);
        if(isDirectory()){
           for each file in the directory
               listAll(depth+1)
        }
     }
	 ```
  + postorder traversal
    - left->right->root
    - compute size of directory
    
    ```
    public int dirSize(){
        int totalSize = sizeOfFile();
        if(isDirectory()){
          for each file f in directory
              totalSize += c.dirSize()
        }
        return totalSize;
    }
   ```
   + levelorder traversal
   + inorder traversal
     - left->root->right

- application
  + file system


## Binary Tree

- overview
  + average depth is O(sqrt(N)), worst case depth is N-1
  + for binary search tree, average depth is O(logN)
- representation
   
  ``` 
  class BinaryNode{
     Object element;
     BinaryNode left;
     BinaryNode right;
  }
  ```
- application
  + expression tree
    - push operands to stack while reading
    - if see an operator, pop two trees(operands) from stack, build a new tree and push onto the stack

## Binary Search Tree

- overview
  + values in left subtree less than root value
  + values in right subtree larger than root value

- operations
  + time O(depth), for average depth, time O(logn)

- balance and self-adjusting


## AVL Tree

- overview
  + almost identical to binary search tree
  + for every node, height of left and right subtree differ at most 1

- rotation

## Splay Tree

## B-Tree


# Set and Map in Standard Libary

## Set

- overview
  + do not allow duplicate

- types
  + TreeSet(Sorted Set): worst time O(logn)

## Map

- overview
  + a collection of entries of keys and values
  + keys must be unique
  + several keys can be mapped to the same value
  + map does not provide iterator

- types
  + TreeMap(Sorted Map): keep keys in sorted order


# Hashing

- overview
  + perform insertion, deletion, searching in average O(1) time
  + print in sorted order in O(n) not supported
  + load factor: ratio of number of elements in the hash table to the table size

## hash function
  + determinism: given an input, always generate the same value
  + uniformity: map inputs as evenly as possible over its output range
  + defined range: desirable that outputs of hash function have fixed size
  + continuity: a hash function that is used to search for similar data must be as continuous as possible, two inputs differing a little should be mapped to nearly equal hash values
  + non-invertible: impossible to reconstruct input from hash value

## collisions
  + given inputs map to the same hash value
  + separate chaining
    - idea: keep a list elements that hash to the same value
    - operations
      + search: first determine which list to traverse and then search appropriate list
      + insertion: first check appropriate list to see whether element is already in place(if duplicates allowed, keep an extra field); if element is new, insert into the front of list because recently inserted elements are more likely to be used in the near future
    - disadvantage
      + using linkedlist
      + slow down time to allocate new cells
      + require implementation of another data structure
  + probing
    - hash function: h_i(x) = (hash(x) + f(i)) % TABLE_SIZE
    - types
      + linear probing
        - f(i) = i
        - primary clustering: as long as table is large enough, a free cell can always be found but might take a long time; when table is relatively empty, blocks of occupied cells start forming
        - insertions and unsuccessful search require the same number of probes
        - on average, successful search takes less time than unsuccessful search
      + quadratic probing
        - f(i) = i^2
        - eliminate the primary clustering
        - no guarantee finding free cell when table gets more than half full or even before if table size is not prime because at most half of the table can be used as alternative locations to resolve collisions
        - table size must be prime, otherwise insertion might fail
        - secondary hashing
          + solution by double hashing: f(i)=i*hash2(x)

## rehashing
  + when table gets too full, operations start taking too long
  + solution: build another table with twice size, compute new hash value for new table
  + running time: O(n)
  + options
    - rehash as soon as table is half full
    - rehash only when an insertion fails
    - rehash when table reaches a certain load factor

## hash tables with worst O(1) time



# Priority Queue(Heap)

- structure property
  + a heap is a binary tree that is completely filled(exception at the bottom level), the average height of complete binary tree is O(logN)
  + for any element in array position i
    - left child: 2i
    - right child: 2i+1
    - parent: floor(i/2)
  + heap consists of Comparable objects
- heap order property
  + smallest(largest) element at the root
  + min-heap: for every node X, key in the parent of X is <= key in X
  + order property ensures findMin() in O(1) time
- operations
  + insert()
    - create a hole in next available position, if order property not violated, done; otherwise, heapify up
    - time O(logN) if inserted element is new minimum(maximum)
  + deleteMin()
    - find minimum is easy
    - removing minimum will create a hole in the root, we must move last element X in the heap to correct position
    - put X in correct spot along a path from the root containing minimum children(heapify down)
    - time: worst O(logN), average O(logN)
  + decreaseKey()
    - lowers the value of item at position p by positive amount
    - if violate order property, heapify up
    - application: make something with higher priority
  + increaseKey()
    - increase the value of item at position p by positive amount
    - if violate order property, heapify down
    - application: scheduler drops the priority of a process that is consuming excessive CPU time
  + delete()
    - remove node at position p
    - first perform decreaseKey(p,infinity) then perform deleteMin()
    - application: process terminated by user
  + buildHeap()
    - done with N successive inserts
    - insert takes O(1) average, O(logN) worst
    - build takes O(N) average, O(NlogN) worst
- applications
  + selection problem
    - approach one(k smallest elements)
      + read N elements into array and buildHeap
      + perform k deleteMin()
      + time: buildHeap O(N), deleteMin O(logN), total is O(N+klogN)
    - approach two(k largest elements)
      + idea: at any time, maintain a set of k largest elements
      + read first k elements
      + when a new element is read, compare it with kth largest element Sk(Sk is the smallest element in set S)
        - if new element is larger, it replaces Sk
        - otherwise, do nothing
      + finally, return the smallest element in S
      + time: build with first k elements O(k), time to process remaining (N-k)O(logk), total is O(k+(N-k)logk) = O(Nlogk)
  + event simulation


