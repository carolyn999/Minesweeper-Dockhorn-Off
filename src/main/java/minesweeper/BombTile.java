package minesweeper;

public class BombTile extends Tile {
    @Override
    public boolean isBomb(){
        return true;
    }

    @Override
    public String toString(){
        if(!isRevealedTile())
        {
            return ".";
        }
        return "B";
    }
}