package minesweeper;

public class BombTile extends Tile {
    @Override
    public boolean isBomb(){
        return true;
    }

    @Override
    public void revealTile()
    {
        super.revealTile();
        EventBus.getInstance().notifyObservers("Bomb revealed!");
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