/* The read4 API is defined in the parent class Reader4.
      int read4(char[] buf); */
## Idea
* Each time it reads 4 characters at a time from a file
* Return the actual number characters read
* Use temp buffer to store characters that every read4 generated
* Use an index to tracj the buf array current position
* Then save temp array to buffer array

Two corner cases:
* If I read 4 characters, but I only need 2,
* If I read 4 characters, but there are only 1 character.
So we need to use a flag to show if it is the end of the file and do a comparison between n and current total amount
public class Solution extends Reader4 {
    /**
     * @param buf Destination buffer
     * @param n   Maximum number of characters to read
     * @return    The number of characters read
     */
    public int read(char[] buf, int n) {
        char[] temp = new char[4];//use an array to store the 4 chars we read from the file
        int index = 0;//num of chars we have read
        while (true) {
            int count = read4(temp);//read 4 chars from file, write them into the array, return value is the num of chars read
            count = Math.min(count, n - index);//if n < total chars in file, count = n-index means we reach end of file
            for (int i = 0; i < count; i++) {
                buf[index++] = temp[i];//put all chars into the buf array, increment the index during this process
            }
            if (count < 4 || index == n) {//count<4 means all chars in file has been read,index==n means n<total chars in file
                return index;
            }
        }
    }
}

LeetCode是int read(char *buf, int n)。
当时楼主做的是int read(int n)，自己开了个global buffer。. 鍥磋鎴戜滑@1point 3 acres
感觉可以和面试官讨论直到把function signature改得和LeetCode一模一样。

楼主看过面经，知道会被问read4() 2，但是一开始还是没问清，给了个read4() 1的答案。。
记住要问read()会不会被called multiple times。如果是被called multiple times，就是read4() 2了。