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
        private final TileFactory tileFactory;
        private int rows;
        private int cols;
        private AdjacencyPattern adjacencyPattern;

        public MapBuilder(TileFactory tileFactory)
        {
            this.tileFactory = tileFactory;
        }

        public MapBuilder rows(int rows){
            this.rows = rows;
            return this;
        }
        public MapBuilder cols(int cols){
            this.cols = cols;
            return this;
        }
        public MapBuilder adjacencyPattern(AdjacencyPattern adjacencyPattern){
            this.adjacencyPattern = adjacencyPattern;
            return this;
        }
        public Map build(){
            Tile[][] tiles = new Tile[rows][cols];
            return new Map(rows, cols, tiles, adjacencyPattern);
        }
    }
}
