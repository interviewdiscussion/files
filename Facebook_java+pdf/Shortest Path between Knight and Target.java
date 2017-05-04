public class Solution {8^n
   //output min steps from a knight to a target in a matrix
   private static int[][] move = new int[][]{{1, 2}, {2, 1}, {-1, 2}, {-2, 1}, {1, -2}, {2, -1}, {-1, -2}, {-2, -1}};

   private static int knightToTarget(int[][] matrix, int x1, int y1, int x2, int y2) {
       if (matrix == null || matrix.length == 0 || matrix[0] == null || matrix[0].length == 0) {
           return -1;
       }
       int m = matrix.length;
       int n = matrix[0].length;
       if (!isValid(matrix, m, n, x1, y1) || !isValid(matrix, m, n, x2, y2)) {
           return -1;
       }
       Queue<Integer> queue = new LinkedList<>();
       boolean[][] visited = new boolean[m][n];
       int res = 0;
       queue.offer(x1 * n + y1);
       visited[x1][y1] = true;//if we can modify the board, use board[x1][y1] = 1;
       while (!queue.isEmpty()) {
           int size = queue.size();
           for (int i = 0; i < size; i++) {
               int key = queue.poll();
               int x = key / n;
               int y = key % n;
               if (x == x2 && y == y2) {
                   return res;
               }
               for (int k = 0; k < move.length; k++) {
                   int nextX = x + move[k][0];
                   int nextY = y + move[k][1];
                   if (isValid(matrix, m, n, nextX, nextY) && !visited[nextX][nextY]) {//board[nextX][nextY] != 1
                       queue.offer(nextX * n + nextY);
                       visited[nextX][nextY] = true;//board[nextX][nextY] = 1
                   }
               }
           }
           res++;
       }
       return -1;
   }
   private static boolean isValid(int[][] matrix, int m, int n, int i, int j) {
       if (i < 0 || i >= m || j < 0 || j >= n) {
           return false;
       }
       return true;
   }
}