package minesweeper;

public class BombTile extends Tile {
    @Override
    public boolean isBomb(){
        return true;
    }

    @Override
    protected String getRevealedDisplayValue(){
        return "B";
    }
}