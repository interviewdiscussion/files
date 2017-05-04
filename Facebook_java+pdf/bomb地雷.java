windows里面的扫雷，给一个h,w和m. 生成一个高度h，宽度w，总共m颗雷的矩阵。要求m颗雷随机分布。
    时间：O(m)
public class Main {
	
	public static void main(String[] args) {
		bomb(5,5,8);
    }
	public static void bomb(int h,int w,int count){
		Random rmd=new Random();
		int[] nums=new int[h*w];//索引数组，长度为雷区中元素个数
		int size=h*w;
		for(int i=0;i<size;i++){
			nums[i]=i;//初始化索引数组，使每个元素不一样
		}
		int[] locations=new int[count];
		for(int i=0;i<count;i++){
			locations[i]=nums[i];
		}
		for(int i=count;i<size;i++){
			int j=rmd.nextInt(i+1);
			if(j<count) locations[j]=nums[i];
		}
		int[][] res=new int[h][w];
		for(int i=0;i<locations.length;i++){
			int x=locations[i]/h;
			int y=locations[i]%w;
			res[x][y]=1;
		}
		for(int i=0;i<res.length;i++){
			System.out.println(Arrays.toString(res[i]));
		}
		//return res;
	}
}
http://m.it610.com/article/3096698.htm