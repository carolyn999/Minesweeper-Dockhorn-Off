package minesweeper;

public abstract class NumberTile extends Tiles {
    private final int adjacentBombs;

    protected NumberTile(int adjacentBombs){
        this.adjacentBombs = adjacentBombs;
    }

    @Override
    public boolean isBomb(){
        return false;
    }
}
