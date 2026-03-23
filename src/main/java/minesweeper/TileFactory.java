package minesweeper;

public interface TileFactory {
    Tile createTile(boolean isBomb, int adjacentBombs);
}
