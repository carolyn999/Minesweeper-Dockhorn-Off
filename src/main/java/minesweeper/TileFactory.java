package minesweeper;

public class TileFactory {
    public Tile createTile(boolean isBomb)
    {
        if(isBomb)
        {
            return new BombTile();
        }
        else
        {
            return new EmptyTile();
        }
    }

    public Tile createFlagTile(Tile baseTile)
    {
        return new FlagTile(baseTile);
    }

    public Tile createNumberTile(Tile baseTile) {
        return new NumberTile(baseTile);
    }
}
