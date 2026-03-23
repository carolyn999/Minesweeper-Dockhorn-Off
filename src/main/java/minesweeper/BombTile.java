package minesweeper;

public abstract class BombTile extends Tile {
    @Override
    public boolean isBomb(){
        return true;
    }
}