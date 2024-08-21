import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Logger;
import java.util.logging.Level;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.SimpleFormatter;
public class Connect4 {
    private GameGrid gameGrid;
    private Player player1;
    private Player player2;
    private Player currentPlayer;
    private int winningLength;

    private static final Logger LOG = Logger.getLogger(Connect4.class.getName());

    public Connect4(int rows, int columns, int winningLength) {
        this.gameGrid = new GameGrid(rows, columns);
        this.player1 = new Player("Player 1", "R");
        this.player2 = new Player("Player 2", "Y");
        this.currentPlayer = player1;
        this.winningLength = winningLength;
        setupLogger();
    }

    public void playGame() {
        boolean gameWon = false;
        LOG.info("Game started");
        while (!gameWon) {
            gameGrid.displayGrid();
            System.out.println(currentPlayer.getName() + " (" + currentPlayer.getColour() + "), choose a column: ");
            int column = getInputColumn();
            if (gameGrid.addToken(column, currentPlayer.getColour())) {
                if (checkForWin()) {
                    gameWon = true;
                    gameGrid.displayGrid();
                    LOG.info(currentPlayer.getName() + " won");
                    System.out.println(currentPlayer.getName() + " wins!");
                } else {
                    currentPlayer = Player.switchPlayer(currentPlayer, player1, player2);
                }
            } else {
                LOG.warning(currentPlayer.getName() + " tried placing token in a full column: " + column);
                System.out.println("Column full! Choose another column.");
            }
        }
    }

    private int getInputColumn() {
        // Placeholder for input method
        Scanner sc = new Scanner(System.in);
        int col =  sc.nextInt() - 1;
        if(col < 0 || col >= gameGrid.getGrid()[0].length){
            LOG.warning(currentPlayer.getName() + " chose invalid column: " + Integer.toString(col));
            System.out.println("Invalid column, please try again");
            return getInputColumn();
        }
        return col;

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

    private static void setupLogger() {
        try {
            // Create a FileHandler to log to a file
            FileHandler fileHandler = new FileHandler("connect4.log", true);
            fileHandler.setFormatter(new SimpleFormatter());

            // Create a ConsoleHandler to log to console
            ConsoleHandler consoleHandler = new ConsoleHandler();

            // Add handlers to the logger
            LOG.addHandler(consoleHandler);
            LOG.addHandler(fileHandler);

            // Set log levels
            LOG.setLevel(Level.ALL);
            fileHandler.setLevel(Level.ALL);
            consoleHandler.setLevel(Level.ALL);

        } catch (IOException e) {
            LOG.log(Level.SEVERE, "Failed to set up logger", e);
        }
    }
}
