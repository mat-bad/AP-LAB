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

  static Scanner scanner = new Scanner(System.in);

  static void getInput() {
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
        System.out.print(board[row][column] + " ");
      }
      System.out.println();
    }
  }

  public static void main(String[] args) {
    getInput();
    if(solve(board)) {
      System.out.print("Yes\n");
      printBoard(board);  
    } else {
      System.out.print("No solution\n");
    }
  }
}
