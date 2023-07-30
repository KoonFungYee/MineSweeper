# Mine Sweeper App

### Description

* Download the minesweeper.zip and unzip it.
* Run the application on MinesweeperApplication.java
* Size of grid must in between 3 to 6.
* The system will fill in number of mines into random places based on the user input.
* After inputting the size of the grid, the system will initialize default grid value to “_”.
* UncoverSquare method is to display the number of surrounding mines of its current position. If the current position contains 0 mines, it will uncover for its surrounding position with its 8 places that are closest to the current position. It is same logic for its surrounding position as well.
* Once the uncover count equal to total number of grids minus total number of mines, user won the game.

