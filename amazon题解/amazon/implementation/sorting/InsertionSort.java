/** Insertion Sort
 *  Efficient for sorting small number of elements
 *  Time O(n^2), sort in place
 */  
public class InsertionSort{
	public static void main(String[] args){
		int[] arr1 = new int[]{5,2,4,6,1,3};
		insertionSort(arr1);
		for(int curr: arr1){
			System.out.print(curr + " ");
		}

	}

	public static void insertionSort(int[] arr){
		int len = arr.length;
		int key, i;
		for(int j=1; j<len; j++){
			key = arr[j];
			i = j - 1;
			//insert into sorted left subarray
			while(i>=0 && arr[i] > key){
				arr[i+1] = arr[i];
				i--;
			}
			arr[i+1] = key;
		}

	}
}