package com.demo.minesweeper.model;

import lombok.Data;

import java.util.Arrays;
import java.util.Random;

@Data
public class MineSweeper {
    private char[][] grid;
    private int gridSize;
    private int numMines;
    private int uncoveredCount;

    public MineSweeper(int gridSize, int numMines) {
        this.gridSize = gridSize;
        this.numMines = numMines;
        this.uncoveredCount = 0;
        this.grid = new char[gridSize][gridSize];
        initializeGrid();
        placeMines();
    }

    private void initializeGrid() {
        for (int i = 0; i < gridSize; i++) {
            Arrays.fill(grid[i], '_');
        }
    }

    private void placeMines() {
        Random random = new Random();
        int minesPlaced = 0;

        while (minesPlaced < numMines) {
            int row = random.nextInt(gridSize);
            int col = random.nextInt(gridSize);

            if (grid[row][col] != 'X') {
                grid[row][col] = 'X';
                minesPlaced++;
            }
        }
    }
}
