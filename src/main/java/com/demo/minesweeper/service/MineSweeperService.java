package com.demo.minesweeper.service;

import com.demo.minesweeper.model.MineSweeper;
import org.springframework.stereotype.Service;

import java.util.InputMismatchException;
import java.util.Scanner;

@Service
public class MineSweeperService {
    public void playGame(MineSweeper mineSweeper) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            displayGrid(mineSweeper);

            System.out.print("Select a square to reveal (e.g. A1): ");
            try {
                String input = scanner.nextLine().trim();

                if (input.length() != 2 || !Character.isLetter(input.charAt(0)) || !Character.isDigit(input.charAt(1))) {
                    System.out.println("Incorrect input.");
                    continue;
                }

                int row = input.charAt(0) - 'A';
                int col = input.charAt(1) - '1';

                if (row >= mineSweeper.getGridSize() || col >= mineSweeper.getGridSize()) {
                    System.out.println("Invalid selection.");
                    continue;
                }

                if (mineSweeper.getGrid()[row][col] == 'X') {
                    displayGrid(mineSweeper);
                    System.out.println("Oh no, you detonated a mine! Game over.");
                    break;
                }

                if (mineSweeper.getGrid()[row][col] == '_') {
                    uncoverSquare(mineSweeper, row, col);
                }

                if (mineSweeper.getUncoveredCount() == mineSweeper.getGridSize() * mineSweeper.getGridSize() - mineSweeper.getNumMines()) {
                    displayGrid(mineSweeper);
                    System.out.println("Congratulations, you have won the game!");
                    break;
                }
            } catch (InputMismatchException | ArrayIndexOutOfBoundsException e) {
                System.out.println("Incorrect input.");
                scanner.nextLine();
            }
        }
    }

    private void uncoverSquare(MineSweeper mineSweeper, int row, int col) {
        if (row < 0 || row >= mineSweeper.getGridSize() || col < 0 || col >= mineSweeper.getGridSize() || mineSweeper.getGrid()[row][col] != '_') {
            return;
        }

        int adjacentMines = countAdjacentMines(mineSweeper, row, col);
        mineSweeper.getGrid()[row][col] = (char) (adjacentMines + '0');
        mineSweeper.setUncoveredCount(mineSweeper.getUncoveredCount() + 1);

        if (adjacentMines == 0) {
            uncoverSquare(mineSweeper, row - 1, col - 1);
            uncoverSquare(mineSweeper, row - 1, col);
            uncoverSquare(mineSweeper, row - 1, col + 1);
            uncoverSquare(mineSweeper, row, col - 1);
            uncoverSquare(mineSweeper, row, col + 1);
            uncoverSquare(mineSweeper, row + 1, col - 1);
            uncoverSquare(mineSweeper, row + 1, col);
            uncoverSquare(mineSweeper, row + 1, col + 1);
        }
    }

    private int countAdjacentMines(MineSweeper mineSweeper, int row, int col) {
        int count = 0;
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                int newRow = row + i;
                int newCol = col + j;
                if (newRow >= 0 && newRow < mineSweeper.getGridSize() && newCol >= 0 && newCol < mineSweeper.getGridSize() && mineSweeper.getGrid()[newRow][newCol] == 'X') {
                    count++;
                }
            }
        }
        return count;
    }

    private void displayGrid(MineSweeper mineSweeper) {
        System.out.print("  ");
        for (int i = 0; i < mineSweeper.getGridSize(); i++) {
            System.out.print(i + 1 + " ");
        }
        System.out.println();
        for (int i = 0; i < mineSweeper.getGridSize(); i++) {
            System.out.print((char) ('A' + i) + " ");
            for (int j = 0; j < mineSweeper.getGridSize(); j++) {
                if (String.valueOf(mineSweeper.getGrid()[i][j]).equals("X")) {
                    System.out.print("_ ");
                } else {
                    System.out.print(mineSweeper.getGrid()[i][j] + " ");
                }
            }
            System.out.println();
        }
        System.out.println();
    }
}
