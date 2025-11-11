package sudoku;

import java.util.List;

/**
 * Represents a 9x9 Sudoku board.
 */
public class SudokuBoard {
    public static final int SIZE = 9;
    private final int[][] grid;

    public SudokuBoard() {
        this.grid = new int[SIZE][SIZE];
    }

    public SudokuBoard(int[][] values) {
        if (values.length != SIZE) {
            throw new IllegalArgumentException("El tablero debe tener 9 filas.");
        }
        this.grid = new int[SIZE][SIZE];
        for (int row = 0; row < SIZE; row++) {
            if (values[row].length != SIZE) {
                throw new IllegalArgumentException("Cada fila debe tener 9 columnas.");
            }
            System.arraycopy(values[row], 0, this.grid[row], 0, SIZE);
        }
    }

    public int getCell(int row, int col) {
        validatePosition(row, col);
        return grid[row][col];
    }

    public void setCell(int row, int col, int value) {
        validatePosition(row, col);
        if (value < 0 || value > SIZE) {
            throw new IllegalArgumentException("Los valores deben estar entre 0 y 9.");
        }
        grid[row][col] = value;
    }

    public boolean isEmpty(int row, int col) {
        validatePosition(row, col);
        return grid[row][col] == 0;
    }

    public int[][] copyGrid() {
        int[][] copy = new int[SIZE][SIZE];
        for (int row = 0; row < SIZE; row++) {
            System.arraycopy(grid[row], 0, copy[row], 0, SIZE);
        }
        return copy;
    }

    private void validatePosition(int row, int col) {
        if (row < 0 || row >= SIZE || col < 0 || col >= SIZE) {
            throw new IndexOutOfBoundsException("Posición fuera de rango: (" + row + ", " + col + ")");
        }
    }

    public void print() {
        for (int row = 0; row < SIZE; row++) {
            if (row % 3 == 0 && row != 0) {
                System.out.println("------+-------+------");
            }
            for (int col = 0; col < SIZE; col++) {
                if (col % 3 == 0 && col != 0) {
                    System.out.print("| ");
                }
                System.out.print(grid[row][col]);
                if (col < SIZE - 1) {
                    System.out.print(" ");
                }
            }
            System.out.println();
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {
                sb.append(grid[row][col]);
                if (col < SIZE - 1) {
                    sb.append(' ');
                }
            }
            if (row < SIZE - 1) {
                sb.append(System.lineSeparator());
            }
        }
        return sb.toString();
    }

    public static SudokuBoard fromLines(List<String> lines) {
        if (lines.size() != SIZE) {
            throw new IllegalArgumentException("Se esperaban 9 líneas para el tablero.");
        }
        int[][] values = new int[SIZE][SIZE];
        for (int row = 0; row < SIZE; row++) {
            String line = lines.get(row).trim();
            if (line.isEmpty()) {
                throw new IllegalArgumentException("La línea " + (row + 1) + " está vacía.");
            }
            String[] tokens = line.split("\\s+");
            if (tokens.length != SIZE) {
                throw new IllegalArgumentException("La línea " + (row + 1) + " debe contener 9 números.");
            }
            for (int col = 0; col < SIZE; col++) {
                try {
                    int value = Integer.parseInt(tokens[col]);
                    if (value < 0 || value > SIZE) {
                        throw new IllegalArgumentException("Los valores deben estar entre 0 y 9.");
                    }
                    values[row][col] = value;
                } catch (NumberFormatException e) {
                    throw new IllegalArgumentException(
                        "Entrada inválida en la línea " + (row + 1) + ": '" + tokens[col] + "'.");
                }
            }
        }
        return new SudokuBoard(values);
    }
}
