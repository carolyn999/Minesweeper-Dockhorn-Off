package minesweeper;

public class Minesweeper implements ITilesGame {
    private final Map map;
    private final Player player;

    public Minesweeper(Map map,Player player)
    {
        this.map = map;
        this.player=player;
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
        // Should use dependency injection for the player.
        //Player player = new Player();
        EventBus.getInstance().notifyObservers("Starting game:\n" + map);

        while (!isOver())
        {
            PlayerAction action = player.getAction();

            if (action == PlayerAction.QUIT)
            {
                EventBus.getInstance().notifyObservers("Quit game.");
                return;
            }

            int row = player.getRow();
            int col = player.getCol();

            if (action == PlayerAction.REVEAL)
            {
                map.revealTile(row, col);
                // Moving this to map/tile so the actual status of the attempted action based on tile can be sent
                //EventBus.getInstance().notifyObservers("Tile revealed at row " + row + ", col " + col + "\n" + map);
            }
            else if (action == PlayerAction.FLAG)
            {
                map.flagTile(row, col);
                //EventBus.getInstance().notifyObservers("Tile flagged/unflagged at row " + row + ", col " + col + "\n" + map);
            }
        }
        EventBus.getInstance().notifyObservers("Game over.");
        if(isWon())
        {
            EventBus.getInstance().notifyObservers("You won!");
        }
        else
        {
            EventBus.getInstance().notifyObservers("You lost!");
        }
    }

    public boolean isOver()
    {
        return map.hasRevealedBomb()||map.allSafeTilesRevealed();
    }

    public boolean isWon(){
        return !map.hasRevealedBomb() && map.allSafeTilesRevealed();
    }
}
