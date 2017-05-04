//dfs backtracking solution: O(4^n) time, O(n) stack space, n is word.length
public class Solution {
    public boolean exist(char[][] board, String word) {
        if (board == null || board.length == 0 || board[0] == null || board[0].length == 0
        || word == null || word.length() == 0) {
            return false;
        }
        int m = board.length;
        int n = board[0].length;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (word.charAt(0) == board[i][j]) {
                    boolean res = helper(board, word, m, n, i, j, 0);
                    if (res) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
    private boolean helper(char[][] board, String word, int m, int n, int i, int j, int index) {
        if (index == word.length()) {//which means all chars can be found
            return true;
        }
        if (i < 0 || i >= m || j < 0 || j >= n || word.charAt(index) != board[i][j]) {
            return false;
        }
        board[i][j] = '#';//mark it
        boolean res = helper(board, word, m, n, i + 1, j, index + 1) || helper(board, word, m, n, i - 1, j, index + 1) 
        || helper(board, word, m, n, i, j + 1, index + 1) || helper(board, word, m, n, i, j - 1, index + 1);
        board[i][j] = word.charAt(index);//change it back
        return res;
    }
}