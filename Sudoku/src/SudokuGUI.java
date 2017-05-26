import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dialog.ModalityType;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
/**
 * SudokuGUI contains the Graphical UI for the Sudoku program.
 * 
 * @author Pavel Rozvora
 * @version 2017-05-26
 */
public class SudokuGUI implements ModelListener {
	private JFrame frame;
	private JButton newBoardButton;
	private JButton resetButton;
	private JButton quitButton;
	private JButton solveButton;
	private JButton dOkButton;
	private JButton dCancelButton;
	private JTextField messages;
	private JDialog newBoardDialog;
	private JLabel[][] board = new JLabel[9][9];
	private JPanel[][] newBoard = new JPanel[9][9];
	private JTextField[][] newBoardText = new JTextField[9][9];
	
	private ViewListener viewListener;
	private static final int GAP = 10;
	private static final int MIN_SIZE = 50;
	private static final int FONT_SIZE = 24;
	/**
	 * Constructor.
	 */
	public SudokuGUI() {
		createUI();
	}
	/**
	 * Hidden GUI implementation.
	 */
	private void createUI() {
		//main window
		frame = new JFrame("Sudoku");
		JPanel pMaster = new JPanel();
		pMaster.setLayout(new BorderLayout());
		frame.add(pMaster);
		//message field for the user
		messages = new JTextField();
		pMaster.add(messages, BorderLayout.SOUTH);
		messages.setEditable(false);
		messages.setText("Begin by supplying a new Sudoku board.");
		//main panel for program functions
		JPanel pButtons = new JPanel();
		pButtons.setLayout(new BoxLayout(pButtons, BoxLayout.Y_AXIS));
		pMaster.add(pButtons, BorderLayout.EAST);
		pMaster.setBorder(BorderFactory.createEmptyBorder(GAP, GAP, GAP, GAP));
		
		newBoardButton = new JButton("Supply New Board");
		newBoardButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		pButtons.add(Box.createVerticalGlue());
		pButtons.add(newBoardButton);
		
		resetButton = new JButton("Reset Board");
		resetButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		pButtons.add(resetButton);
		
		solveButton = new JButton("Solve Board");
		solveButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		pButtons.add(solveButton);
		
		quitButton = new JButton("Quit Program");
		quitButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		pButtons.add(quitButton);
		pButtons.add(Box.createVerticalGlue());
		
		JPanel pBoard = new JPanel();
		pBoard.setLayout(new GridLayout(9,9));
		pMaster.add(pBoard, BorderLayout.CENTER);
		//board representation
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				board[i][j] = new JLabel("", JLabel.CENTER);
				board[i][j].setPreferredSize(
						new Dimension(MIN_SIZE, MIN_SIZE));
				board[i][j].setBorder(
						BorderFactory.createLineBorder(Color.black));
				board[i][j].setFont(
						new Font(board[i][j].getFont().getName(),
								Font.PLAIN, 
								FONT_SIZE));
				pBoard.add(board[i][j]);
			}
		}
		//create dialog for new board input
		JPanel pInput = new JPanel();
		pInput.setLayout(new BorderLayout());
		JPanel pNewBoard = new JPanel();
		pNewBoard.setLayout(new GridLayout(9,9));
		pInput.add(pNewBoard, BorderLayout.CENTER);
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				newBoard[i][j] = new JPanel();
				newBoard[i][j].setPreferredSize(
						new Dimension(MIN_SIZE, MIN_SIZE));
				newBoard[i][j].setBorder(
						BorderFactory.createLineBorder(Color.black));
				newBoardText[i][j] = new JTextField(1);
				newBoardText[i][j].setFont(new Font(
						newBoardText[i][j].getFont().getName(),
						Font.PLAIN,
						FONT_SIZE));
				newBoard[i][j].add(newBoardText[i][j]);
				pNewBoard.add(newBoard[i][j]);
			}
		}
		
		JTextField dialogMsg = new JTextField(
				"Input the new board. Erroneous input will be ignored.");
		pInput.add(dialogMsg, BorderLayout.NORTH);
		dialogMsg.setEditable(false);
		
		JPanel pDialogButtons = new JPanel();
		pDialogButtons.setLayout(
				new BoxLayout(pDialogButtons, BoxLayout.X_AXIS));
		dOkButton = new JButton("OK");
		dOkButton.setAlignmentY(Component.CENTER_ALIGNMENT);
		dCancelButton = new JButton("Cancel");
		dCancelButton.setAlignmentY(Component.CENTER_ALIGNMENT);
		pInput.add(pDialogButtons, BorderLayout.SOUTH);
		pDialogButtons.add(Box.createHorizontalGlue());
		pDialogButtons.add(dOkButton);
		pDialogButtons.add(dCancelButton);
		pDialogButtons.add(Box.createHorizontalGlue());
		
		
		newBoardDialog = new JDialog();
		newBoardDialog.add(pInput);
		newBoardDialog.pack();
        newBoardDialog.setModalityType(ModalityType.APPLICATION_MODAL);
		
		frame.pack();
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		//listeners
		frame.addWindowListener(new java.awt.event.WindowAdapter() {
			/**
			 * This listener ensures that both the model and view are closed.
			 */
		    public void windowClosing(java.awt.event.WindowEvent windowEvent) {
		        quit();
		    }
		});
		newBoardButton.addActionListener(new ActionListener() {
            /**
             * This listener displays the new board dialog.
             */
            public void actionPerformed (ActionEvent e) {
            	newBoardDialog.setVisible(true);
            }
        });
		dOkButton.addActionListener(new ActionListener() {
            /**
             * This listener gathers the new board and closes the dialog.
             */
            public void actionPerformed (ActionEvent e) {
            	String boardString = "";
            	for (int i = 0; i < 9; i++) {
        			for (int j = 0; j < 9; j++) {
        				switch (newBoardText[i][j].getText()) {
        				case "1":	boardString = boardString.concat("1");
        							break;
        				case "2":	boardString = boardString.concat("2");
									break;
        				case "3":	boardString = boardString.concat("3");
									break;
        				case "4":	boardString = boardString.concat("4");
									break;
        				case "5":	boardString = boardString.concat("5");
									break;
        				case "6":	boardString = boardString.concat("6");
        							break;
        				case "7":	boardString = boardString.concat("7");
									break;
        				case "8":	boardString = boardString.concat("8");
        							break;
        				case "9":	boardString = boardString.concat("9");
									break;
        				default:	boardString = boardString.concat("0");
									break;
        				}
        				newBoardText[i][j].setText("");
        			}
        		}
            	newBoardDialog.setVisible(false);
            	newBoard(boardString);
            }
        });
		dCancelButton.addActionListener(new ActionListener() {
            /**
             * This listener ignores the new board and closes the dialog.
             */
            public void actionPerformed (ActionEvent e) {
            	for (int i = 0; i < 9; i++) {
    				for (int j = 0; j < 9; j++) {
    					newBoardText[i][j].setText("");
    				}
    			}
            	newBoardDialog.setVisible(false);
            	
            }
        });
		resetButton.addActionListener(new ActionListener() {
            /**
             * This listener resets the board. An error message is displayed if
             * no board is supplied.
             */
            public void actionPerformed (ActionEvent e) {
            	try {
            		resetBoard();
            	} catch (NullPointerException exc) {
            		messages.setText("Please supply a board first.");
            	}
            }
        });
		solveButton.addActionListener(new ActionListener() {
            /**
             * This listener solves the board. An error message is displayed if
             * no board is supplied.
             */
            public void actionPerformed (ActionEvent e) {
            	try {
            		solve();
            	} catch (NullPointerException exc) {
            		messages.setText("Please supply a board first.");
            	}
            }
        });
		quitButton.addActionListener(new ActionListener() {
            /**
             * This listener quits the program.
             */
            public void actionPerformed (ActionEvent e) {
                quit();
            }
        });
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
	 * Supply a Sudoku board to the model.
	 * 
	 * @param input The Sudoku board.
	 */
	public void newBoard(String input) {
		viewListener.newBoard(input);
		displayBoard(2);
	}
	/**
	 * Reset the board to its initial supplied state.
	 */
	public void resetBoard() {
		viewListener.resetBoard();
		displayBoard(2);
	}
	/**
	 * Find a solution to the board, if it exists.
	 */
	public void solve() {
		viewListener.solve();
	}
	/**
	 * Display the solved board or a message if it has no solution.
	 * If status == 0, the board has no solution.
	 * If status == 1, the board has no additional solutions.
	 * If status == 2, the board has been solved.
	 * 
	 * @param status A value which indicates the solved status of the board
	 */
	public void displayBoard(int status) {
		display(status);
	}
	/**
	 * Hidden display implementation.
	 * 
	 * @param status A value which indicates the solved status of the board
	 */
	private void display(int status) {
		if (status == 0) {
			messages.setText("This board has no solution.");
			messages.setForeground(Color.red);
			frame.repaint();
		} else if (status == 1) {
			messages.setText("This board has no additional solutions.");
			messages.setForeground(Color.red);
			frame.repaint();
		} else if (status == 2) {
			String boardString = viewListener.boardToString();
			for (int i = 0; i < 9; i++) {
				for (int j = 0; j < 9; j++) {
					if (boardString.charAt(9*i+j) > '0') {
						board[i][j].setText(Character.toString(
								boardString.charAt(9*i+j)));
					} else {
						board[i][j].setText("");
					}
				}
			}
			messages.setText(
					"Solve this board, or search for additional solutions.");
			messages.setForeground(Color.black);
			frame.repaint();
		}
	}
	/**
	 * Quit the program.
	 */
	public void quit() {
		viewListener.quit();
		System.exit(0);
	}
}
