package minesweeper;

public abstract class Tile{
        private boolean revealedTile;
        private boolean flaggedTile;
        private int tileNumber;

        public boolean isRevealedTile(){
            return revealedTile;
        }

        public boolean isFlaggedTile(){
            return flaggedTile;
        }

        public void revealTile(){
            if (!flaggedTile){
                revealedTile = true;
            }
        }

        public int  getTileNumber(){
            return 0;
        }

    public boolean isBomb()
    {
        return false;
    }

    public boolean isNumberTile()
    {
        return false;
    }
}
