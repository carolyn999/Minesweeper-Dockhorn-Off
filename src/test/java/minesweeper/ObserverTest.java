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

}
