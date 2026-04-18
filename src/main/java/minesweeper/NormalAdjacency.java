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
        int adjacentBombCount = 0;
        int[] tileLocation = getMap().getTileLocation(tile);
        int currentRow = tileLocation[0];
        int currentColumn = tileLocation[1];

        for (int rowOffset = -1; rowOffset <= 1; rowOffset++){
            for (int columnOffset = -1; columnOffset <= 1; columnOffset++){
                if (rowOffset == 0 && columnOffset == 0){
                    continue;
                }
                int neighborRow = currentRow + rowOffset;
                int neighborColumn = currentColumn + columnOffset;

                if (isBombAtLocation(neighborRow, neighborColumn)){
                    adjacentBombCount++;
                }
            }
        }
        // Replace with logic to count neighboring tiles containing bombs as adjacent
        return adjacentBombCount;
    }
}
