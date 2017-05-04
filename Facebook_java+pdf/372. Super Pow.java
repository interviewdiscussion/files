public class Solution {
    int base =1337;
    public int superPow(int a, int[] b) {
        return helper(a,b,b.length-1);
    }
    int helper(int a,int[]b,int endidx){
        if(endidx==-1) return 1;
        int last_digit=b[endidx];
        return powmod(helper(a,b,endidx-1),10)*powmod(a,last_digit)%base;
    }
    int powmod(int a,int k){
        a%=base;
        int result=1;
        for(int i=0;i<k;i++){
            result=(result*a)%base;
        }
        return result;
    }
}
* Calculates a to the power of b, mod c
*  distribution law for modulo:
   (x*y)%z == ((x%z)*y)%z == (x*(y%z))%z. 
* Examples:
   PowMod(2,3,5) = 2*2*2 % 5 = 8%5 =3.
   PowMod(3, 6, 7) = 3*3*3*3*3*3 % 7 = 729%7 =1.
   PowMod(16,16,5) = 1 */