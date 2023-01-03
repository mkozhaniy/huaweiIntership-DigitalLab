package com.company;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.util.Scanner;

/**
 * @author Кожуховский Максим
 * mail: m.kozhukhovskii@g.nsu.ru*/

public class Main {

    public static void main(String[] args) throws IOException {
        Scanner in = new Scanner(Path.of("tests.txt"), StandardCharsets.UTF_8);
        PrintWriter result = new PrintWriter("out.txt", StandardCharsets.UTF_8);

        int a1, a2, b1, b2;

        while (in.hasNextInt()) {
            a1 = in.nextInt();
            a2 = in.nextInt();
            String[][] pattern = new String[a1][a2];
            for (int i = 0; i < a1; ++i) {
                for (int j = 0; j < a2; ++j) {
                    pattern[i][j] = in.next();
                }
            }

            b1 = in.nextInt();
            b2 = in.nextInt();
            String[][] matrix = new String[b1][b2];
            for (int i = 0; i < b1; ++i) {
                for (int j = 0; j < b2; ++j) {
                    matrix[i][j] = in.next();
                }
            }

            recognize(matrix, pattern);

            for (var i : matrix) {
                for (var j : i) result.write(j + " ");

                result.write("\n");
            }
            result.write("\n");
            result.flush();
        }
    }

    /**
     * Метод сравнения паттерна и матрицы
     * @param pattern паттерн
     * @param matrix матрица
     * @param row строка, с которой начать сравнение с паттерном
     * @param col коллонка
     * @return true - если паттерн обнаружен*/
    public static boolean equal(String[][] pattern, String[][] matrix, int row, int col) {
        for (int i = row; i < row + pattern.length; ++i) {
            for (int j = col; j < col + pattern[0].length; ++j) {
                if (!pattern[i - row][j - col].equals(matrix[i][j])) return false;
            }
        }
        return true;
    }

    /**
     * Метод замены найденого паттерна в матрице, на другой паттерн
     * @param matrix
     * @param row
     * @param col
     * @param a1 количество строк в паттерне
     * @param a2 количество столбцов в паттерне*/
    public static void swap(String[][] matrix, int row, int col, int a1, int a2) {
        for (int i = row; i < row + a1; ++i) {
            for (int j = col; j < col + a2; ++j) {
                if (matrix[i][j].equals("0")) matrix[i][j] = "*";
                else matrix[i][j] = "2";
            }
        }
    }

    /**
     * Метод, объедянияющий распознование и замену паттерна в матрице
     * @param matrix
     * @param pattern*/
    public static void recognize(String[][] matrix, String[][] pattern) {
        for (int i = 0; i < matrix.length; ++i) {
            if (i + pattern.length > matrix.length) continue;
            for (int j = 0; j < matrix[0].length; ++j) {
                if (j + pattern[0].length > matrix[0].length) continue;
                if (equal(pattern, matrix, i, j)) swap(matrix,
                        i, j, pattern.length, pattern[0].length);
            }
        }
    }
}
