/**
 * Sudoku is a class which takes in a sudoku board and provides its solutions.
 * 
 * @author Pavel Rozvora
 * @version 2017-04-27
 */
public class Sudoku {
	/**
	 * The main program.
	 */
	public static void main(String[] args) {
		SudokuModel model = new SudokuModel();
		SudokuTextUI ui = new SudokuTextUI();
		model.setModelListener(ui);
		ui.setViewListener(model);
		ui.displayOptions();
	}
}
