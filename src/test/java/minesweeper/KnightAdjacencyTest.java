package minesweeper;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class KnightAdjacencyTest
{
    @Test
    void testKnightAdjacency()
    {
        Map map = Map.getBuilder(new TileFactory())
                .useAdjacencyPattern("Knight")
                .create3x3Grid()
                .placeBomb(0,0)
                .placeBomb(0,2)
                .placeBomb(2,0)
                .build();

        assertEquals(2,map.getTile(1,2).getTileNumber());
    }
}
