## Idea
* Check from the biggest, becasue we only have space for biggest
* If the length of num 1 is longer, the values are already there
* If the lenght of num 2 is longer, put the extra length of nums 2 to nums 1
1，正统解法：O(n + m) time, O(1) space
    public class Solution {
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int i=m-1,j=n-1,k=m+n-1;
        while(i>=0&&j>=0){
            nums1[k--]= nums1[i]>=nums2[j]? nums1[i--]:nums2[j--];
        }
        while(j>=0){
            nums1[k--]=nums2[j--];
        }
    }
}
//if we start to fill nums1 from the front, the original values of nums1 may be changed before we put them into a correct pos
//so we start from back(index) of nums1, even if all nums in nums2 are larger than nums1, values in nums won't get messed up
2，merge k sorted arrays
public class Solution {
    /**
     * @param arrays k sorted integer arrays
     * @return a sorted array
     */
    public List<Integer> mergekSortedArrays(int[][] arrays) {
        List<Integer> res = new ArrayList<>();
        if (arrays == null || arrays[0] == null) {
            return null;
        }
        if (arrays.length == 0 || arrays[0].length == 0) {
            return res;
        }
        int[] newArray = mergeHelper(arrays, 0, arrays.length - 1);
        for (int i = 0; i < newArray.length; i++) {
            res.add(newArray[i]);
        }
        return res;
    }
    
    private int[] mergeHelper(int[][] arrays, int start, int end) {
        if (start == end) {
            return arrays[start];
        }
        int mid = start + (end - start) / 2;
        int[] left = mergeHelper(arrays, start, mid);
        int[] right = mergeHelper(arrays, mid + 1, end);
        return mergeTwoArrays(left, right);
    }
    
    private int[] mergeTwoArrays(int[] array1, int[] array2) {
        int a1 = array1.length;
        int a2 = array2.length;
        int[] newArray = new int[a1 + a2];
        int i = 0;
        int j = 0;
        int k = 0;
        while (i < a1 && j < a2) {
            if (array1[i] < array2[j]) {
                newArray[k++] = array1[i++];
            } else {
                newArray[k++] = array2[j++];
            }
        }
        while (i < a1) {
            newArray[k++] = array1[i++];
        }
        while (j < a2) {
            newArray[k++] = array2[j++];
        }
        return newArray;
    }
}
3，iterator的解法merge k sort array
// iterator for merge k sorted arrays, heap: O(nlogk) time, O(k) space
public class iteratorForMergekSortedArrays {
    /**
     * @param arrays k sorted integer arrays
     * @return a sorted array
     */
    public class ArrayIterator {
        int val;//use val to store the next value of this array
        Iterator it;//store this array's iterator
        public ArrayIterator(Iterator i) {
            val = i.next();
            it = i;
        }
    }
    
    private Queue<ArrayIterator> heap;
    
    public iteratorForMergekSortedArrays(ArrayList<ArrayList<Integer>> arrays) {
        heap = new PriorityQueue<>(1, new Comparator<ArrayIterator>(){
            public int compare(ArrayIterator a, ArrayIterator b) {
                return a.val - b.val;
            }
        });
        for (ArrayList array : arrays) {
            if (!array.isEmpty()) {
                ArrayIterator i = new ArrayIterator(array.iterator());
                heap.offer(i);
            }
        }
    }
    
    public int next() {//assume next() will be called only if hasNext() is true
        ArrayIterator i = heap.poll();
        int res = i.val;
        if (i.it.hasNext()) {
            i.val = i.it.next();
            heap.offer(i);
        }
        return res;
    }

    public boolean hasNext() {
        return !heap.isEmpty();
    }
}
 iterator for merge k sorted arrays, heap: O(nlogk) time, O(k) space
//if we start to fill nums1 from the front, the original values of nums1 may be changed before we put them into a correct pos
//so we start from back(index) of nums1, even if all nums in nums2 are larger than nums1, values in nums won't get messed up


4,iterator for 2 sorted arrays' iterators: O(n + m) time, O(1) space
public class iteratorForTwoSortedArrays {
    /**
     * @param arrays k sorted integer arrays
     * @return a sorted array
     */
    public class ArrayIterator {
        Integer val;//use val to store the next value of this array
        Iterator it;//store this array's iterator
        public ArrayIterator(Iterator i) {
            val = i.next();
            it = i;
        }
    }
    
    private ArrayIterator it_a;
    private ArrayIterator it_b;
    
    public iteratorForTwoSortedArrays(Iterator a, Iterator b) {
        if (a.hasNext()) {
            it_a = new ArrayIterator(a);
        }
        if (b.hasNext()) {
            it_b = new ArrayIterator(b);
        }
    }
    
    public int next() {//assume next() will be called only if hasNext() is true
        if (it_a.val == null) {
            return helper(it_b);
        }
        if (it_b.val == null) {
            return helper(it_a);
        }
        //if both of them are not null
        if (it_a.val <= it_b.val) {
            return helper(it_a);
        } else {
            return helper(it_b);
        }
    }

    public boolean hasNext() {
        return it_a.val != null || it_b.val != null;
    }
    
    private int helper(ArrayIterator curr) {
        int res = curr.val;
        if (curr.it.hasNext()) {
            curr.val = it.next();
        } else {
            curr.val = null;
        }
        return res;
    }
}
