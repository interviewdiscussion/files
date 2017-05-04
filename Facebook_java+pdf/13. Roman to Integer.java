public class Solution {
    public int romanToInt(String s) {
        int res=toNumber(s.charAt(0));
        for(int i=1;i<s.length();i++){
            if(toNumber(s.charAt(i-1))<toNumber(s.charAt(i))){
                res+=toNumber(s.charAt(i))-2*toNumber(s.charAt(i-1));
            }else {
                res+=toNumber(s.charAt(i));
            }
        }
        return res;
    }
    public int toNumber(char c){
        switch (c){
            case 'I': return 1;
            case 'V': return 5;
            case 'X': return 10;
            case 'L': return 50;
            case 'C': return 100;
            case 'D': return 500;
            case 'M': return 1000;
        }
        return 0;
    }
}
IntToRoman
public class Solution {
    public String intToRoman(int num) {
        String A[] = {"", "M", "MM", "MMM"};
        String B[] = {"", "C", "CC", "CCC", "CD", "D", "DC", "DCC", "DCCC", "CM"};
        String C[] = {"", "X", "XX", "XXX", "XL", "L", "LX", "LXX", "LXXX", "XC"};
        String D[] = {"", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX"};
        return A[num/1000] + B[(num%1000)/100] + C[(num%100)/10] + D[num%10];
    }
}