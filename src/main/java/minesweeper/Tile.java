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
        public abstract boolean isBomb();

        public int  getTileNumber(){
            return 0;
        }

        @Override
        public Tile createTile(boolean isBomb, int adjacentBombs){
            if (isBomb){
                return new BombTile();
            }
            return new NumberTile(adjacentBombs);
        }
}
