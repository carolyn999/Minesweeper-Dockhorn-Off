package minesweeper;

import java.util.ArrayList;
import java.util.List;

public class KnightAdjacency extends AdjacencyPattern
{
    public KnightAdjacency(Map map)
    {
        super(map);
    }

    @Override
    public int countAdjacentBombTiles(Tile tile)
    {
        int bombCount = 0;
        int[] tileLocation = getMap().getTileLocation(tile);
        List<int[]> moveLocations = getMoveLocations(tileLocation);

        for(int[] moveLocation:moveLocations)
        {
            if(isBombAtLocation(moveLocation))
            {
                bombCount++;
            }
        }

        return bombCount;
    }

    @Override
    public String getName()
    {
        return "Knight Adjacency";
    }

    private List<int[]> getMoveLocations(int[] tileLocation)
    {
        // Hardcoded due to the complex shape of knight moves.
        List<int[]> moveLocations = new ArrayList<>();
        int[] upLeftMoveLocation = new int[]{tileLocation[0]-1,tileLocation[1]+2};
        moveLocations.add(upLeftMoveLocation);
        int[] upRightMoveLocation = new int[]{tileLocation[0]+1,tileLocation[1]+2};
        moveLocations.add(upRightMoveLocation);
        int[] leftUpMoveLocation = new int[]{tileLocation[0]-2,tileLocation[1]+1};
        moveLocations.add(leftUpMoveLocation);
        int[] leftDownMoveLocation = new int[]{tileLocation[0]-2,tileLocation[1]-1};
        moveLocations.add(leftDownMoveLocation);
        int[] downLeftMoveLocation = new int[]{tileLocation[0]-1,tileLocation[1]-2};
        moveLocations.add(downLeftMoveLocation);
        int[] downRightMoveLocation = new int[]{tileLocation[0]+1,tileLocation[1]-2};
        moveLocations.add(downRightMoveLocation);
        int[] rightUpMoveLocation = new int[]{tileLocation[0]+2,tileLocation[1]+1};
        moveLocations.add(rightUpMoveLocation);
        int[] rightDownMoveLocation = new int[]{tileLocation[0]+2,tileLocation[1]-1};
        moveLocations.add(rightDownMoveLocation);
        return moveLocations;
    }
}
