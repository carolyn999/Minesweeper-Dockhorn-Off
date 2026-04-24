package minesweeper;

import java.util.ArrayList;
import java.util.List;

public class EventBus implements ITilesGame
{
    private final List<TilesObserver> observers = new ArrayList<>();
    private static EventBus instance;

    private EventBus()
    {

    }

    public static EventBus getInstance()
    {
        if(instance==null)
        {
            instance = new EventBus();
        }

        return instance;
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

    public void notifyObservers(String event)
    {
        for(TilesObserver observer:observers)
        {
            observer.updateObservers(event);
        }
    }
}
