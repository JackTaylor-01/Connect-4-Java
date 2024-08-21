import java.util.Scanner;
public class Connect4App {
    public static void main(String[] args){

        int rows = 10;
        int columns = 10;
        int winningLength = 4;

        Scanner sc = new Scanner(System.in);
        System.out.println("Please enter row amount: ");
        rows = sc.nextInt();
        System.out.println("Please enter column amount: ");
        columns = sc.nextInt();
        System.out.println("Please enter winning length: ");
        winningLength = sc.nextInt();

        new Connect4GUI(rows, columns, winningLength);
    }
    
}
