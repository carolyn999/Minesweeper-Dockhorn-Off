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
}
