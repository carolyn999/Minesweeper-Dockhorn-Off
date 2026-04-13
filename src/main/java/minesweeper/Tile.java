package minesweeper;

public abstract class Tile{
        private boolean revealedTile;
        private boolean flaggedTile;
        private int tileNumber;
        private int row;
        private int col;

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

        void setPosition(int row, int col){
            this.row = row;
            this.col = col;
        }

        public int getRow(){
            return row;
        }

        public int getColumn(){
            return col;
        }

        public boolean isBomb()
        {
            return false;
        }

        public int  getTileNumber(){
            return tileNumber;
        }
}
