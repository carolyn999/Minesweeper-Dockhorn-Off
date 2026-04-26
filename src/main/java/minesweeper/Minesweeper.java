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
        EventBus.getInstance().notifyObservers("Starting game:\n" + map);

        while (!isOver())
        {
            EventBus.getInstance().notifyObservers("Adjacency rule: "+map.getAdjacencyPatternName());
            PlayerAction action = player.getAction();

            if (action == PlayerAction.QUIT)
            {
                EventBus.getInstance().notifyObservers("Quit game.");
                return;
            }

            int row = player.getRow();
            int col = player.getCol();

            if (!tileLocationIsValid(row, col))
            {
                EventBus.getInstance().notifyObservers("Row " + row + ", col " + col + " is out of bounds.");
                continue;
            }

            if (action == PlayerAction.REVEAL && tileAlreadyRevealed(row, col))
            {
                EventBus.getInstance().notifyObservers("Tile at row " + row + ", col " + col + " is already revealed.");
                continue;
            }

            if (action == PlayerAction.REVEAL)
            {
                map.revealTile(row, col);
            }
            else if (action == PlayerAction.FLAG)
            {
                map.flagTile(row, col);
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

    private boolean tileLocationIsValid(int row, int col)
    {
        return map.inBounds(row, col);
    }

    //added to prevent players from revealing same tile twice
    private boolean tileAlreadyRevealed(int row, int col)
    {
        return map.getTile(row, col).isRevealedTile();
    }
}
