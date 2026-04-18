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
    protected boolean isBombAtLocation(int[] location)
    {
        return map.inBounds(location[0],location[1]) && map.getTile(location[0],location[1]).isBomb();
    }
    protected boolean isBombAtLocation(int row, int col)
    {
        return map.inBounds(row,col) && map.getTile(row,col).isBomb();
    }
}
