public class validPalindrome{
	public boolean isPalindrome(String s){
		if(s == null){
			return true;
		}
		StringBuffer buf = new StringBuffer();
		int len = s.length();
       //build a new string
		for(int i=0; i<len; i++){
			if((s.charAt(i)>='0' && s.charAt(i)<='9') || (s.charAt(i)>='A' && s.charAt(i)<='Z') ||(s.charAt(i)>='a' && s.charAt(i)<='z')){
				buf.append(Character.toLowerCase(s.charAt(i)));
			}
		}
		String str = buf.toString();
		System.out.println(str);
		len = str.length();
		if(len == 0){
			return true;
		}
		int i = 0;
		int j = len-1;
        //determine if it's palindrome
		while(i < len/2){
			if(str.charAt(i) != str.charAt(j)){
				return false;
			}
			i++;
			j--;
		}
		return true;
	}

	
	public boolean isPalindrome2(String s){
		int i=0;
		int j=s.length()-1;
		while(i < j){
            //skip non-alphanumeric characters
			while(i<j && !Character.isLetterOrDigit(s.charAt(i))){
				i++;
			}
			while(i<j && !Character.isLetterOrDigit(s.charAt(j))){
				j--;
			}
			if(Character.toLowerCase(s.charAt(i)) != Character.toLowerCase(s.charAt(j))){
				return false;
			}
			i++;
			j--;
		}
		return true;
	}
}