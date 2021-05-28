/*
Programmer: Shannon Chesterfield
Class: Artificial Intelligence
Date: 2/1/2019

*/
public class Sudoku {
	
	// create starting puzzle to solve with a 2D array
	public static int[][] startergrid = {
			{0,0,0,0,0,7,3,4,0},
			{4,0,0,2,0,0,0,6,8},
			{0,0,0,9,0,0,7,0,0},
			{6,0,0,0,0,0,8,7,0},
			{0,9,5,0,0,0,6,2,0},
			{0,2,7,0,0,0,0,0,3},
			{0,0,2,0,0,6,0,0,0},
			{3,8,0,0,0,1,0,0,5},
			{0,1,4,5,0,0,0,0,0},
	};
	
	private int[][] board;
	public static final int EMPTY = 0; // empty cell
	public static final int SIZE = 9; // size of our Sudoku grids
	
	public Sudoku(int[][] board) {
		this.board = new int[SIZE][SIZE];
		
		for (int i = 0; i < SIZE; i++) {
			for (int j = 0; j < SIZE; j++) {
				this.board[i][j] = board[i][j];
			}
		}
	}
	
	// checks row for valid entry
	private boolean inRow(int row, int number) {
		for (int i = 0; i < SIZE; i++)
			if (board[row][i] == number)
				return true;
		
		return false;
	}
	
	// checks column for valid entry
	private boolean inCol(int col, int number) {
		for (int i = 0; i < SIZE; i++)
			if (board[i][col] == number)
				return true;
		
		return false;
	}
	
	// checks respective 3x3 box for valid entry
	private boolean inBox(int row, int col, int number) {
		int r = row - row % 3;
		int c = col - col % 3;
		
		for (int i = r; i < r + 3; i++)
			for (int j = c; j < c + 3; j++)
				if (board[i][j] == number)
					return true;
		
		return false;
	}
	
	// method to check all three acceptable conditions at once, row, column, 3x3 box
	private boolean validEntry(int row, int col, int number) {
		return !inRow(row, number)  &&  !inCol(col, number)  &&  !inBox(row, col, number);
	}
	
	// Solve method DFS/backtracking
       public boolean solve() {
        for (int row = 0; row < SIZE; row++) {
         for (int col = 0; col < SIZE; col++) {
          // search an empty cell
          if (board[row][col] == EMPTY) {
            // attempt possible numbers
            for (int number = 1; number <= SIZE; number++) {
              if (validEntry(row, col, number)) {
                // valid entry that follows sudoku rules
                board[row][col] = number;

                if (solve()) { // start backtracking until solved
                  return true;
                } else { // empty the cell and continue if no solution is found
                  board[row][col] = EMPTY;
                }
             }
            }

            return false;
           }
          }
         }

         return true; // solved
	}
	
	public void display() {
		for (int i = 0; i < SIZE; i++) {
			for (int j = 0; j < SIZE; j++) {  // displays board
				System.out.print(" " + board[i][j]);
			}
		
			System.out.println();
		}
		
		System.out.println();
	}
	
	public static void main(String[] args) {
		Sudoku sudoku = new Sudoku(startergrid);
		System.out.println("Grid before solution");
		sudoku.display();
		
		// we try resolution
		if (sudoku.solve()) {
			System.out.println("Finished grid");
			sudoku.display();
		} else {
			System.out.println("Unsolvable");
		}
	}

}
