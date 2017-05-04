public class searchIncreasingDecreasingArray{
	public static void main(String[] args){
		int[] arr = new int[] {1,3,5,7,19,221,132,56,8,6,4,2,1,-3,-17}; 
		System.out.println(search(arr, -17));

	}


	public static int search(int[] arr, int target){
		int peak = findPeak(arr, 0, arr.length);

		System.out.println("Peak element index: " + peak);

		int res = binarySearch(arr, 0, peak, target, true);
		System.out.println("Search increasing array: " + res);

		if(res != -1){
			return res;
		}else{
			return binarySearch(arr, peak, arr.length-1, target, false);
		}

	}

    //find peak element using binary search
	public static int findPeak(int[] arr, int low, int high){
		if(arr.length<=2)
			return -1;
			
		int mid=(low+high)/2;
		
		if(low==mid|| mid+1==high )
			return -1;
		
		if(arr[mid]>arr[mid-1] && arr[mid]>arr[mid+1])
			return mid;
		
		int n1= findPeak(arr, low, mid);
		int n2= findPeak(arr, mid, high);
		
		if(n1 != -1)
			return n1;
		if(n2 != -1)
			return n2;
					
		return -1;
	}

	public static int binarySearch(int[] arr, int low, int high, int target, boolean isIncreasing){
		if(low <= high){
			int mid = low + (high - low)/2;
			if(arr[mid] == target){
				return mid;
			}else if(arr[mid] < target){
				if(isIncreasing){
					return binarySearch(arr, mid+1, high, target, true);
				}else{
					return binarySearch(arr, low, mid-1, target, false);
				}
			}else{
				if(isIncreasing){
					return binarySearch(arr, low, mid-1, target, true);
				}else{
					return binarySearch(arr, mid+1, high, target, false);
				}
			}
		}
		return -1;
	}
}