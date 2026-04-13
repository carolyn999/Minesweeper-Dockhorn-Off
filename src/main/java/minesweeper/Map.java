package minesweeper;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Map
{
    private final int rows;
    private final int cols;
    private final Tile[][] tiles;
    private final AdjacencyPattern adjacencyPattern;
    private final List<TilesObserver> observers = new ArrayList<>();

    private Map(int rows, int cols, Tile[][] tiles, AdjacencyPattern adjacencyPattern){
        this.rows = rows;
        this.cols = cols;
        this.tiles = tiles;
        this.adjacencyPattern = adjacencyPattern;
    }



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

    public boolean inBounds(int row, int col){
        return row >= 0 && row < rows && col >= 0 && col < cols;
    }

    public void revealTile(int row, int col){
        Tile tile =  getTile(row, col);
        tile.revealTile();
        notifyObservers();
    }

    public void flagTile(int row, int col){
        Tile tile = getTile(row, col);
        tile.toggleFlag();
        notifyObservers();
    }

    //observers
    public void addObserver(TilesObserver observer) {
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


    //need to implement notifyObservers & more

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

    public static class MapBuilder
    {
        private Map map;
        private final TileFactory tileFactory;
        private int rows;
        private int cols;
        private String adjacencyPatternName = "Normal";
        private AdjacencyPattern adjacencyPattern;
        private final List<int[]> bombLocations = new ArrayList<>();


        public MapBuilder(TileFactory tileFactory)
        {
            this.map = new Map();
            this.tileFactory = tileFactory;
        }

        public MapBuilder useAdjacencyPattern(String adjacencyPattern) {
            this.adjacencyPatternName = adjacencyPattern;
            return this;
        }
//            if(Objects.equals(adjacencyPattern, "Fibonacci"))
//            {
//                this.adjacencyPattern = new FibonacciAdjacency(map);
//            }
//            else if (Objects.equals(adjacencyPattern, "Knight"))
//            {
//                this.adjacencyPattern = new KnightAdjacency(map);
//            }
//            else
//            {
//                this.adjacencyPattern = new NormalAdjacency(map);
//            }
//        }

        public MapBuilder rows(int rows){
            this.rows = rows;
            return this;
        }
        public MapBuilder cols(int cols){
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
