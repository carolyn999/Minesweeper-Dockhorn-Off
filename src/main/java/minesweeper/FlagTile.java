package minesweeper;

public class FlagTile extends DecoratedTile
{
    public FlagTile(Tile baseTile)
    {
        super(baseTile);
    }

    @Override
    public void revealTile()
    {
        EventBus.getInstance().notifyObservers("Tile is flagged, cannot reveal until flag is removed.");
    }

    @Override
    public boolean isFlaggedTile()
    {
        return true;
    }

    @Override
    public String toString()
    {
        return "F";
    }
}
