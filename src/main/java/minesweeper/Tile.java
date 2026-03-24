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

        @Override
        public Tile createTile(boolean isBomb, int adjacentBombs){
            if (isBomb){
                return new BombTile();
            }
            return new NumberTile(adjacentBombs);
        }
}
