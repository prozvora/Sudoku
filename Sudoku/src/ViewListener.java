/**
 * ViewListener defines the methods needed for communication from model to UI.
 * 
 * @author Pavel Rozvora
 * @version 2017-05-26
 */
public interface ViewListener {
	/**
	 * Build a new Sudoku board.
	 * 
	 * @param input the Sudoku board.
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
	 * Give the board's string representation.
	 * 
	 * @return the String representation of the board
	 */
	public String boardToString();
	/**
	 * Quit the program.
	 */
	public void quit();
}
