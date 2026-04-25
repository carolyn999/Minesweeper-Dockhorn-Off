package minesweeper;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;

public class Map
{
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

    // Making this return a String with the adjacencyPattern name for better encapsulation.
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
        // Moved (and modified) from Minesweeper
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
        // Moved (and modified) from Minesweeper
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
        private int rows;
        private int cols;
        private AdjacencyPattern adjacencyPattern;
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
            int[] bombLocation = new int[]{row,col};

            // Avoid adding repeat locations
            if(locationHasBomb(bombLocation))
            {
                // Could add warning here.
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
            int[] bombLocation = new int[]{rand.nextInt(rows), rand.nextInt(cols)};

            // Return without doing anything if all locations are already bombs.
            if (bombLocations.size() >= rows * cols)
            {
                // Should add warning here.
                return this;
            }

            // Avoid adding repeat locations.
            while(locationHasBomb(bombLocation))
            {
                bombLocation = new int[]{rand.nextInt(rows),rand.nextInt(cols)};
            }

            bombLocations.add(bombLocation);

            return this;
        }

        public MapBuilder placeBombs(int numBombs)
        {
            for(int i=0;i<numBombs;i++)
            {
                placeBomb();
            }

            return this;
        }

        private void populateTileNumbers(){
            for (int row = 0; row < rows; row++){
                for (int col = 0; col < cols; col++){
                    Tile tile = map.tiles[row][col];
                    if (!tile.isBomb()){
                        int count = adjacencyPattern.countAdjacentBombTiles(tile);
                        tile.setTileNumber(count);
                    }
                }
            }
        }

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

            map.rows = this.rows;
            map.cols = this.cols;
            Tile[][] tiles = new Tile[rows][cols];
            for(int row=0;row<rows;row++)
            {
                for(int col=0;col<cols;col++)
                {
                    tiles[row][col] = map.tileFactory.createTile(false);
                }
            }

            for(int[] bombLocation:bombLocations)
            {
                if(!map.inBounds(bombLocation[0],bombLocation[1]))
                {
                    // Should add warning if any bomb locations are skipped due to being out of bounds.
                    continue;
                }
                tiles[bombLocation[0]][bombLocation[1]] = map.tileFactory.createTile(true);
            }

            // Should make sure at least one bomb is placed in a valid location before the map is returned.

            map.tiles = tiles;
            map.adjacencyPattern = this.adjacencyPattern;
            populateTileNumbers();

            return this.map;
        }
    }
}
