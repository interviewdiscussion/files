//detect duplicate in array

//0 < arr[i] <= length for all and 0 <= i < length

//brute-force
//sort and check
//hashtable
//bit manipulation


public class detectDuplicateInArray{
	public static void main(String[] args){
		int[] test = new int[]{0,3,2,2};
		System.out.println(hasDuplicate(test));

	}

	public static boolean hasDuplicate(int[] arr){
		int check = 0;
		int len = arr.length;
		for(int i=0; i<len; i++){
			check ^= arr[i] ^ i; 
		}
		return check!=0;
	}
}