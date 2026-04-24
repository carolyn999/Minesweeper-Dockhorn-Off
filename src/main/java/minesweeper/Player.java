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

    public PlayerCommand getCommand()
    {
        String action = getAction();
        if (action.equals("quit"))
        {
            return new PlayerCommand("quit", -1, -1);
        }
        int row = getRow();
        int col = getCol();
        return new PlayerCommand(action, row, col);
    }

    private String getAction()
    {
        while (true)
        {
            System.out.print("Enter action: reveal (R), flag (F), or quit (Q): ");
            String action = scanner.nextLine().trim().toLowerCase();

            if (action.startsWith("r"))
            {
                return "reveal";
            }
            else if (action.startsWith("f"))
            {
                return "flag";
            }
            else if (action.startsWith("q"))
            {
                return "quit";
            }
            else
            {
                System.out.println("Invalid action. Enter R, F, or Q.");
            }
        }
    }

    private int getRow()
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

    private int getCol()
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