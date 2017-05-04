//Medallia
//Bucket sort: find minimum and maximum of the range, create an array of buckets that 
//             is allocated for each value between min and max; number of each value
//             is counted by one pass and store into bucket array. 

//time O(n), n is the number of elements to be sorted
//fast when range is reasonably small

public class BucketSort{
	public static void main(String[] args){
		int[] test = new int[]{2,5,8,12,7,1,9,15,3,1,8};
		sort(test);
		for(int curr: test){
			System.out.print(curr + " ");
		}

	}
    

    //number of buckets (max-min+1)
	public static void sort(int[] arr){
		if(arr==null || arr.length<=0){
			return;
		}
		int min = arr[0];
		int max = min;
		int n = arr.length;

		for(int i=1; i<n; i++){
			if(arr[i] > max){
				max = arr[i];
			}else if(arr[i] < min){
				min = arr[i];
			}
		}

		int bucket[] = new int[max-min+1];
		for(int i=0; i<n; i++){
			bucket[arr[i]-min]++;
		}

		int i = 0;
		int len = bucket.length;
		for(int j=0; j<len; j++){
			for(int k=0; k<bucket[j]; k++){
				arr[i++] = j+min;
			}
		}
	}
    

    //number of buckets is n
	public static void bucketSort(int[] arr, int numOfBucket){
		if(numOfBucket <= 0){
			throw new IllegalArgumentException("Invalid number of buckets");
		}
		if(arr == null || arr.length==0){
			return;
		}

		int high = arr[0];
		int low = arr[0];

		//find value range
		int len = arr.length;
		for(int i=0; i<len; i++){
			low = Math.min(low, arr[i]);
			high = Math.max(high, arr[i]);
		}

		//compute interval
		double interval = (double)(high - low + 1)/numOfBucket;
        
        //create buckets
		ArrayList<Integer> buckets[] = new ArrayList[numOfBucket];
		for(int i=0; i<numOfBucket; i++){
			buckets[i] = new ArrayList<Integer>();
		}

		//put elements into buckets
		for(int i=0; i<len; i++){
			buckets[(int)((arr[i]-low)/interval)].add(arr[i]);
		}

		//sort each bucket and write to the original array
		int idx = 0;
		for(int i=0; i<numOfBucket; i++){
			Collections.sort(buckets[i]);
			int size = buckets[i].size();
			for(int j=0; j<size; j++){
				arr[idx] = buckets[i].get(j);
				idx++;
			}
		}
	}
}