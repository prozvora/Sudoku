/**
 * SudokuModel implements a backtracking algorithm to find solutions to the
 * supplied board.
 * 
 * @author Pavel Rozvora
 * @version 2017-04-27
 */
public class SudokuModel implements ViewListener {
	private ModelListener modelListener;
	private Node[][] board;
	/**
	 * Constructor.
	 */
	public SudokuModel() {
	}
	/**
	 * Associate the model with a UI.
	 * 
	 * @param modelListener the UI
	 */
	public void setModelListener(ModelListener modelListener) {
		this.modelListener = modelListener;
	}
	/**
	 * Build a new Sudoku board.
	 * 
	 * @param input The Sudoku board.
	 */
	public void newBoard(String input) {
		Node[][] board = new Node[9][9];
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				try {
					int current = (input.charAt(9*i + j) - 48);
					if (current >= 1 && current <= 9) {
						board[i][j] = new Node(current, false);
					} else if (current == 0) {
						board[i][j] = new Node(current, true);
					} else {
						usage();
					}
				} catch (Exception e) {
					usage();
				}
			}
		}
		this.board = board;
	}
	/**
	 * Find a solution to the board, if it exists.
	 */
	public void solve() {
		boolean solved = checkSolved();
		if (solved) {
			solve(solved);
		} else {
			boolean impossible = false;
			for (int i = 0; i < 9; i++) {
				for (int j = 0; j < 9; j++) {
					if (!board[i][j].getVar() && !checkValid(i, j)) {
						//impossible supplied board
						impossible = true;
						modelListener.printBoard(0);
						break;
					}
				}
				if (impossible) {
					break;
				}
			}
			if (!impossible) {
				solve(solved);
			}
		}
	}
	/**
	 * The underlying backtracking algorithm which attempts to solve the board.
	 * 
	 * @param solved true if the board has already been solved, false otherwise
	 */
	private void solve(boolean solved) {
		int r = 0, c = 0;
		boolean forward = true;
		if (solved) {
			r = 8;
			c = 8;
			//if last cell is not variable, go back to the last one that is
			forward = false;
		}
		while (true) {
			if (r < 0) {
				//board has no additional solutions or was impossible
				if (!solved) {
					modelListener.printBoard(0);
				} else {
					modelListener.printBoard(1);
				}
				break;
			}
			if (r > 8) {
				//board is solved
				solved = true;
				modelListener.printBoard(2);
				break;
			}
			if (!board[r][c].getVar()) {
				//skip this cell and increment/decrement another
			} else {
				board[r][c].setVal(board[r][c].getVal()+1);
				while (!checkValid(r, c)) {
					board[r][c].setVal(board[r][c].getVal()+1);
				}
				if (board[r][c].getVal() == 10) {
					board[r][c].setVal(0);
					forward = false;
				} else {
					forward = true;
				}
			}
			if (forward) {
				c++;
				if (c > 8) {
					r++;
					c = 0;
				}
			} else {
				c--;
				if (c < 0) {
					r--;
					c = 8;
				}
			}
		}
	}
	private boolean checkSolved() {
		boolean s = true;
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				if (board[i][j].getVal() == 0) {
					s = false;
					break;
				}
			}
			if (!s) {
				break;
			}
		}
		return s;
	}
	/**
	 * Check the given cell for validity.
	 * The cell is invalid if another cell in the same row, column, or box has
	 * the same value.
	 * 
	 * @param r the cell's row
	 * @param c the cell's column
	 * @return true if the cell is valid, false otherwise
	 */
	private boolean checkValid(int r, int c) {
		//check row
		for (int i = 0; i < 9; i++) {
			if (i != c && board[r][i].getVal() == board[r][c].getVal()) {
				return false;
			}
		}
		//check col
		for (int i = 0; i < 9; i++) {
			if (i != r && board[i][c].getVal() == board[r][c].getVal()) {
				return false;
			}
		}
		//check box
		int topRow = r/3 * 3;	//{0,1,2} -> 0, {3,4,5} -> 3, {6,7,8} -> 6
		int topCol = c/3 * 3;
		for (int i = topRow; i < topRow + 3; i++) {
			for (int j = topCol; j < topCol + 3; j++) {
				if (i != r && j != c && 
						board[i][j].getVal() == board[r][c].getVal()) {
					return false;
				}
			}
		}
		return true;
	}
	/**
	 * Output the board to standard output.
	 */
	public void printBoard() {
		System.out.println();
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				if (board[i][j].getVal() == 0) {
					System.out.print("  ");
				} else {
					System.out.print(board[i][j].getVal() + " ");
				}
			}
			System.out.println();
		}
		System.out.println();
	}
	/**
	 * Reset the board to its initial supplied state
	 */
	public void resetBoard() {
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				if (board[i][j].getVar()) {
					board[i][j].setVal(0);
				}
			}
		}
	}
	/**
	 * Quit the program.
	 */
	public void quit() {
		System.exit(0);
	}
	/**
	 * Prints an error message outlining the proper format for supplying the
	 * board if the board hasn't been supplied properly.
	 */
	private static void usage() {
		System.err.println("Board data is supplied with 81 digits in 0-9");
		System.err.println("Numbers 1-9 represent initial filled in cells");
		System.err.println("0's represent initially blank cells");
		System.exit(0);
	}
}
