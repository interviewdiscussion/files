// O(mn) time, O(n) space, stack solution
//calculate area of rectangle expanding from curr j col to left&right bounds,from curr row to top "0"/top boundary
public class Solution {
    public int maximalRectangle(char[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0] == null || matrix[0].length == 0) {
            return 0;
        }
        int m = matrix.length;
        int n = matrix[0].length;
        int max = 0;
        int[] heights = new int[n + 1];//height[j]:the num of "1" in j col from currRow to the top "0"/top boundary
        for (int i = 0; i < m; i++) {
            Stack<Integer> stack = new Stack<>();
            for (int j = 0; j < n + 1; j++) {
                if (j < n && matrix[i][j] == '1') {
                    heights[j] += 1;//update the height that expand from curr row to the top '1'
                } else {//which means the last num(heights[n]) is always 0; and if j < n but it's not '1', it should be 0 too
                    heights[j] = 0;
                }
                
                //each loops we calculate the area of rectangle that ends at curr j, loops until the top is less than curr j
                //each num only goes in&out stack once,so this is O(1) time
                //if curr height <= peek(),calculate the max area, and update left bound by poping out the top
                while (!stack.empty() && heights[j] <= heights[stack.peek()]) {
                    int height = heights[stack.pop()];
                    int width = j;//if stack is empty,it means the width of curr rectangle starts from leftmost(0) to curr j
                    if (!stack.empty()) {//if stack isn't empty,it means the width of curr rectangle starts from peek() to j
                        width = width - stack.peek() - 1;
                    }
                    max = Math.max(max, height * width);//update the max rectangle
                }
                stack.push(j);//remember to push j !!!
            }
        }
        return max;
    }
}


// O(mn) time, O(n) space dp solution, only need to maintain 3 1d arrays to iterate each rows and cols
//the idea is:calculate area of rectangle expanding from curr j col to left&right bounds,from curr row to top "0"/top boundary
public class Solution {
    public int maximalRectangle(char[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0] == null || matrix[0].length == 0) {
            return 0;
        }
        int m = matrix.length;
        int n = matrix[0].length;
        int max = 0;
        int[] height = new int[n];//height[j]:the num of "1" in j col from currRow to the top "0"/top boundary
        int[] left = new int[n];//left[j]:the index of rightmost leftbound of j col from currRow to the top "0"/top boundary
        int[] right = new int[n];//left[j]:the index of rightmost leftbound of j col from currRow to the top "0"/top boundary
        for (int i = 0; i < right.length; i++) {
            right[i] = n;//initialize right with n, a num like "Integer.MAX_VALUE" so that each time got a valid right bound
        }//(when matrix[i][j]=='1'), we can update the right[j] later with the value of currRightBound by using min()
        for (int i = 0; i < m; i++) {//iterate each row
            int currLeftBound = 0;
            int currRightBound = n;//should be n !!!
            
            //iterate each col to get each j col's height
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == '1') {
                    height[j]++;
                } else {
                    height[j] = 0;//if curr col got a cut off by 0, we should turn the height back to 0 again
                }
            }
            
            //iterate each col from left to right to get each j col's leftbound
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == '1') {
                    left[j] = Math.max(left[j], currLeftBound);
                } else {//0 is a num like "Integer.MIN_VALUE" so that each time we got a valid left bound(when matrix[i][j]
                    left[j] = 0;//=='1'), we can update the left[j] later with the value of currLeftBound by using max()
                    currLeftBound = j + 1;//+1 here so it will be easy to figure out the value of difference between bounds
                }//also cuz use max,bound should always be larger than 0 if we got a valid left bound(when matrix[i][j]=='1')
            }
            
            //iterate each col from right to left to get each j col's rightbound
            for (int j = n - 1; j >= 0; j--) {
                if (matrix[i][j] == '1') {
                    right[j] = Math.min(right[j], currRightBound);
                } else {
                    right[j] = n;
                    currRightBound = j;
                }
            }
            
            //calculate area of rectangle expanding from curr j col to left&right bounds,from curr row to top "0"/top boundary
            for (int j = 0; j < n; j++) {
                max = Math.max(max, (right[j] - left[j]) * height[j]);
            }
        }
        return max;//max is already the area, no need to max*max
    }
}
use Math.max(left[j], currLeftBound) cuz we need to use the rightmost left boundary when we expanding to left from j col
use Math.min(right[j], currRightBound) cuz we need to use the leftmost right boundary when we expanding to right from j col
1. left(i,j) = max(left(i-1,j), cur_left), cur_left can be determined from the current row
2. right(i,j) = min(right(i-1,j), cur_right), cur_right can be determined from the current row
3. height(i,j) = height(i-1,j) + 1, if matrix[i][j]=='1';
4. height(i,j) = 0, if matrix[i][j]=='0'
eg.
0 0 0 1 0 0 0 
0 0 1 1 1 0 0 
0 1 1 1 1 1 0
The vector "left" and "right" from row 0 to row 2 are as follows:

row 0:
l: 0 0 0 3 0 0 0
r: 7 7 7 4 7 7 7

row 1:
l: 0 0 2 3 2 0 0
r: 7 7 5 4 5 7 7 

row 2:
l: 0 1 2 3 2 1 0
r: 7 6 5 4 5 6 7
The vector "left" is computing the left boundary.Take (i,j)=(1,3) for example, on current row 1, left boundary is at j=2.
However, cuz matrix[1][3] is 1,we need to consider the left boundary on previous row too, which is 3, not currLeftBound2.
So the real left boundary at (1,3) is 3.