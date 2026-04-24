package minesweeper;

public class VisualMinesweeperObserver implements TilesObserver
{
    private final Map map;

    public VisualMinesweeperObserver(Map map)
    {
        this.map = map;
    }

    //renamed method for good naming conventions (maybe?)
    @Override
    public void updateObservers(String event)
    {
        System.out.println("\n[" + event + "]");
        System.out.println(map.toString());
        System.out.println();
    }

}
