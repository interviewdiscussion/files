O(m*n)    
    static private int m;
	static private int n;
	static private int size;
	public static void numIslands(char[][] grid,int k) {
        m=grid.length;
        if(m==0) return;
        n=grid[0].length;
        boolean[][] bool=new boolean[m][n];
        char[][] temp=new char[m][n];
        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                temp[i][j] = grid[i][j];
            }
        }
        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                if(temp[i][j]=='1'){
                	size=0;
                    dfs(temp,i,j);
                    if(size<k){
                    	remove(grid,i,j);
                    }
                }
            }
        }
    }
    public static void dfs(char[][] grid,int i,int j){
    	if(i<0||j<0||i>=m||j>=n||grid[i][j]=='0') return;
        grid[i][j]='0';
        size++;
        dfs(grid,i,j+1);
        dfs(grid,i,j-1);
        dfs(grid,i+1,j);
        dfs(grid,i-1,j);
    }
    public static void remove(char[][] grid,int i,int j){
        if(i<0||j<0||i>=m||j>=n||grid[i][j]=='0') return;
        grid[i][j]='0';
        remove(grid,i,j+1);
        remove(grid,i,j-1);
        remove(grid,i+1,j);
        remove(grid,i-1,j);
    }