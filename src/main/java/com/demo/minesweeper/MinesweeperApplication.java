package com.demo.minesweeper;

import com.demo.minesweeper.controller.MineSweeperController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MinesweeperApplication {

	public static void main(String[] args) {
		SpringApplication.run(MinesweeperApplication.class, args);
		MineSweeperController.startMineSweeper();
	}
}
