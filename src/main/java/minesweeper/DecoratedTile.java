package minesweeper;

public abstract class DecoratedTile extends Tile
{
    // Note: this implementation is set up to handle multiple decorators on the same tile
    // even though it should never be able to happen

    private final Tile baseTile;

    public DecoratedTile(Tile baseTile)
    {
        this.baseTile = baseTile;
    }

    @Override
    public boolean isRevealedTile()
    {
        return baseTile.isRevealedTile();
    }

    @Override
    protected Tile getBaseTile()
    {
        return baseTile.getBaseTile();
    }

    @Override
    public int getTileNumber()
    {
        return baseTile.getTileNumber();
    }
}
