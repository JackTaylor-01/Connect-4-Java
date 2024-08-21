import java.util.Scanner;

public class Connect4 {
    private GameGrid gameGrid;
    private Player player1;
    private Player player2;
    private Player currentPlayer;
    private int winningLength;

    public Connect4(int rows, int columns, int winningLength) {
        this.gameGrid = new GameGrid(rows, columns);
        this.player1 = new Player("Player 1", "R");
        this.player2 = new Player("Player 2", "Y");
        this.currentPlayer = player1;
        this.winningLength = winningLength;
    }

    public void playGame() {
        boolean gameWon = false;
        while (!gameWon) {
            gameGrid.displayGrid();
            System.out.println(currentPlayer.getName() + " (" + currentPlayer.getColour() + "), choose a column: ");
            int column = getInputColumn();
            if (gameGrid.addToken(column, currentPlayer.getColour())) {
                if (checkForWin()) {
                    gameWon = true;
                    gameGrid.displayGrid();
                    System.out.println(currentPlayer.getName() + " wins!");
                } else {
                    currentPlayer = Player.switchPlayer(currentPlayer, player1, player2);
                }
            } else {
                System.out.println("Column full! Choose another column.");
            }
        }
    }

    private int getInputColumn() {
        // Placeholder for input method
        Scanner sc = new Scanner(System.in);
        return sc.nextInt() - 1;

    }

    private boolean checkForWin() {
        return checkHorizontalWin() || checkVerticalWin() || checkDiagonalWin();
    }

    private boolean checkHorizontalWin() {
        String[][] grid = gameGrid.getGrid();
        for (int i = 0; i < gameGrid.getGrid().length; i++) {
            for (int j = 0; j <= grid[0].length - winningLength; j++) {
                String color = grid[i][j];
                if (!color.equals(".") && checkLine(color, i, j, 0, 1)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean checkVerticalWin() {
        String[][] grid = gameGrid.getGrid();
        for (int i = 0; i <= grid.length - winningLength; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                String color = grid[i][j];
                if (!color.equals(".") && checkLine(color, i, j, 1, 0)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean checkDiagonalWin() {
        String[][] grid = gameGrid.getGrid();
        // Check downward diagonals
        for (int i = 0; i <= grid.length - winningLength; i++) {
            for (int j = 0; j <= grid[0].length - winningLength; j++) {
                String color = grid[i][j];
                if (!color.equals(".") && checkLine(color, i, j, 1, 1)) {
                    return true;
                }
            }
        }
        // Check upward diagonals
        for (int i = winningLength - 1; i < grid.length; i++) {
            for (int j = 0; j <= grid[0].length - winningLength; j++) {
                String color = grid[i][j];
                if (!color.equals(".") && checkLine(color, i, j, -1, 1)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean checkLine(String color, int row, int col, int dRow, int dCol) {
        for (int k = 1; k < winningLength; k++) {
            if (!gameGrid.getGrid()[row + k * dRow][col + k * dCol].equals(color)) {
                return false;
            }
        }
        return true;
    }
}
