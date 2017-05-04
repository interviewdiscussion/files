public class Solution {
    public int divide(int dividend, int divisor) {
        int sign=1;
        if((dividend<0&&divisor>0)||(dividend>0&&divisor<0)) sign=-1;
        long ldividend=Math.abs((long)dividend);
        long ldivisor=Math.abs((long)divisor);
        if(ldividend<ldivisor||ldividend==0) return 0;
        long lans=divide(ldividend,ldivisor);
        int ans=0;
        if(lans>Integer.MAX_VALUE){
            ans= (sign==1)? Integer.MAX_VALUE:Integer.MIN_VALUE;
        }else ans=(int)(sign*lans);
        return ans;
    }
    private long divide(long ldividend,long ldivisor){
        if(ldividend<ldivisor) return 0;
        long sum=ldivisor;
        long multiple=1;
        while((sum+sum)<ldividend){
            sum+=sum;
            multiple+=multiple;
        }
        return multiple+divide(ldividend-sum,ldivisor);
    }
}
logn*logn 空间O(logn)
    
-----------------------------------------------------------------------------------------------
public class Solution {
    public int divide(int dividend, int divisor) {
        if (divisor == 0 || (dividend == Integer.MIN_VALUE && divisor == -1)) {
            return Integer.MAX_VALUE;
        }
        boolean isNegative = (dividend > 0 && divisor < 0) || (dividend < 0 && divisor > 0);
        long first = Math.abs((long)dividend);//if don't use (long), abs(Integer.MIN_VALUE) will not changed
        long second = Math.abs((long)divisor);
        int res = 0;
        while( first >= second ){
            long temp = second;
            int count =1;
            while( (temp << 1) <= first){
                temp <<= 1;
                count <<= 1;
            }
            res += count;
            first = first - temp;
        }
        return isNegative ? -res : res;
    }
}
Given  32 / 3 = 10
3 in binary 11
count = 1 
left shift 
temp 6 110      count 2  10
temp 12 110     count 4  100
temp 24 1100    count 8  1000 
res = 8 
new dvident = 32 - 24 = 8
temp 6 110     count 2 10
res = 8 + 2 = 10
    
    
第三种：加减
class myCode {
    public static void main (String[] args) throws java.lang.Exception {
        myCode sol = new myCode();
        int res = sol.divide(-7,2);
        System.out.println(res);

    }
    public int divide(int a, int b) {
        if( a == 0) return 0;
        if( b == 1) return a;
        if( b == 0 || (b == -1 && a == Integer.MIN_VALUE)) return Integer.MAX_VALUE;
        int sign = (a > 0 && b > 0) || ( a < 0 && b < 0) ? 1 : -1;
        long first = Math.abs((long)a) , second = Math.abs((long)b);
        if( first < second) return 0;
        int count = 0;
        while( first >= second ){
            first -= second;
            count++;
        }
        return count * sign;
    }
