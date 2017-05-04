public class Solution {
    public void solveSudoku(char[][] board) {
        if (board == null || board.length != 9 || board[0] == null || board[0].length != 9) return;
        solve(board, 0, 0);
    }
    
    private boolean solve(char[][] board, int i, int j) {
        if (i == 9) return true;//if we've solve all the rows(which means the sudoku solved),return true(stop all resursions)
        if (j >= 9) return solve(board, i + 1, 0);//if curr row has been filled, jump to the next row's first col
        if (board[i][j] == '.') {
            for (int k = 1; k <= 9; k++) {//k = 1, k <= 9, not k = 0, k < 9 !!!!!!!!!!!!!!!
                board[i][j] = (char) (k + '0');//see how to get the char
                if (isValid(board, i, j)) {//if board[i][j] = k is valid
                    if (solve(board, i, j + 1)) return true;//if finally sudoku solved(i==9),return true(stop all resursions)
                }
                board[i][j] = '.';//if we can't, we set board[i][j] back to '.' and try the next number
            }
        } else {//if board[i][j] != '.', just jump to the next position
            return solve(board, i, j + 1);
        }
        return false;//if board[i][j] == '.' but it doesn't have a valid answer from 1-9
    }
    
    private boolean isValid(char[][] board, int i, int j) {
        for (int col = 0; col < 9; col++) {
            if (col != j && board[i][j] == board[i][col]) return false;
        }
        for (int row = 0; row < 9; row++) {
            if (row != i && board[i][j] == board[row][j]) return false;
        }
        for (int row = i / 3 * 3; row < i / 3 * 3 + 3; row++) {//see how to iterate the submatrix
            for (int col = j / 3 * 3; col < j / 3 * 3 + 3; col++) {//col < j / 3 * 3 + 3, not col < i / 3 * 3 + 3 !!!!!!!!!!!!
                if ((row != i || col != j) && board[i][j] == board[row][col]) return false;//use ||, not && !!!!!!!!!!!!!!
            }
        }
        return true;
    }
}