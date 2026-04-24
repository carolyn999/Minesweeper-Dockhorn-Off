package minesweeper;

public class FlagTile extends Tile
{
    // Note: this implementation is set up to handle multiple decorators on the same tile
    // even though it should never be able to happen
    private final Tile baseTile;

    public FlagTile(Tile baseTile)
    {
        this.baseTile = baseTile;
    }

    @Override
    public int getTileNumber()
    {
        return baseTile.getTileNumber();
    }

    @Override
    protected Tile getBaseTile()
    {
        return baseTile.getBaseTile();
    }

    @Override
    public void revealTile()
    {
        // Override to do nothing for now.
        // Could maybe add a logger message or something to say tile is flagged.
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
