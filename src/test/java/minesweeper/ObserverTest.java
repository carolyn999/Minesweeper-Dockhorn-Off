package minesweeper;

import org.junit.jupiter.api.Test;

public class ObserverTest
{
    // Note: the results of these tests are not easily checked with assert statements,
    // so results must be verified by viewing the actual output in the terminal after they are run

    @Test
    public void visualizeMap() {
        Map map = Map.getBuilder(new TileFactory())
                .useAdjacencyPattern("Normal")
                .createSquareGrid(5)
                .placeBombs(4)
                .build();

        Minesweeper minesweeper = new Minesweeper(map, new DoesNothingTestPlayer());
        VisualMinesweeperObserver observer = new VisualMinesweeperObserver();
        minesweeper.attach(observer);
        minesweeper.playGame();
        minesweeper.detach(observer);
    }

    @Test
    public void visualizeOnUpdate() {
        Map map = Map.getBuilder(new TileFactory())
                .useAdjacencyPattern("Normal")
                .createSquareGrid(5)
                .placeBomb(0, 0)
                .placeBomb(2, 3)
                .placeBomb(4, 1)
                .build();

        Minesweeper minesweeper = new Minesweeper(map, new DoesNothingTestPlayer());
        VisualMinesweeperObserver observer = new VisualMinesweeperObserver();

        minesweeper.attach(observer);

        // Reveal an empty tile (becomes number tile)
        map.revealTile(2, 2);

        // Flag a tile
        map.flagTile(0, 0);

        //reveal a bomb
        map.revealTile(4, 1);

        minesweeper.detach(observer);
    }
}
