public class Connect4App {
    public static void main(String[] args){

        Player player = new Player("player 1", "R");
        System.out.println(player.getColour());
        System.out.println(player.getName());

        GameGrid grid = new GameGrid(10, 10);
        grid.displayGrid();

    }
    
}
