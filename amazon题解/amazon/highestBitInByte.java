//consider endian


public class highestBitInByte{
	public static void main(String[] args){
		int[] test = new int[]{0x81, 0xf4, 0x42, 0x03};
		for(int n: test){
			System.out.println(highestBit(n));
		}

	}

	public static int highestBit(int num){
		for(int i=1; i<=7; i++){
			num = num >>> 1;
		}
		return num & 1;
	}


}