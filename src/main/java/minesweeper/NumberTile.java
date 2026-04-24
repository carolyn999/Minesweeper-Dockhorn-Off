package minesweeper;

public class NumberTile extends DecoratedTile {

    public NumberTile(Tile baseTile){
        super(baseTile);
    }

    @Override
    public String toString()
    {
        return String.valueOf(getBaseTile().getTileNumber());
    }
}
