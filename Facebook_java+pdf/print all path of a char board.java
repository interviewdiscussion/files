public class Solution {
    private int m;
    private int n;
    public int numIslands(char[][] grid) {
        int count=0;
        m=grid.length;
        if(m==0) return 0;
        n=grid[0].length;
        dfs(grid,0,0,"");
    }
    public void dfs(char[][] grid,int i,int j,String s){
        if(i>m||j>n) return;
        if(i==m&&j==n) print(s+grid[i][j]);
        dfs(grid,i,j+1,s+grid[i][j]+"->");
        dfs(grid,i+1,j,s+grid[i][j]+"->");
    }
}
follow up是如果board很大，怎么优化。答用stringbuilder
class myCode {
    public static void main (String[] args) throws java.lang.Exception {
        char[][] test = {{'a','b','c'},{'e','f','g'}}; 
        myCode sol = new myCode();        
        List<String> res = sol.printAllPath(test);       
        System.out.println("xxx");        
        for(String s : res) {
            System.out.println(s);
        }
    
    }
    public List<String> printAllPath(char[][] board){
        List<String> res = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        dfs(board,res,sb,0,0);
        return res;
    }
    public void dfs(char[][] b, List<String> res, StringBuilder sb, int x, int y){
        int len = sb.length();
        sb.append(b[y][x]);
        
        if( x == b[0].length - 1 && y == b.length - 1){
            String s = sb.toString();
            res.add(s);
        } else{
            if( x + 1 < b[0].length) dfs(b,res,sb,x+1,y);
            if( y + 1 < b.length ) dfs(b,res,sb,x,y+1); 
        }
        sb.setLength(len);
    }
        
}
