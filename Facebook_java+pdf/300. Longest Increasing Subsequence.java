public class Solution {
    public int lengthOfLIS(int[] nums) {
        int[] tails = new int[nums.length];
        int size = 0;
        for (int x : nums) {
            int i = 0, j = size;
            while (i != j) {
                int m = (i + j) / 2;
                if (tails[m] < x)
                    i = m + 1;
                else
                    j = m;
            }
            tails[i] = x;
            if (i == size) ++size;
        }
        return size;
    }
}
10, 9, 2, 5, 3, 7, 101, 18

10 
9
2
2,5
2,3
2,3,7
2,3,7,101
2,3,7,18
## Idea ( Two for loop DP )
* Time O(n log n)

## Idea ( DP + Binary search)
* Tails is an int array storing the smallest tail of all increasing subsequences with length i+1 in tails[i].

```
For example, say we have nums = [4,5,6,3], then all the available increasing subsequences are:

len = 1   :      [4], [5], [6], [3]   => tails[0] = 3
len = 2   :      [4, 5], [5, 6]       => tails[1] = 5
len = 3   :      [4, 5, 6]            => tails[2] = 6

```
* We can easily prove that tails is a increasing array. Therefore it is possible to do a binary search in tails array to find the one needs update.
* Case 1 : if current number is larger than all tails, append it, increase the size by 1
* Case 2 : if tails[i-1] < x <= tails[i], update tails[i]

## Time
* O(nlogn)


## Visualization 

```
For intput 5,6,3,8,9,-1,0,1,2,3,4

left right 
0      0 -> 1     5
0      1 -> 2    5, 6
0      2 -> 3    5, 6 ,8       
0      3 -> 4    5 , 6, 8, 9 
0      num = -1 mid = 2 -> mid = 1 -> mid = 0 
0      4        -1 , 6, 8, 9 
0      num = 0, mid = 1      
0      4        -1, 1 ,8 ,9 
                -1, 1 , 2 ,9  
       5          -1, 1 , 2 , 3 ,4  
