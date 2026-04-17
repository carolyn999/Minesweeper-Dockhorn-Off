package minesweeper;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Map
{
    private int rows;
    private int cols;
    private Tile[][] tiles;
    private AdjacencyPattern adjacencyPattern;
    // There is a note below on why observer stuff should not be here.
    //private final List<TilesObserver> observers = new ArrayList<>();

    // Suggestion: have builder use the empty constructor
    // Otherwise, why would we need a builder?
    /*private Map(int rows, int cols, Tile[][] tiles, AdjacencyPattern adjacencyPattern){
        this.rows = rows;
        this.cols = cols;
        this.tiles = tiles;
        this.adjacencyPattern = adjacencyPattern;
    }*/



    private Map()
    {

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

    private void setAdjacencyPattern(AdjacencyPattern adjacencyPattern){
        this.adjacencyPattern = adjacencyPattern;
    }

    public Tile getTile(int row, int col){
        if (!inBounds(row,col)){
            throw new IllegalArgumentException("Tile position is out of bounds.");
        }
        return tiles[row][col];
    }

    public int[] getTileLocation(Tile tile)
    {
        for(int row=0;row<rows;row++)
        {
            for(int col=0;col<cols;col++)
            {
                if(tiles[row][col]==tile)
                {
                    return new int[]{row,col};
                }
            }
        }
        throw new IllegalArgumentException("Tile not found in map.");
    }

    public boolean inBounds(int row, int col){
        return row >= 0 && row < rows && col >= 0 && col < cols;
    }

    public void revealTile(int row, int col){
        Tile tile =  getTile(row, col);
        tile.revealTile();
        //notifyObservers();
    }

    public void flagTile(int row, int col){
        Tile tile = getTile(row, col);
        tile.toggleFlag();
        //notifyObservers();
    }

    // This should not be in map
    // unless you create some kind of observable interface for map to implement
    //observers
    /*public void addObserver(TilesObserver observer) {
        observers.add(observer);
    }

    public void removeObserver(TilesObserver observer) {
        observers.remove(observer);
    }

    private void notifyObservers(){
        for (TilesObserver observer : observers){
            observer.update();
        }
    }


    //need to implement notifyObservers & more*/

    private void populateTileNumbers(){
        for (int row = 0; row < rows; row++){
            for (int col = 0; col < cols; col++){
                Tile tile = tiles[row][col];
                if (!tile.isBomb()){
                    int count = adjacencyPattern.countAdjacentBombTiles(tile);
                    tile.setTileNumber(count);
                }
            }
        }
    }

    @Override
    public String toString() {
        StringBuilder mapString = new StringBuilder();

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                mapString.append("|").append(tiles[row][col]);
            }
            mapString.append("|");

            if (row < rows - 1) {
                mapString.append("\n");
            }
        }

        return mapString.toString();
    }

    public static class MapBuilder
    {
        private Map map;
        private final TileFactory tileFactory;
        private int rows;
        private int cols;
        //private String adjacencyPatternName = "Normal";
        private AdjacencyPattern adjacencyPattern;
        private final List<int[]> bombLocations = new ArrayList<>();


        public MapBuilder(TileFactory tileFactory)
        {
            this.map = new Map();
            this.tileFactory = tileFactory;
        }

        public MapBuilder useAdjacencyPattern(String adjacencyPattern) {
            // Why replace my working code with two sources of truth?
            // And a chance of never setting the actual object?
            if(Objects.equals(adjacencyPattern, "Fibonacci"))
            {
                this.adjacencyPattern = new FibonacciAdjacency(map);
            }
            else if (Objects.equals(adjacencyPattern, "Knight"))
            {
                this.adjacencyPattern = new KnightAdjacency(map);
            }
            else
            {
                this.adjacencyPattern = new NormalAdjacency(map);
            }
            return this;
        }

        public MapBuilder setRows(int rows){
            this.rows = rows;
            return this;
        }
        public MapBuilder setCols(int cols){
            this.cols = cols;
            return this;
        }

        public MapBuilder create3x3Grid(){
            this.rows = 3;
            this.cols = 3;
            return this;
        }

        public MapBuilder createSquareGrid(int size){
            this.rows = size;
            this.cols = size;
            return this;
        }

        public MapBuilder placeBomb(int row, int col){
            bombLocations.add(new int[]{row,col});
            return this;
        }

        // The way this is currently set up, this method would never be usable.
        //public MapBuilder useAdjacencyPattern(AdjacencyPattern adjacencyPattern){
        //    this.adjacencyPattern = adjacencyPattern;
        //    return this;
        //}

        public Map build(){

            if(rows<=0)
            {
                // Should set up a logger and have it warn about a default being set
                rows= 3;
            }

            if(cols<=0)
            {
                // Same as above about logger/warning.
                cols=3;
            }

            if(adjacencyPattern==null)
            {
                // Again about logger/warning.
                this.adjacencyPattern = new NormalAdjacency(map);
            }

            // Should add a method to place bombs randomly
            // And make sure there are bombs before the map is returned.

            map.rows = this.rows;
            map.cols = this.cols;
            Tile[][] tiles = new Tile[rows][cols];
            for(int row=0;row<rows;row++)
            {
                for(int col=0;col<cols;col++)
                {
                    tiles[row][col] = tileFactory.createTile(false);
                }
            }

            for(int[] bombLocation:bombLocations)
            {
                tiles[bombLocation[0]][bombLocation[1]] = tileFactory.createTile(true);
            }

            map.tiles = tiles;
            map.adjacencyPattern = this.adjacencyPattern;
            map.populateTileNumbers();

            return this.map;
        }
    }
}
