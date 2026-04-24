package minesweeper;

import org.junit.jupiter.api.Test;

public class ObserverTest
{
    @Test
    public void visualizeMap() {
        Map map = Map.getBuilder(new TileFactory())
                .useAdjacencyPattern("Normal")
                .createSquareGrid(5)
                .placeBombs(4)
                .build();

        Minesweeper minesweeper = new Minesweeper(map);
        VisualMinesweeperObserver observer = new VisualMinesweeperObserver();
        minesweeper.attach(observer);
        minesweeper.playGame();
        minesweeper.detach(observer);
        //observer.updateObserver("Starter map");
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

        Minesweeper minesweeper = new Minesweeper(map);
        VisualMinesweeperObserver observer = new VisualMinesweeperObserver();

        minesweeper.attach(observer);
        // If we do player as dependency injection, we could make computer run test players to do things for the tests
        // For now, this should work without manually sending updates to the observer in the test.
        //minesweeper.playGame();
        map.revealTile(2, 2);
        //observer.updateObserver("Revealed (2,2)");

        map.flagTile(0, 0);
        //observer.updateObserver("Flagged (0,0)");

        //reveal a bomb
        map.revealTile(4, 1);
        //observer.updateObserver("Revealed (4,1)");

        minesweeper.detach(observer);
    }


}
