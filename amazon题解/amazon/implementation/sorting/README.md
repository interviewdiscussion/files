# Sorting Algorithms

Contents: 

- [Bubble Sort](#Bubble Sort)
- [Insertion Sort](#Insertion Sort)
- [Selection Sort](#Selection Sort)

- [Merge Sort](#Merge Sort)
- [Heap Sort](#Heap Sort)
- [Quick Sort](#Quick Sort)

- [Counting Sort](#Counting Sort)
- [Bucket Sort](#Bucket Sort)



<a name="Bubble Sort"/>

### Bubble Sort
+ Idea 
  - compare each item wth the item next to it, and swap positions if needed
  - algorithm repeats until we have a pass without swapping any elements

+ Implementation
  - for every step, move largest element in left unsorted array to the end; in this caes, avoid inner loop to iterate through right sorted subarray
  - for every step, swap unordered adjacent pair until no swap is needed 
  - note that in one pass, more than one element can be placed into their final positions, so the position after last swap is sorted and do not need to traverse again

+ Application
  - to find k largest/smallest, use outer loop of bubble sort for k times

+ Analysis
  - Time O(n^2)
  - Space O(1)
  - ability to detect that list is sorted is build into the algorithm
  - large elements at the beginning get swapped quickly to the end, but small elements at the end get swapped very slowly to the beginning

+ Variants
  - Odd Even Sort: compare all (odd,even) indexed pairs of adjacent elements, if a pair is in the wrong order, switch elements; then alternate between (odd,even) and (even,odd) pairs
  - Cocktail Sort: solve the rabbit turtle problem

<a name="Insertion Sort"/>

### Insertion Sort
+ The idea is to remove one entry at a time and insert it into the correct position in the sorted part.

+ Analysis 
  - Time O(n^2)
  - Space O(1)

<a name="Selection Sort"/>

### Selection Sort
+ The idea is to select the smallest element of remaining array and then swap it to the front.

+ Analysis 
  - Time O(n^2)
  - Space O(1)




<a name="Merge Sort"/>

### Merge Sort
+ Use divide and conquer paradigm
+ Procedure
  - divide array into two subarrays
  - sort each subarray
  - merge sorted subarrays into one
+ Analysis
  - Time O(nlogn)
  - Space O(nlogn)


<a name="Bucket Sort"/>

### Bucket Sort
+ Idea
  - partition array into buckets
  - sort each bucket, or recursively call bucket sort
  - merge sorted buckets

+ Drawbacks
  - how to handle duplicates: store duplicates into linkedlist or counting sort
  - must know min and max value in the original array
  - enough memory

+ Optimization
  - first partition into unsorted buckets, put unsorted elements in the buckets into the original array, then run insertion sort over the entire array
    + insertion sort's performance is based on how far element is from its final position in sorted order
    + cache friendly because of contiguous use of memory

+ Variants
  - Generic bucket sort
    + find max, divide into n buckets with size M/n
    + use insertion sort in each bucket
    + expected linear time
    + bad performance when many elements fall into a single bucket(clustering)
  - Histogram sort(counting sort)
    + first count the number of elements that will fall into each bucket
    + use the information to arrange buckets in place
    + no space overhead for bucket storage

+ Analysis
  - Time O(n)
  - Space O(n)
  - when bucket size is 1, equivalent to counting sort
  - when use two buckets, equivalent to quick sort, but random choice of pivot in quicksort makes it more resistant to clustering problem





