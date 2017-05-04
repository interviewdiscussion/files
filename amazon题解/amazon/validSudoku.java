/**
* Problem: Determine if a sudoku if valid
*
* Solution: matrix traversal, check by row, column, and submatrix
* follow-up: valid side length should be square of n
* follow-up: modify the program for any valid side length
* 
*/
public class ValidSudoku{
	public boolean isValidSudoku(char[][] board){
		int row = board.length;
		int col = 0;
       //check board size
		if(row!=9){
			return false;
		}else{
			col = board[0].length;
			if(col!=9){
				return false;
			}
		}
		boolean[] visited = new boolean[9];
        //check rule by row
		for(int i=0; i<row; i++){
			visited = new boolean[9];
			for(int j=0; j<col; j++){
				if(!isValidSlot(visited,board[i][j])){
					return false;
				}
			}
		}
        //check rule by column
		for(int i=0; i<col; i++){
			visited = new boolean[9];
			for(int j=0; j<row; j++){
				if(!isValidSlot(visited,board[j][i])){
					return false;
				}
			}
		}
       //check subsquare
		for(int subRow=0; subRow<row; subRow+=3){
			for(int subCol=0; subCol<col; subCol+=3){
				visited = new boolean[9];
				for(int i=subRow; i<subRow+3; i++){
					for(int j=subCol; j<subCol+3; j++){
						if(!isValidSlot(visited,board[i][j])){
							return false;
						}
					}
				}
			}
		}
		return true;
	}


     //check if slot is valid and set visited if necessary
	public boolean isValidSlot(boolean[] visited, char digit){
		if(digit == '.'){
			return true;
		}
		int val = digit - '0';
		if(val>=1 && val<=9 && !visited[val-1]){
			visited[val-1] = true;
			return true;
		}
		return false;
	}
}