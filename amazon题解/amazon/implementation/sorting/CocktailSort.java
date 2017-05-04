//Cocktail Sort

public class CocktailSort{
	public static void main(String[] args){
		int[] test = new int[]{7,5,2,4,3,9};
		cocktailSort2(test);
		for(int curr: test){
			System.out.print(curr + " ");
		}
	}

	public static void cocktailSort(int[] arr){
		if(arr==null || arr.length<=1){
			return;
		}
		int len = arr.length;
		boolean swapped = true;

		while(swapped){
			swapped = false;
			for(int i=0; i<=len-2; i++){
				if(arr[i] > arr[i+1]){
					swap(arr, i, i+1);
					swapped = true;
				}
			}

			if(!swapped){
				break;
			}
			swapped = false;
            
            for(int i=len-2; i>=0; i--){
            	if(arr[i] > arr[i+1]){
            		swap(arr, i, i+1);
            		swapped = true;
            	}
            }
		}
	}

	public static void cocktailSort2(int[] arr){
		if(arr==null || arr.length<=1){
			return;
		}
		int begin = -1;
		int end = arr.length - 2;
		boolean swapped = true;

		while(swapped){
			swapped = false;
			begin = begin + 1;
			for(int i=begin; i<=end; i++){
				if(arr[i] > arr[i+1]){
					swap(arr, i, i+1);
					swapped = true;
				}
			}

			if(!swapped){
				break;
			}
			swapped = false;
            end = end - 1;

            for(int i=end; i>=begin; i--){
            	if(arr[i] > arr[i+1]){
            		swap(arr, i, i+1);
            		swapped = true;
            	}
            }
		}
	}

	public static void swap(int[] arr, int i, int j){
		int temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
	}


}