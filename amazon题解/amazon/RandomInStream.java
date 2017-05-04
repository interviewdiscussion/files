//select random number from stream

import java.util.*;
public class RandomInStream{
	public static void main(String[] args){
		int[] stream = new int[]{1,2,3,4};
		for(int i=0; i<stream.length; i++){
			System.out.println(i+": " + selectRandom(stream[i]));
		}

	}


    static int res;  
    static int count = 0;  //count of numbers visited so far
    
	public static int selectRandom(int x){
		

        count ++;
        
        //if first element from stream, return it
        if(count == 1){
        	res = x;
        }else{
        	//generate random number from 0 to count-1
        	Random rand = new Random();
        	int i = rand.nextInt(count);

        	//replace the prev random number with probability 1/count
        	if(i == count-1){
        		res = x;
        	}

        }
        return res;
	}



}