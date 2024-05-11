import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.stream.IntStream;

public class SudokuSolver {
  
  static int BOARD_START_INDEX = 0;
  static int BOARD_SIZE = 9;
  static int NO_VALUE = 0;
  static int MIN_VALUE = 1;
  static int MAX_VALUE = 9;
  static int SUBSECTION_SIZE = 3;

  static int[][] board = new int[BOARD_SIZE][BOARD_SIZE];
  static Scanner scanner;
  static FileWriter fileWriter;
  static FileReader fileReader;


  static void init() {
    try {
      fileReader = new FileReader("input.txt");
    } catch (FileNotFoundException e) {
      throw new RuntimeException("no input file");
    }
    scanner = new Scanner(fileReader);
    try {
      fileWriter = new FileWriter("output.txt");
    } catch (IOException e) {
      throw new RuntimeException("output file exception");
    }
  }

  static void endProgram() {
    try {
      fileWriter.close();
      scanner.close();
      fileReader.close();
    } catch (IOException e) {
      throw new RuntimeException("file closing exception");
    }
  }

  static void output(String s) {
    try {
      fileWriter.write(s);
    } catch (IOException e) {
      return;
    } 
  }

  static void outputln(String s) {
    output(s+"\n");
  }

  static void outputln() {
    outputln("");
  }

  static void getInput() throws FileNotFoundException {    
    for(int i=0; i<9; i++) {
      for(int j=0; j<9; j++) {
        board[i][j] = scanner.nextInt();
      }
    }
    
  }

  static void set(int boardSize) { 
      BOARD_SIZE = boardSize;
      board = new int[BOARD_SIZE][BOARD_SIZE];
  } 

  private static boolean checkConstraint(int[][] board, int row, boolean[] constraint, int column) {
    if (board[row][column] != NO_VALUE) {
        if (!constraint[board[row][column] - 1]) {
            constraint[board[row][column] - 1] = true;
        } else {
            return false;
        }
    }
    return true;
  }

  private static boolean subsectionConstraint(int[][] board, int row, int column) {
    boolean[] constraint = new boolean[BOARD_SIZE];
    int subsectionRowStart = (row / SUBSECTION_SIZE) * SUBSECTION_SIZE;
    int subsectionRowEnd = subsectionRowStart + SUBSECTION_SIZE;

    int subsectionColumnStart = (column / SUBSECTION_SIZE) * SUBSECTION_SIZE;
    int subsectionColumnEnd = subsectionColumnStart + SUBSECTION_SIZE;

    for (int r = subsectionRowStart; r < subsectionRowEnd; r++) {
        for (int c = subsectionColumnStart; c < subsectionColumnEnd; c++) {
            if (!checkConstraint(board, r, constraint, c)) return false;
        }
    }
    return true;
  }
  
  private static boolean columnConstraint(int[][] board, int column) {
    boolean[] constraint = new boolean[BOARD_SIZE];
    return IntStream.range(BOARD_START_INDEX, BOARD_SIZE)
      .allMatch(row -> checkConstraint(board, row, constraint, column));
  }

  private static boolean rowConstraint(int[][] board, int row) {
    boolean[] constraint = new boolean[BOARD_SIZE];
    return IntStream.range(BOARD_START_INDEX, BOARD_SIZE)
      .allMatch(column -> checkConstraint(board, row, constraint, column));
  }

  private static boolean isValid(int[][] board, int row, int column) {
    return (rowConstraint(board, row)
      && columnConstraint(board, column) 
      && subsectionConstraint(board, row, column));
  }

  private static boolean solve(int[][] board) {
    for (int row = BOARD_START_INDEX; row < BOARD_SIZE; row++) {
        for (int column = BOARD_START_INDEX; column < BOARD_SIZE; column++) {
            if (board[row][column] == NO_VALUE) {
                for (int k = MIN_VALUE; k <= MAX_VALUE; k++) {
                    board[row][column] = k;
                    if (isValid(board, row, column) && solve(board)) {
                        return true;
                    }
                    board[row][column] = NO_VALUE;
                }
                return false;
            }
        }
    }
    return true;
  }

  private static void printBoard(int[][] board) {
    for (int row = BOARD_START_INDEX; row < BOARD_SIZE; row++) {
      for (int column = BOARD_START_INDEX; column < BOARD_SIZE; column++) {
        output(board[row][column] + " ");
      }
      outputln();
    }
  }

  public static void main(String[] args) {
    
    try {
      init();
    } catch (RuntimeException e) {
      return;
    }
    
    if(solve(board)) {
      output("Yes\n");
      printBoard(board);  
    } else {
      output("No solution\n");
    }
    endProgram();
  }
}
/*
8 0 0 0 0 0 0 0 0 
0 0 3 6 0 0 0 0 0 
0 7 0 0 9 0 2 0 0 
0 5 0 0 0 7 0 0 0 
0 0 0 0 4 5 7 0 0
0 0 0 1 0 0 0 3 0 
0 0 1 0 0 0 0 6 8 
0 0 8 5 0 0 0 1 0 
0 9 0 0 0 0 4 0 0

0 2 0 5 0 1 0 9 0
8 0 0 2 0 3 0 0 6
0 3 0 0 6 0 0 7 0
0 0 1 0 0 0 6 0 0
5 4 0 0 0 0 0 1 9
0 0 3 0 0 0 7 0 0
0 9 0 0 3 0 0 8 0
2 0 0 8 0 4 0 0 7
0 1 0 9 0 7 0 6 0

*/
