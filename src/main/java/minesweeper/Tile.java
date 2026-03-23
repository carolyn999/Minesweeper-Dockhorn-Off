package minesweeper;

public abstract class Tile{
        private boolean revealedTile;
        private boolean flaggedTile;

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
        public abstract boolean isBomb();

        public int  getTileNumber(){
            return 0;
        }
}
