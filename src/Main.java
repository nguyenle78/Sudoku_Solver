public class Main {
    private static final int SIZE = 9;

    public static void main(String[] args) {
        int[][] board = {
                {0, 0, 0, 9, 1, 0, 0, 0, 0},
                {9, 0, 0, 6, 0, 0, 3, 0, 0},
                {0, 8, 3, 0, 5, 0, 0, 7, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 5},
                {0, 0, 0, 0, 0, 0, 0, 0, 0},
                {2, 0, 0, 0, 0, 1, 4, 0, 7},
                {1, 0, 2, 0, 7, 0, 6, 0, 0},
                {0, 0, 4, 0, 0, 0, 2, 9, 0},
                {0, 0, 0, 0, 6, 0, 0, 0, 0}
        };
        printBoard(board);
        System.out.println();
        if (solveBoard(board)) {
            System.out.println("Solved board");
            printBoard(board);
        } else {
            System.out.println("Unsolvable board");
        }

    }

    private static void printBoard(int[][] board) {
        for (int row = 0; row < SIZE; row++) {
            if (row % 3 == 0) {
                System.out.println("-------------------");
            }
            for (int column = 0; column < SIZE; column++) {
                if (column % 3 == 0 && column != 0) {
                    System.out.print("|");
                }
                System.out.print(board[row][column] + " ");
            }
            System.out.println();
        }
    }

    private static boolean isInRow(int[][] board, int row, int number) {
        for (int i = 0; i < SIZE; i++) {
            if (board[row][i] == number) {
                return true;
            }
        }
        return false;
    }

    private static boolean isInColumn(int[][] board, int column, int number) {
        for (int i = 0; i < SIZE; i++) {
            if (board[i][column] == number) {
                return true;
            }
        }
        return false;
    }

    private static boolean isInBox(int[][] board, int row, int column, int number) {
        // Coordinate of the top left of 3x3 box
        int boxRow = row - (row % 3);
        int boxColumn = column - (column % 3);
        //Nested loop to search inside the 3x3 box
        for (int i = boxRow; i < boxRow + 3; i++) {
            for (int j = boxColumn; j < boxColumn + 3; j++) {
                if (number == board[i][j]) {
                    return true;
                }
            }
        }
        return false;
    }

    private static boolean isPossibleValue(int[][] board, int number, int row, int column) {
        return !isInBox(board, row, column, number) &&
                !isInColumn(board, column, number) &&
                !isInRow(board, row, number);
    }


    // Main algorithm ò the solver
    private static boolean solveBoard(int[][] board) {
        for (int row = 0; row < SIZE; row++) {
            for (int column = 0; column < SIZE; column++) {
                if (board[row][column] == 0) {
                    for (int numberToTry = 1; numberToTry <= SIZE; numberToTry++) {
                        if (isPossibleValue(board, numberToTry, row, column)) {
                            board[row][column] = numberToTry;
                            // Recursively solve the board again
                            if (solveBoard(board)) {
                                return true;
                            } else {
                                //set number or that position back to 0
                                board[row][column] = 0;
                            }
                        }
                    }
                    return false;
                }
            }
        }
        return true;
    }
}
