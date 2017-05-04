//bubble sort

public class BubbleSort{
	public static void main(String[] args){
		int[] test = new int[]{7,5,2,4,3,9};
		bubbleSort4(test);
		for(int curr: test){
			System.out.print(curr + " ");
		}

	}
    
    //for every step, move largest element in the left unsorted left subarray to front of right sorted array
	public static void bubbleSort(int[] arr){
		if(arr==null || arr.length<=1){
			return;
		}
		int len = arr.length;
		for(int i=len-1; i>=0; i--){
			//move largest element to the end of array
			for(int j=1; j<=i; j++){
				if(arr[j-1] > arr[j]){
					int temp = arr[j-1];
					arr[j-1] = arr[j];
					arr[j] = temp;
				}
			}
		}
	}
    
    //for every step, swap unordered pair until no swap is neede
	public static void bubbleSort2(int[] arr){
		if(arr==null || arr.length<=1){
			return;
		}
		int len = arr.length;
		boolean swap = true;

		while(swap){
			swap = false;
			for(int i=1; i<len; i++){
				if(arr[i-1] > arr[i]){
					int temp = arr[i];
					arr[i] = arr[i-1];
					arr[i-1] = temp;
					swap = true;
				}
			}
		}
	}

	//combine first and second implementation
	public static void bubbleSort3(int[] arr){
		if(arr==null || arr.length<=1){
			return;
		}
		int len = arr.length;
		boolean swap = true;

		while(swap){
			swap = false;
			for(int i=1; i<len; i++){
				if(arr[i-1] > arr[i]){
					int temp = arr[i];
					arr[i] = arr[i-1];
					arr[i-1] = temp;
					swap = true;
				}
			}
			len --;
		}
	}

	//in one pass, more than one element can be placed into final positions
	//position after last swap is sorted and no need to traverse again
	public static void bubbleSort4(int[] arr){
		if(arr==null || arr.length<=1){
			return;
		}
		int len = arr.length;

		while(len != 0){
			int lastSwap = 0;
			for(int i=1; i<len; i++){
				if(arr[i-1] > arr[i]){
					int temp = arr[i];
					arr[i] = arr[i-1];
					arr[i-1] = temp;
					lastSwap = i;
				}
			}
			len = lastSwap;
		}
	}


}