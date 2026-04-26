package minesweeper;

public abstract class Tile{
        private boolean revealedTile;
        private int tileNumber;

        public boolean isRevealedTile(){
            return revealedTile;
        }

        public boolean isFlaggedTile(){
            return false;
        }

        public void revealTile(){
            if(isRevealedTile())
            {
                EventBus.getInstance().notifyObservers("Tile already revealed, cannot reveal again.");
            }
            else
            {
                revealedTile = true;
                EventBus.getInstance().notifyObservers("Tile revealed successfully!");
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

        protected Tile getBaseTile()
        {
            return this;
        }

        @Override
        public String toString(){
            return ".";
        }
}
