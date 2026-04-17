package minesweeper;

public class Minesweeper implements ITilesGame {
    private boolean gameWon;
    private boolean gameOver;
    private Map map;

    public Minesweeper(Map map)
    {
        this.map = map;
    }

    @Override
    public void attach()
    {

    }

    @Override
    public void detach()
    {

    }

    private void checkGameState() {
        //check if any bomb is revealed first
        for (int row = 0; row < map.getRows(); row++) {
            for (int col = 0; col < map.getCols(); col++) {
                Tile tile = map.getTile(row, col);
                if (tile.isBomb() && tile.isRevealedTile()) {
                    gameOver = true;
                    return;
                }
            }
        }
        //check if all safe tiles are revealed second
        for (int row = 0; row < map.getRows(); row++) {
            for (int col = 0; col < map.getCols(); col++) {
                Tile tile = map.getTile(row, col);
                if (!tile.isBomb() && !tile.isRevealedTile()) {
                    return;
                }
            }
        }
        gameWon = true;
    }

    public void playGame()
    {
        checkGameState();
    }

    public boolean isOver()
    {
        return gameOver;
    }

    public boolean isWon(){
        return gameWon;
    }
}
