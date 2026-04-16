package minesweeper;

public class Minesweeper implements ITilesGame {
    private boolean gameWon;
    private boolean gameOver;
    private Map map;

    public Minesweeper(Map map)
    {
        this.map = map;
    }

    @Override
    public void attach()
    {

    }

    @Override
    public void detach()
    {

    }

    public void playGame()
    {
    }

    public boolean isOver()
    {
        return false; // Placeholder for compilation to run tests.
    }
}
