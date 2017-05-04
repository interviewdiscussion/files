//Amazon

public class isPrime{
	public static void main(String[] args){

	}
    
    //check up to the sqrt root
	public static boolean isPrime1(int num){
		if(num <= 2){
			return false;
		}
		int sqrt = (int)Math.sqrt(num);
		for(int i=2; i<=sqrt; i++){
			if(num % i == 0){
				return false;
			}
		}
		return true;
	}

    //rule out even number first, then check up to the square root
	public static boolean isPrime2(int num) {
            if (n <= 1) {
                return false;
            }
            if (n == 2) {
                return true;
            }
            if (n % 2 == 0) {
                return false;
            }
            for (int i = 3; i <= Math.sqrt(num) + 1; i = i + 2) {
                if (n % i == 0) {
                    return false;
                }
            }
            return true;
        }
}