//if initially two inputs are not {index, val} type, save them to hashmap
public class Solution {
    public int sparseVectorMultiplication(int[] nums1, int[] nums2) {
        // nums1.length == nums.length ?
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums1.length; i++) {
            map.put(i, nums1[i]);
        }
        int res = 0;
        for (int key : map.keySet()) {
            res += map.get(key) * nums2[key];
        }
        return res;
    }
    
    public int[][] sparseMatrixMultiplication(int[][] A, int[][] B) {
        if (A.length == 0 || A[0].length == 0 || B.length == 0 || B[0].length == 0) {return new int[][]{};}
        int m = A.length, n = B.length, d = B[0].length;
        int[][] res = new int[m][d];

        Map<Integer, Map<Integer, Integer>> map = new HashMap<>();
        for (int i = 0; i < m; i++) {
            Map<Integer, Integer> temp = new HashMap<>();
            for (int j = 0; j < n; j++) {
                if (A[i][j] != 0) {
                    temp.put(j, A[i][j]);
                }
            }
            map.put(i, temp);
        }

        for (int key1 : map.keySet()) {
            for (int i = 0; i < d; i++) {
                for (int key2 : map.get(key1).keySet()) {
                    res[key1][i] += map.get(key1).get(key2) * B[key2][i];
                }
            }
        }
        return res;
    }
}

public class Solution {//assume inputs are like {{2, 4}, {0, 10}, {3, 15}},index0 is index of non-zero vals,index1 is the val
    private Comparator<ArrayList<Integer>> sparseVectorComparator = new Comparator<ArrayList<Integer>>(){
        public int compare(ArrayList<Integer> a, ArrayList<Integer> b) {
            return a.get(0) - b.get(0);
        }
    };//remember to add ";" !!!
    
    public int sparseVectorMultiplication(ArrayList<ArrayList<Integer>> a, ArrayList<ArrayList<Integer>> b) {
        if (a == null || b == null || a.size() == 0 || b.size() == 0) {
            return 0;
        }
        int m = a.size();
        int n = b.size();
        int res = 0;
        
        //two inputs are unsorted, directly iterate the elements(brute force); O(m*n) time; if use sort, O(mlogm + nlogn)
        for (int i = 0; i < m; i++) {
            ArrayList<Integer> pairA = a.get(i);
            for (int j = 0; j < n; j++) {
                ArrayList<Integer> pairB = b.get(i);
                if (pairA.get(0) == pairB.get(0)) {//if their indices are the same, calculate and break
                    res += pairA.get(1) * pairB.get(1);
                    break;//pairA has been calculated, jump to next pair
                }
            }
        }
        
        //if we need to sort the inputs
        Collections.sort(a, sparseVectorComparator);
        
        //two inputs are sorted by index0, use two pointers(move the smaller, calculate the equal); O(m+n) time
        int i = 0;
        int j = 0;
        while (i < m && j < n) {
            ArrayList<Integer> pairA = a.get(i);
            ArrayList<Integer> pairB = b.get(j);
            if (pairA.get(0) < pairB.get(0)) {
                i++;
            } else if (pairA.get(0) > pairB.get(0)) {
                j++;
            } else {
                res += pairA.get(1) * pairB.get(1);
                i++;
                j++;
            }
        }
        
        //two inputs are sorted by index0, have same size, sometimes dense, sometimes sparse; two pointes + binary search
        int i = 0;
        int j = 0;
        int countA = 0;
        int countB = 0;
        while (i < m && j < n) {
            ArrayList<Integer> pairA = a.get(i);
            ArrayList<Integer> pairB = b.get(j);
            if (pairA.get(0) < pairB.get(0)) {
                i++;
                countA++;
                countB = 0;
                if (countA > Math.log(m)) {
                    i = search(a, i, m, pairB.get(0));
                    countA = 0;
                }
            } else if (pairA.get(0) > pairB.get(0)) {
                j++;
                countB++;
                countA = 0;
                if (countB > Math.log(n)) {
                    j = search(b, j, n, pairA.get(0));
                    countB = 0;
                }
            } else {
                res += pairA.get(1) * pairB.get(1);
                i++;
                j++;
                countA = 0;
                countB = 0;
            }
        }
        
        //two inputs are sorted by index0, input b is much larger than input a, iterate a and binary search b; O(m*logn) time
        int i = 0;
        int j = 0;
        while (i < m) {
            ArrayList<Integer> pairA = a.get(i++);
            j = search(b, j, n, pairA.get(0));
            ArrayList<Integer> pairB = b.get(j++);
            if (pairA.get(0) == pairB.get(0)) {
                res += pairA.get(1) * pairB.get(1);
            }
        }
        
        return res;
    }
    
    private int search(ArrayList<ArrayList<Integer>> array, int start, int end, int target) {
        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            ArrayList<Integer> pair = array.get(mid);
            if (pair.get(0) == target) {
                return mid;
            } else if (pair.get(0) < target) {
                start = mid;
            } else {
                end = mid;
            }
        }
        if (array.get(end).get(0) == target) {
            return end;
        }
        return start;
    }
}
面试官先问每个vector很大，不能在内存中存下怎么办，我说只需存下非零元素和他们的下标就行，然后问面试官是否可用预处理后的
这两个vector非零元素的index和value作为输入，面试官同意后写完O(M*N)的代码(输入未排序，只能一个个找)，MN分别是两个vector长度。

又问这两个输入如果是根据下标排序好的怎么办，是否可以同时利用两个输入都是排序好这一个特性，最后写出了O(M + N)的双指针方法，
每次移动pair里index0较小的指针，如果相等则进行计算，再移动两个指针。

又问如果一个向量比另一个长很多怎么办，我说可以遍历长度短的那一个，然后用二分搜索的方法在另一个vector中找index相同的那个元素，
相乘加入到结果中，这样的话复杂度就是O(M*logN)。

又问如果两个数组一样长，且一会sparse一会dense怎么办。他说你可以在two pointer的扫描中内置一个切换二分搜索的机制。
看差值我说过，设计个反馈我说过，他说不好。他期待的解答是，two pointers找到下个位置需要m次比较，而直接二分搜需要log(n)次比较。
那么在你用two pointers方法移动log(n)次以后，就可以果断切换成二分搜索模式了。

Binary search如果找到了一个元素index，那就用这次的index作为下次binary search的开始。可以节约掉之前的东西，不用search了。
然后问，如果找不到呢，如何优化。说如果找不到，也返回上次search结束的index，然后下次接着search。
就是上一次找到了，就用这个index继续找这次的；如果找不到，也有一个ending index，就用那个index当starting index。
比如[1, 89，100]，去找90；如果不存在，那么binary search的ending index应该是89，所以下次就从那个index开始。
如果找不到，会返回要插入的位置index + 1，index是要插入的位置，我写的就是返回要插入的index的。
但是不管返回89还是100的index都无所谓，反正只差一个，对performance没有明显影响的。

楼主:暴力双循环，skip 0.
面试官:不急着写，你想想有什么好办法存vector？
琢磨了好久，说要不我们用hashmap存value和index
面试官继续追问，hashmap会有空的空间，我们有memory限制，你怎么办
楼主:那用arraylist存pair？
面试官：这个还差不多，那你打算怎么求解？
楼主：排序，two pointer？
面试官：好，你写吧。写完后追问了时间复杂度

public class Solution {
    public int[][] multiply(int[][] a, int[][] b) {
        if (a == null || b == null) {
            return new int[0][0];
        }//for a i*k matrix multiply by a k*j matrix, we will get a i*j matrix
        int[][] res = new int[a.length][b[0].length];//res[i][j] = a[i][0]*b[0][j] + a[i][1]*b[1][j] +...+ a[i][k]*b[k][j];
        for (int i = 0; i < a.length; i++) {
            for (int k = 0; k < a[0].length; k++) {
                if (a[i][k] != 0) {//cuz it's a sparse matrix, we can only calculate nonzero product to reduce operations
                    for (int j = 0; j < b[0].length; j++) {
                        if (b[k][j] != 0) {//we only add up all products that a[i][k] != 0 && b[k][j] != 0 to reduct time
                            res[i][j] += a[i][k] * b[k][j];// +=, not =; *, not + !!!
                        }
                    }
                }
            }
        }
        return res;
    }
}

// Let's look at brute force solution:
public int[][] multiply_bruteForce(int[][] A, int[][] B) {
	int m = A.length, n = A[0].length;
	int nB = B[0].length;
	int [][] C = new int[m][nB];
	for (int i = 0; i<m; i++) {
		for (int j = 0; j<nB; j++){
			C[i][j] = 0;
			for( int k = 0; k<n; k++)
				C[i][j] += A[i][k]*B[k][j];
		}
	}
	return C;
}
For brute force solution, for each C[ i ] [ j ], it uses C[ i ] [ j ] += A[ i ] [ k ] * B[ k ] [ j ] where k = [ 0, n].
Note: even A[ i ] [ k ] or B[ k ] [ j ] is 0, the multiplication is still executed.

For the above smart solution, if A[ i ] [ k ] == 0 or B[ k ] [ j ] == 0, it just skip the multiplication. 
This is achieved by moving for-loop" for ( k = 0; k < n; k++ ) " from inner-most loop to middle loop, 
so that we can use if-statement to tell whether A[ i ] [ k ] == 0 or B[ k ] [ j ] == 0. This is really smart.