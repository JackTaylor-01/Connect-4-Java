public class Player {
    private String name;
    private String colour; 

    public Player(String name, String colour) {
        this.name = name;
        this.colour = colour;
    }

    public String getName() {
        return name;
    }

    public String getColour() {
        return colour;
    }

    public static Player switchPlayer(Player currentPlayer, Player player1, Player player2) {
        return currentPlayer == player1 ? player2 : player1;
    }
}
