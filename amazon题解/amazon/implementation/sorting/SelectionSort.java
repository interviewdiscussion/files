//selection sort

public class SelectionSort{
	public static void main(String[] args){
		int[] test = new int[]{29,64,73,34,20};
		selectionSort(test);
		for(int curr: test){
			System.out.print(curr + " ");
		}

	}

	public static void selectionSort(int[] arr){
		if(arr==null || arr.length<=1){
			return;
		}
		int len = arr.length;
		for(int i=0; i<len; i++){
			int minIdx = i;
			//search for index of smallest element in the remaining array
			for(int j=i+1; j<len; j++){
				if(arr[j] < arr[minIdx]){
					minIdx = j;
				}
			}
			//move smallest element to the front
			int temp = arr[i];
			arr[i] = arr[minIdx];
			arr[minIdx] = temp;
		}

	}
}