package minesweeper;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;

public class Map
{
    static final int DEFAULT_MAP_ROWS = 3;
    static final int DEFAULT_MAP_COLS = 3;

    private TileFactory tileFactory;
    private int rows;
    private int cols;
    private Tile[][] tiles;
    private AdjacencyPattern adjacencyPattern;

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

    public String getAdjacencyPatternName(){
        return adjacencyPattern.getName();
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
        EventBus.getInstance().notifyObservers("Attempting to reveal tile at row " + row + ", col " + col + "\n" + this);
        Tile tile =  getTile(row, col);
        tile.revealTile();
        if(!tile.isBomb()&&!tile.isFlaggedTile())
        {
            replaceTile(row,col,tileFactory.createNumberTile(tile));
        }
        EventBus.getInstance().notifyObservers(this.toString());
    }

    public void flagTile(int row, int col){
        Tile tile = getTile(row, col);
        if(tile.isFlaggedTile())
        {
            replaceTile(row,col,tile.getBaseTile());
            EventBus.getInstance().notifyObservers("Tile unflagged at row " + row + ", col " + col + "\n" + this);
        }
        else if(!tile.isRevealedTile())
        {
            replaceTile(row,col,tileFactory.createFlagTile(tile));
            EventBus.getInstance().notifyObservers("Tile flagged at row " + row + ", col " + col + "\n" + this);
        }
        else
        {
            EventBus.getInstance().notifyObservers("Tile already revealed, cannot flag.");
        }
    }

    private void replaceTile(int row, int col, Tile newTile)
    {
        tiles[row][col] = newTile;
    }

    public boolean hasRevealedBomb()
    {
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                Tile tile = getTile(row, col);
                if (tile.isBomb() && tile.isRevealedTile()) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean allSafeTilesRevealed()
    {
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                Tile tile = getTile(row, col);
                if (!tile.isBomb() && !tile.isRevealedTile()) {
                    return false;
                }
            }
        }
        return true;
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
        private final Map map;
        private final List<int[]> bombLocations = new ArrayList<>();
        private static final Random rand = new Random();

        public MapBuilder(TileFactory tileFactory)
        {
            this.map = new Map();
            map.tileFactory = tileFactory;
        }

        public MapBuilder useAdjacencyPattern(String adjacencyPattern) {
            // Might be a good idea to do an adjacencyPattern factory to avoid using new.
            if(Objects.equals(adjacencyPattern, "Fibonacci"))
            {
                map.adjacencyPattern = new FibonacciAdjacency(map);
            }
            else if (Objects.equals(adjacencyPattern, "Knight"))
            {
                map.adjacencyPattern = new KnightAdjacency(map);
            }
            else
            {
                map.adjacencyPattern = new NormalAdjacency(map);
            }
            return this;
        }

        public MapBuilder setRows(int rows){
            map.rows = rows;
            return this;
        }

        public MapBuilder setCols(int cols){
            map.cols = cols;
            return this;
        }

        public MapBuilder create3x3Grid(){
            map.rows = 3;
            map.cols = 3;
            return this;
        }

        public MapBuilder createSquareGrid(int size){
            map.rows = size;
            map.cols = size;
            return this;
        }

        public MapBuilder placeBomb(int row, int col){
            int[] bombLocation = new int[]{row,col};

            // Avoid adding repeat locations
            if(locationHasBomb(bombLocation))
            {
                return this;
            }

            bombLocations.add(bombLocation);
            return this;
        }

        private boolean locationHasBomb(int[] location)
        {
            for(int[] bombLocation:bombLocations)
            {
                if(location[0]==bombLocation[0]&&location[1]==bombLocation[1])
                {
                    return true;
                }
            }

            return false;
        }

        public MapBuilder placeBomb() {
            int[] bombLocation = new int[]{rand.nextInt(map.rows), rand.nextInt(map.cols)};

            // Return without doing anything if all locations are already bombs.
            if (bombLocations.size() >= map.rows * map.cols)
            {
                return this;
            }

            // Avoid adding repeat locations.
            while(locationHasBomb(bombLocation))
            {
                bombLocation = new int[]{rand.nextInt(map.rows),rand.nextInt(map.cols)};
            }

            bombLocations.add(bombLocation);

            return this;
        }

        public MapBuilder placeBombs(int numBombs)
        {
            for(int bombCount=0;bombCount<numBombs;bombCount++)
            {
                placeBomb();
            }

            return this;
        }

        private void populateTileNumbers(){
            for (int row = 0; row < map.rows; row++){
                for (int col = 0; col < map.cols; col++){
                    Tile tile = map.tiles[row][col];
                    if (!tile.isBomb()){
                        int count = map.adjacencyPattern.countAdjacentBombTiles(tile);
                        tile.setTileNumber(count);
                    }
                }
            }
        }

        private void createAndInitializeMapTiles()
        {
            map.tiles = new Tile[map.rows][map.cols];
            for(int row=0;row<map.rows;row++)
            {
                for(int col=0;col<map.cols;col++)
                {
                    map.tiles[row][col] = map.tileFactory.createTile(false);
                }
            }
        }

        private void createAndPlaceBombs()
        {
            boolean hasAtLeastOneBombPlaced = false;
            for(int[] bombLocation:bombLocations)
            {
                // Skip location if location is out of bounds.
                if(!map.inBounds(bombLocation[0],bombLocation[1]))
                {
                    continue;
                }
                map.tiles[bombLocation[0]][bombLocation[1]] = map.tileFactory.createTile(true);
                hasAtLeastOneBombPlaced = true;
            }

            // Make sure at least one bomb is placed in a valid location before the map is returned.
            if(!hasAtLeastOneBombPlaced)
            {
                map.tiles[rand.nextInt(map.rows)][rand.nextInt(map.cols)] = map.tileFactory.createTile(true);
            }
        }

        private void verifyMapHasValidDimensions()
        {
            // Use a default if an invalid number of rows is set.
            if(map.rows<=0)
            {
                map.rows= DEFAULT_MAP_ROWS;
            }

            // Use a default if an invalid number of columns is set.
            if(map.cols<=0)
            {
                map.cols=DEFAULT_MAP_COLS;
            }
        }

        public Map build(){
            verifyMapHasValidDimensions();

            // Default to normal adjacency if no adjacency pattern is set
            if(map.adjacencyPattern==null)
            {
                map.adjacencyPattern = new NormalAdjacency(map);
            }

            createAndInitializeMapTiles();
            createAndPlaceBombs();
            populateTileNumbers();

            return this.map;
        }
    }
}
