
import java.util.*;
public class ThreeSum{
	public static void main(String[] args){
		int[] test = new int[]{-1,0,1,2,-1,-4};
		ArrayList<ArrayList<Integer>> res = threeSum(test);
		for(ArrayList<Integer> sol: res){
			for(int n: sol){
				System.out.print(n + " ");
			}
			System.out.println();
		}
		int[] test2 = new int[]{-1,2,1,-4};
		System.out.println(closestThreeSum(test2, 1));
	}


	public static ArrayList<ArrayList<Integer>> threeSum(int[] num){
		ArrayList<ArrayList<Integer>> res = new ArrayList<ArrayList<Integer>>();
		if(num == null || num.length==0){
			return res;
		}
        //sort the array
		Arrays.sort(num);
		int len = num.length;

		for(int small=0; small<len-2; small++){
        //skip duplicates if necessary
			if(small==0 || num[small-1]!=num[small]){
				int mid = small + 1;
				int large = len - 1;
				int target = -num[small];

				while(mid < large){
                    //find a solution
					if(num[mid] + num[large] == target){
						ArrayList<Integer> sol = new ArrayList<Integer>();
						sol.add(num[small]);
						sol.add(num[mid]);
						sol.add(num[large]);
						res.add(sol);
						mid ++;
						large --;
                        //skip duplicates
						while(mid < large && num[mid-1]==num[mid]){
							mid ++;
						}
						while(mid < large && num[large+1]==num[large]){
							large --;
						}
					}else if(num[mid] + num[large] < target){
						mid ++;
					}else{
						large --;
					}
				}
			}
		}
		return res;
	}


	public static int closestThreeSum(int[] num, int target){
		if(num == null || num.length==0){
			return 0;
		}
        //sort array
		Arrays.sort(num);
		int len = num.length;
		int res = Integer.MAX_VALUE/2;


		for(int small=0; small<len-2; small++){
			int mid = small + 1;
			int large = len - 1;
			while(mid < large){
				
				int sum = num[small] + num[mid] + num[large];
				if(sum == target){
					return target;
				}else if(sum < target){
					mid ++;
				}else{
					large --;
				}
				if(Math.abs(sum-target) < Math.abs(res-target)){
					res = sum;
				}
			}
		}
		return res;
	}
}