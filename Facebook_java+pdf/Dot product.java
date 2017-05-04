Sparce, long and short
class Tuple{
        int val,x;
        Tuple(int val,int x){
            this.val=val;
            this.x=x;
        }
    }
public int dotProduct(int[] a, int[] b){
        int len = a.length;
        int res = 0;
        for( int i = 0 ; i < len ; i++){
            res += a[i] * b[i];
        }
        return res;
    }
O(m*n)
public int SparseVectorProduct(int[] a,int[] b){
            int res=0;
            List<Tuple> l1=new ArrayList<>();
            List<Tuple> l2=new ArrayList<>();
            for(int i=0;i<a.length;i++){
                if(a[i]!=0) l1.add(new Tuple(a[i],i));
            }
            for(int i=0;i<b.length;i++){
                if(b[i]!=0) l2.add(new Tuple(b[i],i));
            }
            for(Tuple t1:l1){
                for(Tuple t2:l2){
                    if(t1.x==t2.x) res+=t1.val*t2.val;
                }
            }
            return res;
        }

这个是O（m+n） sparce
int i=0,j=0;
while(i<l1.size()&&j<l2.size()){
    while(l1.get(i).x<l2.get(j).x) i++;
    if(l1.get(i).x==l2.get(j).x){
        res+=l1.get(i).val*l2.get(j).val;
        i++;j++;
    }
    while(l1.get(i).x>l2.get(j).x) j++;
}
O(n*logm) long and short
int i=0,j=l2.size()-1;
for(Tuple t1:l1){//短的那个
    while(i<=j){
        int mid=(j-i)/2+i;
        if(l2.get(mid).x==t1.x) res+=t1.val*l2.get(mid).val;
        if(l2.get(mid)<t1.x) j=mid;
        else i=mid+1;
    }
}
