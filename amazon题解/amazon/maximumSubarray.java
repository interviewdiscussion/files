/**
* Problem: Find subarray which has the largest sum
*
* Solution 1: dynamic programming--track max_so_far and upate max_end_here
* Solution 2: divide and conquer--max(max left subarray, max right subarray, max subarray crossing midpoint)
*/
public class maximumSubarray{
	public int maxSubArray(int[] A){
		if(A==null || A.length==0){
			return 0;
		}
		int len = A.length;
		int max_ending_here = A[0];
		int max_so_far = A[0];
		for(int i=1; i<len; i++){
			max_ending_here = Math.max(A[i], max_ending_here+A[i]);
			max_so_far = Math.max(max_ending_here, max_so_far);
		}
		return max_so_far;
	}


	public int maxSubArray2(int[] A){
		if(A==null || A.length==0){
			return 0;
		}
		return maxSubArrayHelper(A, 0, A.length-1);
	}
	public int maxSubArrayHelper(int[] A, int low, int high){
         //one element
		if(low == high){
			return A[low];
		}
		int mid = (low+high)/2;
		return Math.max(Math.max(maxSubArrayHelper(A,low,mid), maxSubArrayHelper(A,mid+1,high)), crossMid(A,low,mid,high));
	}
	public int crossMid(int[] A, int low, int mid, int high){
		int sum = 0;
		int left = Integer.MIN_VALUE;
        //compute left sum
        //include middle element in the left
		for(int i=mid; i>=low; i--){
			sum = sum + A[i];
			if(sum > left){
				left = sum;
			}
		}
		sum = 0;
		int right = Integer.MIN_VALUE;
        //compute right sum
		for(int i=mid+1; i<=high; i++){
			sum = sum + A[i];
			if(sum > right){
				right = sum;
			}
		}
		return left+right;
	}
