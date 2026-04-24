package minesweeper;

public class VisualMinesweeperObserver implements TilesObserver
{
    //private final Map map;

    // Encapsulation/abstraction concern: if this is observing a minesweeper game, it should not need to know about the map structure.
    // Instead, we can send the string representation of the map as an event in update.
    //public VisualMinesweeperObserver(Map map)
    //{
    //    this.map = map;
    //}

    //renamed method for good naming conventions (maybe?)
    // Fair choice, switched to singular instead of plural to avoid confusion.
    @Override
    public void updateObserver(String event)
    {
        System.out.println("\n[" + event + "]\n");
        //System.out.println(map.toString());
        //System.out.println();
    }

}
