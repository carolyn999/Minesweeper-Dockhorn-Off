package minesweeper;

public class NormalAdjacency implements AdjacencyPattern
{
    private Map map;

    public NormalAdjacency(Map map)
    {
        this.map = map;
    }

    @Override
    public int countAdjacentBombTiles(Tile tile)
    {
        // Replace with logic to count neighboring tiles containing bombs as adjacent
        return 0;
    }
}
