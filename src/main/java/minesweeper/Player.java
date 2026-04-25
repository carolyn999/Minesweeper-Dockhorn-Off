package minesweeper;
import java.util.Scanner;

public class Player
{
    private final Scanner scanner;

    public Player()
    {
        this.scanner = new Scanner(System.in);
    }

    public Player(Scanner scanner)
    {
        this.scanner = scanner;
    }

    public PlayerAction getAction() {
        while (true) {
            System.out.print("Enter action: reveal, flag, or quit: ");
            String action = scanner.nextLine().trim().toLowerCase();

            if (action.startsWith("r")) {
                return PlayerAction.REVEAL;
            } else if (action.startsWith("f")) {
                return PlayerAction.FLAG;
            } else if (action.startsWith("q")) {
                return PlayerAction.QUIT;
            } else {
                System.out.println("Invalid action. Enter reveal, flag, or quit.");
            }
        }
    }

    public int getRow()
    {
        while (true)
        {
            System.out.print("Enter row: ");
            try
            {
                return Integer.parseInt(scanner.nextLine().trim());
            }
            catch (NumberFormatException exception)
            {
                System.out.println("Invalid row. Enter a number.");
            }
        }
    }

    public int getCol()
    {
        while (true)
        {
            System.out.print("Enter col: ");
            try
            {
                return Integer.parseInt(scanner.nextLine().trim());
            }
            catch (NumberFormatException exception)
            {
                System.out.println("Invalid column. Enter a number.");
            }
        }
    }
}