package minesweeper;

import java.util.Scanner;

public class Main {
    public static void main(String[] args)
    {
        Scanner scanner = new Scanner(System.in);

        String adjacencyPattern = chooseAdjacencyPattern(scanner);

        int gridSize = chooseGridSize(scanner);
        int numberOfBombs = chooseNumberOfBombs(scanner, gridSize);
        Player player = new Player();

        minesweeper.Map map = Map.getBuilder(new TileFactory())
                .useAdjacencyPattern(adjacencyPattern)
                .createSquareGrid(gridSize).placeBombs(numberOfBombs)
                .build();

        Minesweeper game = new Minesweeper(map, player);
        VisualMinesweeperObserver observer = new VisualMinesweeperObserver();
        game.attach(observer);
        game.playGame();
        game.detach(observer);
    }

    private static String chooseAdjacencyPattern(Scanner scanner)
    {
        while (true)
        {
            System.out.print("Choose adjacency pattern: normal, knight, or fibonacci: ");
            String input = scanner.nextLine().trim().toLowerCase();
            if (input.startsWith("n"))
            {
                return "Normal";
            }
            else if (input.startsWith("k"))
            {
                return "Knight";
            }
            else if (input.startsWith("f"))
            {
                return "Fibonacci";
            }
            else
            {
                System.out.println("Invalid choice. Enter normal, knight, or fibonacci.");
            }
        }
    }

    private static int chooseGridSize(Scanner scanner)
    {
        while (true)
        {
            System.out.print("Choose grid size, such as 3 for 3x3 or 5 for 5x5: ");
            try
            {
                int size = Integer.parseInt(scanner.nextLine().trim());
                if (size >= 2 && size <= 10)
                {
                    return size;
                }
                System.out.println("Grid size can be at least 2 or at most 10.");

            }
            catch (NumberFormatException exception)
            {
                System.out.println("Invalid grid size. Enter a number.");
            }
        }
    }

    private static int chooseNumberOfBombs(Scanner scanner, int gridSize)
    {
        int maxBombs = gridSize * gridSize - 1;
        while (true)
        {
            System.out.print("Choose number of bombs, from 1 to " + maxBombs + ": ");
            try
            {
                int bombs = Integer.parseInt(scanner.nextLine().trim());
                if (bombs >= 1 && bombs <= maxBombs)
                {
                    return bombs;
                }
                System.out.println("Number of bombs must be between 1 and " + maxBombs + ".");
            }
            catch (NumberFormatException exception)
            {
                System.out.println("Invalid number of bombs. Please enter a number.");
            }
        }
    }
}
