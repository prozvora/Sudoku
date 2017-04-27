/**
 * Node defines the structure of each cell in the Sudoku board.
 * Initial supplied cells should be marked as not variable.
 * 
 * @author Pavel Rozvora
 * @version 2017-04-27
 */
public class Node {
	private int value;
	private boolean variable;
	/**
	 * Constructor.
	 * 
	 * @param value		The integer value of the cell
	 * @param variable	true if this cell was initially empty, false otherwise
	 */
	public Node(int value, boolean variable) {
		this.value = value;
		this.variable = variable;
	}
	/**
	 * Set the value for this cell.
	 * @param value 	The new value
	 */
	public void setVal(int value) {
		this.value = value;
	}
	/**
	 * Get the value for this cell.
	 * 
	 * @return	The cell's value
	 */
	public int getVal() {
		return value;
	}
	/**
	 * Return whether or not this cell's value was initially supplied.
	 * @return true if the value was supplied, false otherwise
	 */
	public boolean getVar() {
		return variable;
	}
}
