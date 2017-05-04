import java.util.*;
public class arrayLengthEncoding{
	public static void main(String[] args){
		int[] test = new int[]{1,1,1,0,0,1,1,0,1};
		for(int n: encodeLength(test)){
			System.out.print(n);
		}

	}

	public static ArrayList<Integer> encodeLength(int[] arr){
		ArrayList<Integer> res = new ArrayList<Integer>();
		if(arr==null || arr.length==0){
			res.add(0);
			res.add(0);
			res.add(1);
			res.add(0);
			return res;
		}
		
		int len = arr.length;
		int curr = arr[0];
		res.add(curr);
		int freq = 1;
		for(int i=1; i<len; i++){
			if(arr[i] == curr){
				freq ++;
			}else{
				curr = arr[i];
				res.add(freq);
				res.add(curr);
				freq = 1;
			}

		}
		res.add(freq);
		return res;

	}
}