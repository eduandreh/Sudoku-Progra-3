package sudoku;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * Entry point for the Sudoku solver program.
 */
public class Main {
    public static void main(String[] args) {
        SudokuBoard board = null;
        try {
            if (args.length > 0) {
                board = SudokuBoardLoader.fromFile(args[0]);
                System.out.println("Tablero cargado desde archivo: " + args[0]);
            } else {
                Path defaultPath = Path.of("sudoku.txt");
                if (Files.exists(defaultPath)) {
                    board = SudokuBoardLoader.fromFile(defaultPath.toString());
                    System.out.println("Tablero cargado desde sudoku.txt");
                } else {
                    board = SudokuBoardLoader.fromConsole();
                }
            }
        } catch (IOException | IllegalArgumentException e) {
            System.err.println("Error al cargar el tablero: " + e.getMessage());
            System.exit(1);
        }

        SudokuSolver solver = new SudokuSolver();
        long startTime = System.nanoTime();
        boolean solved = solver.solve(board);
        long endTime = System.nanoTime();

        if (solved) {
            System.out.println("Tablero resuelto:");
            board.print();
            long elapsedNanos = endTime - startTime;
            double elapsedMillis = elapsedNanos / 1_000_000.0;
            System.out.println();
            System.out.println("Estadísticas:");
            System.out.println(" - Tiempo de ejecución: " + String.format("%.3f ms", elapsedMillis));
            System.out.println(" - Llamadas recursivas: " + solver.getRecursiveCalls());
        } else {
            System.out.println("No se encontró una solución para el tablero proporcionado.");
        }
    }
}
