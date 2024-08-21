public class GameGrid {
    private String[][] grid;
    private int rows;
    private int columns;

    public GameGrid(int rows, int columns) {
        this.rows = rows;
        this.columns = columns;
        this.grid = new String[rows][columns];
        initializeGrid();
    }

    private void initializeGrid() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                grid[i][j] = ".";
            }
        }
    }

    public void displayGrid() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                System.out.print(grid[i][j] + " ");
            }
            System.out.println();
        }
    }

    public String[][] getGrid() {
        return grid;
    }
}
