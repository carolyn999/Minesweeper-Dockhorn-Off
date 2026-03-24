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

    //observers
    public void addObserver(TilesObserver observer) {
        observers.add(observer);
    }

    public void removeObserver(TilesObserver observer) {
        observers.remove(observer);
    }

    //need to implement notifyObservers & more

    public static class MapBuilder
    {
        private Map map;
        private final TileFactory tileFactory;
        private int rows;
        private int cols;
        private AdjacencyPattern adjacencyPattern;

        public MapBuilder(TileFactory tileFactory)
        {
            this.map = new Map();
            this.tileFactory = tileFactory;
        }

        public MapBuilder useAdjacencyPattern(String adjacencyPattern)
        {
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
