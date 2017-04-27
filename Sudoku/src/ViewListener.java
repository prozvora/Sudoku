/**
 * ViewListener defines the methods needed for communication from model to UI.
 * 
 * @author Pavel Rozvora
 * @version 2017-04-27
 */
public interface ViewListener {
	/**
	 * Build a new Sudoku board.
	 * 
	 * @param input The Sudoku board.
	 */
	public void newBoard(String input);
	/**
	 * Reset the board to its initial supplied state
	 */
	public void resetBoard();
	/**
	 * Find a solution to the board, if it exists.
	 */
	public void solve();
	/**
	 * Output the board to standard output.
	 */
	public void printBoard();
	/**
	 * Quit the program.
	 */
	public void quit();
}
