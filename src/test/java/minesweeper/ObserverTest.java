package minesweeper;

import org.junit.jupiter.api.Test;

public class ObserverTest
{
    @Test
    void testObserverUpdates()
    {
        // Write a test for observer updates.
    }

    @Test
    public void visualizeMap() {
        Map map = Map.getBuilder(new TileFactory())
                .useAdjacencyPattern("Normal")
                .createSquareGrid(5)
                .placeBombs(4)
                .build();

        VisualMinesweeperObserver observer = new VisualMinesweeperObserver(map);
        observer.updateObservers("Starter map");
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

        VisualMinesweeperObserver observer = new VisualMinesweeperObserver(map);

        map.revealTile(2, 2);
        observer.updateObservers("Revealed (2,2)");

        map.flagTile(0, 0);
        observer.updateObservers("Flagged (0,0)");

        //reveal a bomb
        map.revealTile(4, 1);
        observer.updateObservers("Revealed (4,1)");
    }


}
