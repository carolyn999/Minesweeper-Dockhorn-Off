package minesweeper;

public class VisualMinesweeperObserver implements TilesObserver
{
    @Override
    public void updateObserver(String event)
    {
        System.out.println("\n" + event + "\n");
    }
}
