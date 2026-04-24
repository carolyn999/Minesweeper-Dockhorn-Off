package minesweeper;

public abstract class Tile{
        private boolean revealedTile;
        private int tileNumber;

        // Maybe use strategy pattern instead of inheritance to show numbers when the tile is revealed?
        public boolean isRevealedTile(){
            return revealedTile;
        }

        public boolean isFlaggedTile(){
            return false;
        }

        public void revealTile(){
            revealedTile = true;
        }

        protected void setTileNumber(int adjacentBombs)
        {
            this.tileNumber = adjacentBombs;
        }

        public boolean isBomb()
        {
            return false;
        }

        public int  getTileNumber(){
            return tileNumber;
        }

        protected Tile getBaseTile()
        {
            return this;
        }

        //added this so tiles can inherit toString isFlaggedTile and the other thing
        // so it only has to override the return value at the end.

        protected String getRevealedDisplayValue() {
            return String.valueOf(getTileNumber());
        }

        @Override
        public String toString(){
            if (!isRevealedTile()){
                return ".";
            }
            return getRevealedDisplayValue();
            //return String.valueOf(getTileNumber());
        }
}
