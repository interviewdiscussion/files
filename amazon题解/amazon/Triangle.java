public class Triangle{
	public int minimumTotal(List<List<Integer>> triangle) {
		int size = triangle.size();
		if(size == 1){
			return triangle.get(0).get(0);
		}
		int[] dp = new int[size];
         //initial condition by last row
		for(int i=0; i<size; i++){
			dp[i] = triangle.get(size-1).get(i);
		}
		for(int i=size-2; i>=0; i--){
			int len = triangle.get(i).size();
			for(int j=0; j<len; j++){
                //update by min(two adjacent values in current row) + value in the row above
				dp[j] = Math.min(dp[j],dp[j+1]) + triangle.get(i).get(j);
				//if want maximum sum
				//use dp[j] = Math.max(dp[j],dp[j+1]) + triangle.get(i).get(j);
			}
		}
		return dp[0];
	}
}