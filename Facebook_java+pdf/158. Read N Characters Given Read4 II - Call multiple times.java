/* The read4 API is defined in the parent class Reader4.
      int read4(char[] buf); */
## Idea 
* Based on 159
* Because of call multiple times, there are more corner cases
* First time we call, we need to save extra characters for next call
*  In the while loop, if buffPointer reaches current buffCount, it will be set as zero to be ready to read new data.
public class Solution extends Reader4 {
    /**
     * @param buf Destination buffer
     * @param n   Maximum number of characters to read
     * @return    The number of characters read
     */
    private int pointer = 0;//pointer of temp array
    private int counter = 0;//num of chars in temp
    private char[] temp = new char[4];//can be 4096 here in interview
    
    public int read(char[] buf, int n) {
        int index = 0;//index of buf, also means the total chars we have read
        while (index < n) {
            if (pointer == 0) {//this means we should read new chars
                counter = read4(temp);
            }
            if (counter == 0) {//if no more chars can be read (n > total chars in file)
                break;
            }
            while (index < n && pointer < counter) {//pointer < counter, not <= cuz counter is num of chars, not index
                buf[index++] = temp[pointer++];//both pointers should be increased
            }
            if (pointer == counter) {//if all chars in temp have been read, update the pointer to 0 (need read new chars)
                pointer = 0;
            }
        }
        return index;
    }
}

LeetCode是int read(char *buf, int n)。
当时楼主做的是int read(int n)，自己开了个global buffer。
感觉可以和面试官讨论直到把function signature改得和LeetCode一模一样。

楼主看过面经，知道会被问read4() 2，但是一开始还是没问清，给了个read4() 1的答案。。
记住要问read()会不会被called multiple times。如果是被called multiple times，就是read4() 2了。