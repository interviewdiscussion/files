Define amazing number as: its value is less than or equal to its index. Given a circular array, find the starting position, such that the total number of amazing numbers in the array is maximized.
Example 1: 0, 1, 2, 3
Ouptut: 0.    When starting point at position 0, all the elements in the array are equal to its index. So all the numbers are amazing number.
Example 2: 1, 0 , 0.
Output: 1.    When starting point at position 1, the array becomes 0, 0, 1. All the elements are amazing number.
If there are multiple positions, return the smallest one..
    //http://www.1point3acres.com/bbs/forum.php?mod=viewthread&tid=210159&extra=page%3D2%26filter%3Dsortid%26sortid%3D311%26searchoption%5B3090%5D%5Bvalue%5D%3D2%26searchoption%5B3090%5D%5Btype%5D%3Dradio%26searchoption%5B3048%5D%5Bvalue%5D%3D2%26searchoption%5B3048%5D%5Btype%5D%3Dradio%26searchoption%5B3046%5D%5Bvalue%5D%3D2%26searchoption%5B3046%5D%5Btype%5D%3Dradio%26sortid%3D311
    
    test case:
         4 5 0 1 2 3 9
         0 1 2 3 4 5 6
shift[1]:6 0 1 2 3 4 5
shift[2]:5 6 0 1 2 3 4
private static int bruteForce(int[] a) {
        int res = 0;
        int max = 0;
        for (int i = 0; i < a.length; i++) {
            int count = 0;//每个数开始验证，向右转，转0个位置，1，2。。。。
            for (int j = 0; j < a.length; j++) {//转i个位置后每个数是否符合条件，符合++，count有几个符合
                int p = j + i;
                //int ccc=j - a[p % a.length];
                if (j - a[p % a.length] >= 0) count++;
                //System.out.println(i + ": " +ccc);
            }
            if (count > max) {
                max = count;
                res = i;
            }
        }
        return res;
    }
test case  4,5,0,1,2,3,9
    0: -4 -4 2 2 2 2 -3
    1: -5 1 1 1 1 -4 2
    2: 0 0 0 0 -5 1 1 -1
    3: -1 -1 -1 -6 0 0 6
    4: -2 -2 -7 -1 -1 5  5
    5:.....
public static int amazingNumber(int[] nums) {
        int n = nums.length;
        int[] shifts = new int[n];
        // Keeps track of all the intervals that after right-shifting index, some numbers become amazing number.. 
        //首先你要知道 你当前index这个value 最少需要移几步 才能达到value<=index 如果说你的i<nums[i] 的话最少就要向右移i+1一步 
        /*
         test case: 4,5,0,1,2,3,9  ->5
         最少移动2步，因为移动两步后，4,5,0,1,2,3,9
                                 5,6 0,1,2,3,4
            数字5的下标就是index中最大的了-> n-1
        
        */
        //这样你此时的index就变成了n-1 肯定是>=value的 这就是你最少向右移的步数可以成为amazing number 这样的话当你向右i+1步的时候就会多出现一个amazing num 所以在shift[i+1]中加1同理 
        for(int i=0; i < n; i++) {
            // If the current number is negative or larger than the biggest index, it won't affect the final result.-google 1point3acres
            if(nums[i] >= n || nums[i] <= 0) continue;
            if(nums[i] > i) {
                // Right shift index i + 1 --> the current index would be n-1 after shifting
                shifts[i + 1] += 1;
                if(nums[i] > i + 1) shifts[i + 1 + n - nums[i]] -= 1;//当你向右移i+1+n-nums[i]步时 你的value>i 此时当前的amazing num就消失了 所以在 shift[i+1+n-nums[i]]中减1
            } else {
                // There would be two intervals for each nums[i] <= i
                shifts[0] += 1;//什么都不干本身就是amazing num 所以shift【0】+1
                shifts[i - nums[i] + 1] -= 1;

                if(i != n - 1) shifts[i + 1] += 1;
            }
        }
        int sum = 0, max = 0, index = 0;
        for(int i=0; i < shifts.length; i++) {
            sum += shifts[i];
            if(sum > max) {
                max = sum;
                index = i;
            }
        }
        return index;
    }
test case: 4,5,0,1,2,3,9
    
[0, 1, 0, 0, -1, 0, 0]
[0, 1, 1, 0, -2, 0, 0]
[1, 1, 1, -1, -1, 0, 0]
[2, 1, 1, -2, -1, 1, 0]
[3, 1, 1, -3, -1, 1, 1]