package minesweeper;

import java.util.ArrayList;
import java.util.List;

public class FibonacciAdjacency extends AdjacencyPattern
{
    public FibonacciAdjacency(Map map)
    {
        super(map);
    }

    @Override
    public String getName()
    {
        return "Fibonacci Adjacency";
    }

    @Override
    public int countAdjacentBombTiles(Tile tile) {
        // This works, but could probably use more functional decomposition for readability
        // Use the map bounds to limit to a more reasonable set of fibonacci values without calculating exact limits from the tile.
        List<Integer> horizontalFibonacciValues = getFibonacciNumbers(getMap().getRows());
        List<Integer> verticalFibonacciValues = getFibonacciNumbers(getMap().getCols());
        int[] tileLocation = getMap().getTileLocation(tile);
        int bombCount = 0;

        // Check + and - for each vertical fibonacci number
        for(int number:verticalFibonacciValues)
        {
            if(isBombAtLocation(tileLocation[0],tileLocation[1]+number))
            {
                bombCount++;
            }
            if(isBombAtLocation(tileLocation[0],tileLocation[1]-number))
            {
                bombCount++;
            }
        }

        for(int number:horizontalFibonacciValues)
        {
            // Check + and - for each horizontal fibonacci number
            if(isBombAtLocation(tileLocation[0]+number,tileLocation[1]))
            {
                bombCount++;
            }
            if(isBombAtLocation(tileLocation[0]-number,tileLocation[1]))
            {
                bombCount++;
            }

            // Check valid diagonals
            if(verticalFibonacciValues.contains(number))
            {
                if(isBombAtLocation(tileLocation[0]+number,tileLocation[1]+number))
                {
                    bombCount++;
                }
                if(isBombAtLocation(tileLocation[0]-number,tileLocation[1]+number))
                {
                    bombCount++;
                }
                if(isBombAtLocation(tileLocation[0]+number,tileLocation[1]-number))
                {
                    bombCount++;
                }
                if(isBombAtLocation(tileLocation[0]-number,tileLocation[1]-number))
                {
                    bombCount++;
                }
            }
        }

        return bombCount;
    }

    private List<Integer> getFibonacciNumbers(int maxValue)
    {
        int last = 1;
        int last_2 = 1;
        List<Integer> fibonacciNumbers = new ArrayList<>();
        fibonacciNumbers.add(1);
        int sum = last+last_2;
        while(sum<=maxValue)
        {
            fibonacciNumbers.add(sum);
            last_2 = last;
            last = sum;
            sum = last+last_2;
        }
        return fibonacciNumbers;
    }
}
