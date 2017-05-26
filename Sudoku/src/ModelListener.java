/**
 * ModelListener defines the methods needed for communication from UI to model.
 * 
 * @author Pavel Rozvora
 * @version 2017-05-26
 */
public interface ModelListener {
	/**
	 * Supply a Sudoku board to the model.
	 * 
	 * @param input The Sudoku board.
	 */
	public void newBoard(String input);
	/**
	 * Reset the board to its initial supplied state.
	 */
	public void resetBoard();
	/**
	 * Find a solution to the board, if it exists.
	 */
	public void solve();
	/**
	 * Display the solved board or a message if it has no solution.
	 * If status == 0, the board has no solution.
	 * If status == 1, the board has no additional solutions.
	 * If status == 2, the board has been solved.
	 * 
	 * @param status A value which indicates the solved status of the board
	 */
	public void displayBoard(int status);
	/**
	 * Quit the program.
	 */
	public void quit();
}
