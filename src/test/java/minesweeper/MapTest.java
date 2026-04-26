package minesweeper;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class MapTest {
    @Test
    public void createMapTest(){
        //successfully creates a map
        TileFactory tileFactory = new TileFactory();

        Map map = Map.getBuilder(tileFactory).setRows(3).setCols(3).useAdjacencyPattern("Normal").build();

        assertNotNull(map);
        assertEquals(3, map.getRows());
        assertEquals(3, map.getCols());
        assertNotNull(map.getAdjacencyPatternName());

    }

    // Move tile toString tests out of MapTest
    @Test
    public void mapToStringTest() {
        Map map = Map.getBuilder(new TileFactory())
                .create3x3Grid()
                .useAdjacencyPattern("Normal")
                .build();

        assertEquals("|.|.|.|\n|.|.|.|\n|.|.|.|", map.toString());
    }

    @Test
    public void revealedMapToStringTest() {
        Map map = Map.getBuilder(new TileFactory())
                .useAdjacencyPattern("Normal")
                .create3x3Grid()
                .placeBomb(0, 0)
                .build();

        map.revealTile(1, 1);

        assertEquals("|.|.|.|\n|.|1|.|\n|.|.|.|", map.toString());
    }

    @Test
    public void flaggedMapTileToStringTest() {
        Map map = Map.getBuilder(new TileFactory())
                .useAdjacencyPattern("Normal")
                .create3x3Grid()
                .build();

        map.flagTile(0, 1);

        assertEquals("|.|F|.|\n|.|.|.|\n|.|.|.|", map.toString());
    }

    @Test
    public void testBuilderBombPlacements()
    {
        // This test is making sure that random bomb assignment is not prevented
        // by manually trying to put a bomb in the same place multiple times.
        Map map = Map.getBuilder(new TileFactory())
                .setRows(2)
                .setCols(2)
                .placeBomb(0,0)
                .placeBomb(0,1)
                .placeBomb(1,0)
                .placeBomb(0,0)
                .placeBomb()
                .build();

        // If all tiles are bomb tiles as intended, then it will count as having all safe tiles revealed.
        assertTrue(map.allSafeTilesRevealed());
    }

    @Test
    public void testBuilderAlwaysProducesValidMap()
    {
        Map map = Map.getBuilder(new TileFactory()).build();
        assertEquals(Map.DEFAULT_MAP_ROWS,map.getRows());
        assertEquals(Map.DEFAULT_MAP_COLS,map.getCols());

        assertTrue(map.getAdjacencyPatternName().contains("Normal"));

        // Reveal all tiles in order to check that a bomb has been placed using the hasRevealedBomb method.
        for(int row=0;row<3;row++)
        {
            for(int col=0;col<3;col++)
            {
                map.revealTile(row,col);
            }
        }

        assertTrue(map.hasRevealedBomb());
    }

}
