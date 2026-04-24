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
        Player player = new Player();
        EventBus.getInstance().notifyObservers("Starting game:\n" + map);

        while (!isOver())
        {
            PlayerAction action = player.getAction();

            if (action == PlayerAction.QUIT)
            {
                EventBus.getInstance().notifyObservers("Game quit.");
                return;
            }

            int row = player.getRow();
            int col = player.getCol();

            if (action == PlayerAction.REVEAL)
            {
                map.revealTile(row, col);

                EventBus.getInstance().notifyObservers("Tile revealed at row " + row + ", col " + col + "\n" + map);
            }
            else if (action == PlayerAction.FLAG)
            {
                map.flagTile(row, col);
                EventBus.getInstance().notifyObservers("Tile flagged/unflagged at row " + row + ", col " + col + "\n" + map);
            }
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
