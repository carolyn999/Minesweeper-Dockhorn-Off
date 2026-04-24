package minesweeper;

public abstract class DecoratedTile extends Tile
{
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
