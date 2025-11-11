package sudoku;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Utility class for loading Sudoku boards from different sources.
 */
public final class SudokuBoardLoader {
    private SudokuBoardLoader() {
    }

    public static SudokuBoard fromFile(String filePath) throws IOException {
        Path path = Path.of(filePath);
        List<String> lines = Files.readAllLines(path);
        return SudokuBoard.fromLines(lines);
    }

    public static SudokuBoard fromConsole() {
        Scanner scanner = new Scanner(System.in);
        List<String> lines = new ArrayList<>();
        System.out.println("Ingrese 9 líneas con 9 números separados por espacios (use 0 para celdas vacías):");
        while (lines.size() < SudokuBoard.SIZE) {
            System.out.print("Fila " + (lines.size() + 1) + ": ");
            String line = scanner.nextLine();
            if (line.trim().isEmpty()) {
                System.out.println("La línea no puede estar vacía. Intente nuevamente.");
                continue;
            }
            lines.add(line);
        }
        return SudokuBoard.fromLines(lines);
    }
}
