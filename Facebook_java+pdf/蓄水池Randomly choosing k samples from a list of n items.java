O(n)
public class Solution{
    public int[] selectKItems(int[] stream,int k){
        Random rmd=new Random();
        int n=stream.length;
        int[] res=new int[k];
        // first k elements from stream[]
        for(int i=0;i<k;i++){
            res[i]=stream[i];
        }
        // Iterate from the (k+1)th element to nth element
        for(int i=k;i<n;i++){
            // Pick a random index from 0 to i.
            int j=rmd.nextInt(i+1);
            // If the randomly  picked index is smaller than k, then replace
            // the element present at the index with new element from stream
            if(j<k){
                res[j]=stream[i];
            }
        }
        return res;
    }
}
void selectKItems(int stream[], int n, int k)
{    

    Ramdom rmd=new Ramdom();
    // reservoir[] is the output array. Initialize it with
    // first k elements from stream[]
    int reservoir[k];
    for (i = 0; i < k; i++)
        reservoir[i] = stream[i];
 
    // Use a different seed value so that we don't get
    // same result each time we run this program
    srand(time(NULL));
 
    // Iterate from the (k+1)th element to nth element
    for (int i;; i < n; i++)
    {
        // Pick a random index from 0 to i.
        int j = rmd.nextInt(i+1);
 
        // If the randomly  picked index is smaller than k, then replace
        // the element present at the index with new element from stream
        if (j < k)
          reservoir[j] = stream[i];
    }
}