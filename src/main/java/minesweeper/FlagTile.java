package minesweeper;

public class FlagTile extends DecoratedTile
{
    // Note: this implementation is set up to handle multiple decorators on the same tile
    // even though it should never be able to happen
    public FlagTile(Tile baseTile)
    {
        super(baseTile);
    }

    @Override
    public void revealTile()
    {
        // Override to do nothing for now.
        // Could maybe add a logger message or something to say tile is flagged.
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
