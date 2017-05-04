int countPS(string str)
{
    int n = str.length();
    int[][] res=new int[n+1][n+1];
    for(int i=0;i<n;i++){
        res[i][i]=1;
    }
    for(int i=2;i<=n;i++){
        for(int j=0;j<n;j++){
            int k=i+j-1;
            if(str.charAt(j)==str.charAt(k)){
                res[j][k]=res[j][k-1]+res[j+1][k]+1;
            }else{
                res[j][k]=res[j][k-1]+res[j+1][k]-res[j+1][k-1];
            }
        }
    }
    // return total palindromic subsequence
    return res[0][n-1];
}
http://www.geeksforgeeks.org/count-palindromic-subsequence-given-string/
int countPS(string str)
{
    int N = str.length();
 
    // create a 2D array to store the count of palindromic
    // subsequence
    int cps[N+1][N+1];
    memset(cps, 0 ,sizeof(cps));
 
    // palindromic subsequence of length 1
    for (int i=0; i<N; i++)
        cps[i][i] = 1;
 
    // check subsequence of length L is palindrome or not
    for (int L=2; L<=N; L++)
    {
        for (int i=0; i<N; i++)
        {
            int k = L+i-1;
            if (str[i] == str[k])
                cps[i][k] = cps[i][k-1] +
                            cps[i+1][k] + 1;
            else
                cps[i][k] = cps[i][k-1] +
                            cps[i+1][k] -
                            cps[i+1][k-1];
        }
    }
 
    // return total palindromic subsequence
    return cps[0][N-1];
}