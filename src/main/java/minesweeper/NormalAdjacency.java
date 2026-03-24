package minesweeper;

public class NormalAdjacency extends AdjacencyPattern
{
    public NormalAdjacency(Map map)
    {
        super(map);
    }

    @Override
    public int countAdjacentBombTiles(Tile tile)
    {
        // Replace with logic to count neighboring tiles containing bombs as adjacent
        return 0;
    }
}
