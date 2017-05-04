public class NumToString{
	public static void main(String[] args){

	}

	public static String numToString(int num){
		StringBuilder sb = new StringBuilder();
		int len = 1;
		//count number of digits
		while(Math.pow((double)10, (double)len) < num){
			len ++;
		}

		String[] word1 = new String[]{"", "One ", "Two ", "Three ","Four ", "Five ", "Six ", "Seven ", "Eight ", "Nine "};
		String[] word11 = new String[]{"", "Eleven ", "Twelve ", "Thirteen ", "Fourteen ", "Fifteen ", "Sixteen ", "Seventeen ", "Eighteen ", "Nineteen "};
		String[] word10 = new String[]{"", "Ten ","Twenty ","Thirty ", "Fourty ", "Fifty ","Sixty ","Senventy ", "Eighty ","Ninety "};
		String[] word100 = new String[]{"", "Hundred ","Thousand "};

		int temp;
		if(num == 0){
			sb.append("Zero");
		}else{
			if(len>3 && len%2==0){
				len ++;
			}
			do{
				if(len>3){
					temp = (num / (int)Math.pow((double)10, (double)len-2));
					//if temp is 2 digit number and not a multiple of 10
					if(temp/10 == 1 && temp%10 != 0){
						sb.append(word11[temp]);
					}else{
						sb.append(word10[temp/10]);
						sb.append(word1[temp%10]);
					}
					if(temp > 0){
						sb.append(word100[len/2]);
					}
					num = num % (int)Math.pow((double)10, (double)len-2);
					len = len - 2;
				//number less than 1000
				}else{
					temp = num/100;
					if(temp != 0){
						sb.append(word1[temp]);
						sb.append(word100[len/2]);
					}
					temp = num % 100;
					if(temp/10==1 && temp%10 != 0){
						sb.append(word11[temp%10]);
					}else{
						sb.append(word10[temp/10]);
						sb.append(word1[temp%10]);
					}
					len = 0;

				}
			}while(len>0);
		}
		return sb.toString();

	}
}