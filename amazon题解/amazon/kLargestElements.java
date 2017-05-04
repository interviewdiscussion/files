//http://www.geeksforgeeks.org/k-largestor-smallest-elements-in-an-array/

//use bubble sort O(nk)
//use sort O(nlogn)
//use max heap(build O(n) and extra max k times O(klogn)) time O(n+klogn)
//quick select


import java.util.*;
public class kLargestElements{
	public static void main(String[] args){
		int[] test = new int[]{7,5,2,4,3,9};
		int res = findKLargest3(test,3);
		System.out.println(res);
	}

    //use outer loop of bubble sort for k times
    //time O(nk)
	public static int findKLargest(int[] array, int k){
		if(array == null){
			return Integer.MAX_VALUE;
		}
		//can throw exception when length is small
		
		int len = array.length;
		for(int i=len-1; i>=len-k; i--){
			for(int j=1; j<=i; j++){
				if(array[j-1] > array[j]){
					int temp = array[j-1];
					array[j-1] = array[j];
					array[j] = temp;
				}
			}
		}
		int[] res = new int[k];
		int idx = 0;
		for(int i=len-1; i>=len-k; i--){
			res[idx] = array[i];
			idx ++;
		}
		return res[res.length-1];
	}
    
    //use priority queue
    //time O(n+klogn)
	public static int findKLargest2(int[] array, int k){
		if(array == null){
			return Integer.MAX_VALUE;
		}
		int len = array.length;
		PriorityQueue<Integer> pq = new PriorityQueue<Integer>(len, new Comparator<Integer>(){
			public int compare(Integer n1, Integer n2){
				return n2 - n1;
			}
		});
		for(int num: array){
			pq.offer(num);
		}
		int[] res = new int[k];
		for(int i=0; i<k; i++){
			res[i] = pq.poll();
		}
		return res[k-1];

	}

	//quicksort
	//worst O(n^2) averageO(n)
	public static int findKLargest3(int[] array, int k){
		return helper(array, 0, array.length-1, array.length-k+1);
	}

	public static int helper(int[] arr, int left, int right, int k){
		if(k>0 && k<=right-left+1){
			int pos = partition(arr, left, right);

			if(pos-left == k-1){
				return arr[pos];
			}
			if(pos-left > k-1){
				return helper(arr, left, pos-1, k);
			}
			return helper(arr, pos+1, right, k-pos+left-1);
			
		}
		return Integer.MAX_VALUE;
	}

	public static int partition(int[] array, int left, int right){
		int x = array[right];
		int i = left;
		for(int j=left; j<=right-1; j++){
			if(array[j] <= x){
				swap(array,i, j);
				i++;
			}
		}
		swap(array,i,right);
		return i;
	}

	public static void swap(int[] array, int i, int j){
		int temp = array[i];
		array[i] = array[j];
		array[j] = temp;
	}

	public static int randomPartition(int[] arr, int left, int right){
		int n = right-left+1;
		int pivot = rand()%n;
		swap(arr, left+pivot, right);
		return partition(arr,left,right);
	}
   



}
