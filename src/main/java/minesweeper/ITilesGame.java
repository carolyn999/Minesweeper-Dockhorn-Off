package minesweeper;

public interface ITilesGame
{
    void attach(TilesObserver observer);
    void detach(TilesObserver observer);
}
