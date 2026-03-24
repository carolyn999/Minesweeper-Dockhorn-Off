package minesweeper;

public class Map
{
    private final int rows;
    private final int cols;
    private final Tile[][] tiles;
    private final AdjacencyPattern adjacencyPattern;

    public Map(int rows, int cols, Tile[][] tiles, AdjacencyPattern adjacencyPattern){
        this.rows = rows;
        this.cols = cols;
        this.tiles = tiles;
        this.adjacencyPattern = adjacencyPattern;
    }



    public static MapBuilder getBuilder(TileFactory tileFactory)
    {
        return new MapBuilder(tileFactory);
    }

    public int getRows(){
        return rows;
    }

    public int getCols(){
        return cols;
    }

    public AdjacencyPattern getAdjacencyPattern(){
        return adjacencyPattern;
    }

    public static class MapBuilder
    {
        private TileFactory tileFactory;

        public MapBuilder(TileFactory tileFactory)
        {
            this.tileFactory = tileFactory;
        }
    }
}
