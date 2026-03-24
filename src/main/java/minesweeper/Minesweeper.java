package minesweeper;

public class Minesweeper implements TilesObserver {
    private boolean gameWon;
    private boolean gameOver;

    @Override
    public void onGameWon(){
        gameWon = true;
        gameOver = true;
    }

    @Override
    public void onGameOver(){
        gameWon = false;
        gameOver = true;
    }
}
