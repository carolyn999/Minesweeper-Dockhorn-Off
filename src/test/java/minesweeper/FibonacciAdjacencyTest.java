package minesweeper;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FibonacciAdjacencyTest
{
    @Test
    void testFibonacciAdjacency()
    {
        // |b| | | | |
        // | | | | | |
        // |b| | | |b|
        // | |b| | | |
        // | | | | | |

        Map map = Map.getBuilder(new TileFactory())
                .useAdjacencyPattern("Fibonacci")
                .createSquareGrid(5)
                .placeBomb(0,0)
                .placeBomb(2,4)
                .placeBomb(3,1)
                .placeBomb(2,0)
                .build();

        assertEquals(4,map.getTile(2,1).getTileNumber());
    }
}
