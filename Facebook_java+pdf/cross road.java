public class Solution {
    public int maxKilledEnemies(char[][] grid) {
        if(grid == null || grid.length == 0 ||  grid[0].length == 0) return 0;
        int max = 0;
        int row = 0;
        int[] col = new int[grid[0].length];
        for(int i = 0; i<grid.length; i++){
            for(int j = 0; j<grid[0].length;j++){
                if(grid[i][j] == '0') continue;
                if(j == 0 || grid[i][j-1] == '0'){
                     row = killedEnemiesRow(grid, i, j);
                }
                if(i == 0 || grid[i-1][j] == '0'){
                     col[j] = killedEnemiesCol(grid,i,j);
                }
                if(grid[i][j] == '1'){
                    max = (row + col[j] > max) ? row + col[j] : max;
                }
            }
    
        }
        
        return max-1;
    }
    
    //calculate killed enemies for row i from column j
    private int killedEnemiesRow(char[][] grid, int i, int j){
        int num = 0;
        while(j <= grid[0].length-1 && grid[i][j] != '0'){
            if(grid[i][j] == '1') num++;
            j++;
        }
        return num;
    }
    //calculate killed enemies for  column j from row i
    private int killedEnemiesCol(char[][] grid, int i, int j){
        int num = 0;
        while(i <= grid.length -1 && grid[i][j] != '0'){
            if(grid[i][j] == '1') num++;
            i++;
        }
        return num;
    }
}