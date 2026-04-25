package minesweeper;

public class DoesNothingTestPlayer extends Player
{
    @Override
    public PlayerAction getAction() {
        return PlayerAction.QUIT;
    }
}
