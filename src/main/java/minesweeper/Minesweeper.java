package minesweeper;

public class Minesweeper implements ITilesGame {
    private final Map map;

    public Minesweeper(Map map)
    {
        this.map = map;
    }

    @Override
    public void attach(TilesObserver observer)
    {
        EventBus.getInstance().attach(observer);
    }

    @Override
    public void detach(TilesObserver observer)
    {
        EventBus.getInstance().detach(observer);
    }

    public void playGame()
    {
        /* Gameplay loop
        while(!isOver())
        {
            // Maybe add a player class with this method to control user interaction
            // and potentially have the possibility of computer player?
            playTurn();
        }
        */
    }

    public boolean isOver()
    {
        return map.hasRevealedBomb()||map.allSafeTilesRevealed();
    }

    public boolean isWon(){
        return !map.hasRevealedBomb() && map.allSafeTilesRevealed();
    }
}
