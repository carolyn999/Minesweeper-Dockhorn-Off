package minesweeper;

//cleaner implementation for playgame

public class PlayerCommand {
    private final String action;
    private final int row;
    private final int col;

    public PlayerCommand(String action, int row, int col)
    {
        this.action = action;
        this.row = row;
        this.col = col;
    }

    public boolean isQuit()
    {
        return action.equals("quit");
    }

    public void execute(Map map)
    {
        if (action.equals("reveal"))
        {
            map.revealTile(row, col);
        }
        else if (action.equals("flag"))
        {
            map.flagTile(row, col);
        }
    }

    @Override
    public String toString()
    {
        return action + " at row " + row + ", col " + col;
    }
}
