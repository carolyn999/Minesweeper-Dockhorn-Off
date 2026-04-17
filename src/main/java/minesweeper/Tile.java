package minesweeper;

public abstract class Tile{
        private boolean revealedTile;
        private boolean flaggedTile;
        private int tileNumber;

        // Maybe use strategy pattern instead of inheritance to show numbers when the tile is revealed?
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

        public void toggleFlag(){
            if (!revealedTile){
                flaggedTile = !flaggedTile;
            }
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

        //added this so tiles can inherit toString isFlaggedTile and the other thing
        // so it only has to override the return value at the end.

        protected String getRevealedDisplayValue() {
            return String.valueOf(getTileNumber());
        }

        @Override
        public String toString(){
            if (isFlaggedTile()){
                return "Flagged";
            }
            if (!isRevealedTile()){
                return ".";
            }
            return getRevealedDisplayValue();
            //return String.valueOf(getTileNumber());
        }
}
