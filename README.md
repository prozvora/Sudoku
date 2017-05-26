# Sudoku
A 9x9 Sudoku solver which uses a backtracking approach

## Usage

**Input**
  ```
  java Sudoku <viewType>
  ```
  \<viewType\> is a choice of UI. Valid choices are "text" and "gui".
  
 For text, the Sudoku program will display program functions in standard output for the user.
 For gui, the Sudoku program will display program functions in a graphical interface.
 
 **Example Run - text**
 ```
 Choose an option:
1. Supply a new Sudoku board.
2. Reset the current board to its initial state.
3. Solve the board and display its solution, or find additional solutions.
4. Quit program.
Enter your selction (1-4): 1
Board data is supplied with 81 digits in 0-9.
Numbers 1-9 represent initial filled in cells.
0's represent initially blank cells.
Enter the board: 400300000006008000000000010000500900800006000702000000001027005030000409000000000
Choose an option:
1. Supply a new Sudoku board.
2. Reset the current board to its initial state.
3. Solve the board and display its solution, or find additional solutions.
4. Quit program.
Enter your selction (1-4): 3

4 1 5 3 6 2 7 9 8 
2 7 6 1 9 8 3 5 4 
3 8 9 7 4 5 2 1 6 
1 6 3 5 7 4 9 8 2 
8 9 4 2 1 6 5 7 3 
7 5 2 8 3 9 6 4 1 
6 4 1 9 2 7 8 3 5 
5 3 7 6 8 1 4 2 9 
9 2 8 4 5 3 1 6 7 

Choose an option:
1. Supply a new Sudoku board.
2. Reset the current board to its initial state.
3. Solve the board and display its solution, or find additional solutions.
4. Quit program.
Enter your selction (1-4): 3

4 1 5 3 6 2 7 9 8 
2 7 6 1 9 8 3 5 4 
3 8 9 7 4 5 2 1 6 
1 6 3 5 7 4 9 8 2 
8 9 4 2 1 6 5 7 3 
7 5 2 9 8 3 6 4 1 
9 4 1 6 2 7 8 3 5 
6 3 7 8 5 1 4 2 9 
5 2 8 4 3 9 1 6 7 

Choose an option:
1. Supply a new Sudoku board.
2. Reset the current board to its initial state.
3. Solve the board and display its solution, or find additional solutions.
4. Quit program.
Enter your selction (1-4): 4
```
