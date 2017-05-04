// 比如排列是(2,3,6,5,4,1)
// 1) 先从后往前找到第一个不是依次增长的数，记录下位置p。比如例子中的3，对应的位置是1;
// 2) 接下来分两种情况：
//     (1) 如果上面的数字都是依次增长的，那么说明这是最后一个排列，下一个就是第一个，其实把所有数字反转过来即可(比如(6,5,4,3,2,1)下一个是(1,2,3,4,5,6));
//     (2) 否则，如果p存在，从p开始往后找，找到下一个数就比p对应的数小的数字，然后两个调换位置，比如例子中的4。调换位置后得到(2,4,6,5,3,1)。最后把p之后的所有数字倒序，比如例子中得到(2,4,1,3,5,6), 这个即是要求的下一个排列。

public class nextPermutation{    public void nextPermutation(int[] num) {  
        if(num==null || num.length==0)  
            return;  
        int i = num.length-2;  
        while(i>=0 && num[i]>=num[i+1])  
        {  
            i--;  
        }  
        if(i>=0)  
        {  
            int j=i+1;  
            while(j<num.length && num[j]>num[i])  
            {  
                j++;  
            }  
            j--;  
            int temp = num[i];  
            num[i] = num[j];  
            num[j] = temp;  
        }  
        reverse(num, i+1);  
    }  
    private void reverse(int[] num, int index)  
    {  
        int l = index;  
        int r = num.length-1;  
        while(l<r)  
        {  
            int temp = num[l];  
            num[l] = num[r];  
            num[r] = temp;  
            l++;  
            r--;  
        }  
    }  }