
## Idea
* Use two largest values and update if we find smaller 
* First value : update if n[i] is smaller than both
* Second value L update only if n[i] is greater then small but smaller than big
* Return true if find n[i] bigger than both

## Test 
```
Input : 6 7 3 4 5 
First    6  3 
Second   7  4 -> true 
public class Solution {
    public boolean increasingTriplet(int[] nums) {
        int min=Integer.MAX_VALUE,secondemin=Integer.MAX_VALUE;
        for(int num:nums){
            if(num<=min) min=num;
            else if(num<=secondemin) secondemin=num; 
            else return true;
        }
        return false;
    }
}