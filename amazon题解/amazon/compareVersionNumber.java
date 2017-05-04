public class compareVersionNumber{
	public int compareVersion(String version1, String version2){
        //split on dots
		String[] v1 = version1.split("\\.");
		String[] v2 = version2.split("\\.");
		int len = Math.min(v1.length, v2.length);
		int v1curr,v2curr;
        //compare the part with same length
		for(int i=0; i<len; i++){
			v1curr = Integer.parseInt(v1[i]);
			v2curr = Integer.parseInt(v2[i]);
			if(v1curr > v2curr){
				return 1;
			}else if(v1curr < v2curr){
				return -1;
			}
		}
        //continue to compare if different length
		if(len<v1.length && !sumZero(v1,len)){
			return 1;
		}else if(len<v2.length && !sumZero(v2,len)){
			return -1;
		}else{
			return 0;
		}
	}
    //check if version numbers sum to zero
	public boolean sumZero(String[] arr, int start){
		int sum = 0;
		for(int i=start; i<arr.length; i++){
			sum += Integer.parseInt(arr[i]);
		}
		return sum==0;
	}
