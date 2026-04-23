package minesweeper;

import java.util.ArrayList;
import java.util.List;

public class Minesweeper implements ITilesGame {
    private final Map map;
    private final List<TilesObserver> observers;

    public Minesweeper(Map map)
    {
        this.map = map;
        observers = new ArrayList<>();
    }

    @Override
    public void attach(TilesObserver observer)
    {
        observers.add(observer);
    }

    @Override
    public void detach(TilesObserver observer)
    {
        observers.remove(observer);
    }

    private void notifyObservers(String event)
    {
        for(TilesObserver observer:observers)
        {
            observer.update(event);
        }
    }

    public void playGame()
    {
        /* Gameplay loop
        while(!isOver())
        {
            // Maybe add a player class with this method to control user interaction
            // and potentially have the possibility of computer player?
            playTurn();
        }
        */
    }

    public boolean isOver()
    {
        return map.hasRevealedBomb()||map.allSafeTilesRevealed();
    }

    public boolean isWon(){
        return !map.hasRevealedBomb() && map.allSafeTilesRevealed();
    }
}
