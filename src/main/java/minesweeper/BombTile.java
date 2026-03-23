package minesweeper;

public abstract class BombTile extends Tiles{
    @Override
    public boolean isBomb(){
        return true;
    }
}