//follow-up: find all pairs

//follow-up: duplicate allowed

import java.util.*;
public class twoSum{
	public int[] twoSum(int[] numbers, int target){
		int[] index = new int[2];
		if(numbers==null || numbers.length<2){
			return index;
		}
		int len = numbers.length;
		int first = 0;
		int second = 1;
		while(first < len){
			while(second<len && numbers[first]+numbers[second]!=target){
				second++;
			}
			if(second == len){
				first ++;
				second = first+1;
			}else{
				index[0] = first+1;
				index[1] = second+1;
				return index;
			}
		}
		return index;
	}

	public int[] twoSum2(int[] numbers, int target){
		int[] index = new int[2];
		if(numbers==null || numbers.length<2){
			return index;
		}
		int len = numbers.length;
		HashMap<Integer,Integer> map = new HashMap<Integer,Integer>();
		for(int i=0; i<len; i++){
			int val = target - numbers[i];
			if(!map.containsKey(val)){
				map.put(numbers[i],i+1);
			}else{
				index[0] = map.get(val);
				index[1] = i+1;
				return index;
			}
		}
		return index;
	}
}