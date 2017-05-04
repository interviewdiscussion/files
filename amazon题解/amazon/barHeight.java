public class barHeight{
	public static void main(String[] args){
		int[] elevation = new int[]{0,1,0,2,1,0,1,3,2,1,3,1};
		System.out.println(highestWaterLevel(elevation));

	}

	public static int highestWaterLevel(int[] elevation){
		if(elevation==null || elevation.length==0){
			return 0;
		}
		int len = elevation.length;
		int[] left = new int[len];
		int[] right = new int[len];
        

        //scan from left
		int max = 0;
		for(int i=0; i<len-1; i++){
			if(elevation[i] > max){
				max = elevation[i];
			}
			left[i] = max;
		}

		//scan from right
		max = 0;
		for(int i=len-1; i>=0; i--){
			if(elevation[i] > max){
				max = elevation[i];
			}
			right[i] = max;
		}

		int waterLevel = 0;
        
        //water volume at each position
		for(int i=0; i<len-1; i++){
			int volume = Math.min(left[i], right[i]) - elevation[i];
			if(volume > 0){
				waterLevel = Math.max(Math.min(left[i], right[i]), waterLevel);
			}
		}
		return waterLevel;

	}
}