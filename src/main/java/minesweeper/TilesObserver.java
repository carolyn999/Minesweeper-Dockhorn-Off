package minesweeper;

public interface TilesObserver {
    // Update observers every time a tile is revealed or flagged and at end of game.
    // Maybe do singleton eventBus like Polymorphia?
    void updateObservers(String event);
}
