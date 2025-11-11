package sudoku;

/**
 * Backtracking Sudoku solver.
 */
public class SudokuSolver {
    private long recursiveCalls;

    public long getRecursiveCalls() {
        return recursiveCalls;
    }

    public boolean solve(SudokuBoard board) {
        recursiveCalls = 0;
        return backtrack(board);
    }

    private boolean backtrack(SudokuBoard board) {
        recursiveCalls++;
        int[] emptyCell = findEmptyCell(board);
        if (emptyCell == null) {
            return true; // No hay celdas vacías: tablero resuelto.
        }
        int row = emptyCell[0];
        int col = emptyCell[1];

        for (int value = 1; value <= SudokuBoard.SIZE; value++) {
            if (isValid(board, row, col, value)) {
                board.setCell(row, col, value);
                if (backtrack(board)) {
                    return true;
                }
                board.setCell(row, col, 0); // backtrack
            }
        }
        return false; // Ningún valor válido encontrado.
    }

    private int[] findEmptyCell(SudokuBoard board) {
        for (int row = 0; row < SudokuBoard.SIZE; row++) {
            for (int col = 0; col < SudokuBoard.SIZE; col++) {
                if (board.isEmpty(row, col)) {
                    return new int[] { row, col };
                }
            }
        }
        return null;
    }

    private boolean isValid(SudokuBoard board, int row, int col, int value) {
        for (int i = 0; i < SudokuBoard.SIZE; i++) {
            if (board.getCell(row, i) == value || board.getCell(i, col) == value) {
                return false;
            }
        }

        int subgridRowStart = (row / 3) * 3;
        int subgridColStart = (col / 3) * 3;
        for (int r = 0; r < 3; r++) {
            for (int c = 0; c < 3; c++) {
                if (board.getCell(subgridRowStart + r, subgridColStart + c) == value) {
                    return false;
                }
            }
        }
        return true;
    }
}
