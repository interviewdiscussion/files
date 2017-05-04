时间复杂度：O(m * n)
public class Solution {
    public int uniquePaths(int m, int n) {
        Integer[][] map=new Integer[m][n];
        for(int i=0;i<m;i++)
            map[i][0]=1;
        for(int j=0;j<n;j++)
            map[0][j]=1;
        for(int i=1;i<m;i++){
            for(int j=1;j<n;j++){
                map[i][j]=map[i-1][j]+map[i][j-1];
            }
        }
        return map[m-1][n-1];
    }
}
时间复杂度：O(m + n)
//而对于空间可以看出我们每次只需要用到上一行当前列，以及前一列当前行的信息，我们只需要用一个一维数组存上一行的信息即可，然后扫过来依次更替掉上一行对应列的信息即可（因为所需要用到的信息都还没被更替掉）
public class Solution {
    public int uniquePaths(int m, int n) {
        int[] arr=new int[n];
        for(int i=0;i<n;i++){
            arr[i]=1;
        }
        for(int i=1;i<m;i++){
            for(int j=1;j<n;j++)
                arr[j]+=arr[j-1];
        }
        return arr[n-1];
    }
}
//上面的方法用动态规划来求解，如果我们仔细的看这个问题背后的数学模型，其实就是机器人总共走m+n-2步，其中m-1步往下走，n-1步往右走，本质上就是一个组合问题，也就是从m+n-2个不同元素中每次取出m-1个元素的组合数。根据组合的计算公式，我们可以写代码来求解即可。
public class Solution {
    public int uniquePaths(int m, int n) {
        long result=1;
        for(int i=0;i<Math.min(m-1,n-1);i++){//结果没区别，但运行时间有区别
            result=result*(m+n-2-i)/(i+1);
        }
        return (int)result;
    }
}//C 底数m+n-2，指数 m-1或者n-1