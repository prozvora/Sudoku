import java.util.Scanner;
/**
 * SudokuTextUI contains the UI for the Sudoku program.
 * 
 * @author Pavel Rozvora
 * @version 2017-04-27
 */
public class SudokuTextUI implements ModelListener {
	private ViewListener viewListener;
	/**
	 * Constructor.
	 */
	public SudokuTextUI() {
		
	}
	/**
	 * Associate this UI with a model
	 * 
	 * @param viewListener the model
	 */
	public void setViewListener(ViewListener viewListener) {
		this.viewListener = viewListener;
	}
	/**
	 * Display the program's functions to the user.
	 */
	public void displayOptions() {
		Scanner s = new Scanner(System.in);
		while (true) {
			System.out.println("Choose an option:");
			System.out.println("1. Supply a new Sudoku board.");
			System.out.println("2. Reset the current board to its initial state.");
			System.out.println("3. Solve the board and display its solution, or find additional solutions.");
			System.out.println("4. Quit program.");
			System.out.print("Enter your selction (1-4): ");
			try {
				int choice = s.nextInt();
				switch (choice) {
				case 1: usage();
						System.out.print("Enter the board: ");
						String board = s.next();
						newBoard(board);
						break;
				case 2: resetBoard();
						break;
				case 3: solve();
						break;
				case 4: s.close();
						quit();
				default: System.err.println("\nInvalid choice.\n");
						break;
				}
			} catch (NullPointerException e) {
				System.err.println("\nPlease enter a board first (Option 1).\n");
			} catch (Exception e) {
				System.err.println("\nInvalid input, quitting program.\n");
				s.close();
				quit();
			}
		}
	}
	/**
	 * Supply a Sudoku board to the model.
	 * 
	 * @param input The Sudoku board.
	 */
	public void newBoard(String board) {
		viewListener.newBoard(board);
	}
	/**
	 * Reset the board to its initial supplied state
	 */
	public void resetBoard() {
		viewListener.resetBoard();
	}
	/**
	 * Find a solution to the board, if it exists.
	 */
	public void solve() {
		viewListener.solve();
	}
	/**
	 * Output the solved board or a message if it has no solution.
	 * If status == 0, the board has no solution.
	 * If status == 1, the board has no additional solutions.
	 * If status == 2, the board has been solved.
	 * 
	 * @param status A value which indicates the solved status of the board
	 */
	public void printBoard(int status) {
		if (status == 0) {
			System.out.println("\nThis board has no possible solution.\n");
		} else if (status == 1) {
			System.out.println("\nThis board has no additional solutions.\n");
		} else if (status == 2) {
			viewListener.printBoard();
		}
	}
	/**
	 * Quit the program.
	 */
	public void quit() {
		viewListener.quit();
		System.exit(0);
	}
	/**
	 * Outputs a usage message for board input format.
	 */
	private static void usage() {
		System.out.println("Board data is supplied with 81 digits in 0-9.");
		System.out.println("Numbers 1-9 represent initial filled in cells.");
		System.out.println("0's represent initially blank cells.");
	}
}
