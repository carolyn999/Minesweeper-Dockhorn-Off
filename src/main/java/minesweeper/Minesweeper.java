package minesweeper;

public class Minesweeper implements ITilesGame {
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
