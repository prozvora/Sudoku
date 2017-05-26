/**
 * Sudoku is a class which takes in a sudoku board and provides its solutions.
 * 
 * @author Pavel Rozvora
 * @version 2017-05-26
 */
public class Sudoku {
	/**
	 * The main program.
	 */
	public static void main(String[] args) {
		SudokuModel model = new SudokuModel();
		if (args[0].equals("text")) {
			SudokuTextUI ui = new SudokuTextUI();
			model.setModelListener(ui);
			ui.setViewListener(model);
			ui.displayOptions();
		} else if (args[0].equals("gui")) {
			SudokuGUI ui = new SudokuGUI();
			model.setModelListener(ui);
			ui.setViewListener(model);
		} else {
			usage();
		}
		
	}
	/**
	 * If the input is faulty, the program will print a usage message and exit.
	 */
	public static void usage() {
		System.err.println("Usage: java Sudoku <viewType>");
		System.err.println("<viewType> is a choice of UI. "
				+ "Valid choices are \"text\" and \"gui\"");
		System.exit(0);
	}
}
