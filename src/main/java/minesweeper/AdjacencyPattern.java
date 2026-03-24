package minesweeper;

public abstract class AdjacencyPattern
{
    private final Map map;

    public AdjacencyPattern(Map map)
    {
        this.map = map;
    }
    public abstract int countAdjacentBombTiles(Tile tile);
    protected Map getMap()
    {
        return this.map;
    }
}
