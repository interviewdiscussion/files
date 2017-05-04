public class minMaxInArray{
	public static int minMax(int[] A){
		int max = Math.MIN_VALUE;
        int min = Math.MAX_VALUE;

	   for (int i = 0; i < A.length; i++) {
	        max = Math.max(A[i],max);
	        min =Math.in(A[i],min);
	 	}
	 	return max, min;
	 }

	 public static int minMax(int[] A){
	 	int max = A[0];
	 	int min = A[0];

	 	for (int i = 1; i < A.length; ) {
	 		if (A[i-1]<A[i]) {. from: 1point3acres.com/bbs 
	 			max = Math.max(A[i],max);
	 			min =Math.in(A[i-1],min);
	 		} else {
	 			max = Math.max(A[i-1],max);. 
	 			min =Math.in(A[i],min);
	 		}
	 		i = i+2;
	 	}
	 	if(A.length % 2 !=0) {
	 		max = Math.max(A[A.length-1],max);
	 		min =Math.in(A[A.length-1],min);
	 	}
	 	return max,min
	 }
}