package minesweeper;

public class NumberTile extends Tile {
    private final int adjacentBombs;

    public NumberTile(int adjacentBombs){
        this.adjacentBombs = adjacentBombs;
    }

    @Override
    public boolean isBomb(){
        return false;
    }

    @Override
    public int getTileNumber(){
        return adjacentBombs;
    }
}
