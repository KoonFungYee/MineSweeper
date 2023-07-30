package com.demo.minesweeper.controller;

import com.demo.minesweeper.model.MineSweeper;
import com.demo.minesweeper.service.MineSweeperService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.InputMismatchException;
import java.util.Scanner;

public class MineSweeperController {

    @Autowired
    private static MineSweeper mineSweeper;


    public static void startMineSweeper() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Welcome to Minesweeper!\n");
            try {
                System.out.print("Enter the size of the grid (e.g. 4 for a 4x4 grid), maximum is 6: ");
                int gridSize = scanner.nextInt();

                if (gridSize < 3 || gridSize > 6) {
                    System.out.println("Minimum size of grid is 3 and maximum size of grid is 6.");
                    continue;
                }

                System.out.print("Enter the number of mines to place on the grid (maximum is " + (int) (0.35 * gridSize * gridSize) + "): ");
                int numMines = scanner.nextInt();
                int maxMines = (int) (0.35 * gridSize * gridSize);
                if (numMines <= 0 || numMines > maxMines) {
                    System.out.println("There must be at least 1 mine and at most " + maxMines + " mines.");
                    continue;
                }

                mineSweeper = new MineSweeper(gridSize, numMines);
                MineSweeperService mineSweeperService = new MineSweeperService();
                staticMineSweeperService(mineSweeperService, mineSweeper);


                System.out.print("Press Enter to play again, or 'Q' to quit: ");
                scanner.nextLine();
                String playAgain = scanner.nextLine().trim();
                if (playAgain.equalsIgnoreCase("Q")) {
                    break;
                }
            } catch (InputMismatchException e) {
                System.out.println("Incorrect input.");
                scanner.nextLine();
            }
        }
        scanner.close();
    }

    private static void staticMineSweeperService(MineSweeperService mineSweeperService, MineSweeper mineSweeper) {
        mineSweeperService.playGame(mineSweeper);
    }
}
