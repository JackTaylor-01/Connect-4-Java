import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Connect4GUI extends JFrame {
    private Connect4 connect4;
    private JButton[] buttons;
    private JLabel[][] cells;

    public Connect4GUI(int rows, int columns, int winningLength) {
        // Initialize the ConnectFour object with the given parameters
        this.connect4 = new Connect4(rows, columns, winningLength);

        setTitle("Connect Four");
        setSize(700, 600);
        setLayout(new BorderLayout());

        // Create the top panel with buttons for each column
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new GridLayout(1, columns));
        buttons = new JButton[columns];
        for (int i = 0; i < columns; i++) {
            buttons[i] = new JButton("Drop");
            int column = i;
            buttons[i].addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    if (connect4.getGameGrid().addToken(column, connect4.getCurrentPlayer().getColour())) {
                        updateGrid();
                        if (connect4.checkForWin()) {
                            JOptionPane.showMessageDialog(null, connect4.getCurrentPlayer().getName() + " wins!");
                            resetGame();
                        } else {
                            connect4.switchPlayer();
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Column full! Choose another column.");
                    }
                }
            });
            topPanel.add(buttons[i]);
        }
        add(topPanel, BorderLayout.NORTH);

        // Create the main grid panel where the game state will be displayed
        JPanel gridPanel = new JPanel();
        gridPanel.setLayout(new GridLayout(rows, columns));
        cells = new JLabel[rows][columns];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                cells[i][j] = new JLabel(".");
                cells[i][j].setHorizontalAlignment(SwingConstants.CENTER);
                cells[i][j].setVerticalAlignment(SwingConstants.CENTER);
                cells[i][j].setOpaque(true);
                cells[i][j].setBackground(Color.WHITE);
                gridPanel.add(cells[i][j]);
            }
        }
        add(gridPanel, BorderLayout.CENTER);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    private void updateGrid() {
        String[][] grid = connect4.getGameGrid().getGrid();
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                cells[i][j].setText(grid[i][j]);
                if (grid[i][j].equals("R")) {
                    cells[i][j].setBackground(Color.RED);
                } else if (grid[i][j].equals("Y")) {
                    cells[i][j].setBackground(Color.YELLOW);
                } else {
                    cells[i][j].setBackground(Color.WHITE);
                }
            }
        }
    }

    private void resetGame() {
        connect4 = new Connect4(connect4.getGameGrid().getGrid().length, connect4.getGameGrid().getGrid()[0].length, connect4.getWinningLength());
        updateGrid();
    }

}
