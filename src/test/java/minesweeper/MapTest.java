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
        assertNotNull(map.getAdjacencyPattern());

    }
    @Test
    public void mapToStringTest() {
        Map map = Map.getBuilder(new TileFactory())
                .create3x3Grid()
                .useAdjacencyPattern("Normal")
                .build();

        assertEquals("|.|.|.|\n|.|.|.|\n|.|.|.|", map.toString());
    }


    @Test
    public void unrevealedEmptyTileToStringTest() {
        Tile tile = new EmptyTile();

        assertEquals(".", tile.toString());
    }

    @Test
    public void flaggedTileToStringTest() {
        Tile tile = new EmptyTile();
        tile.toggleFlag();

        assertEquals("F", tile.toString());
    }

    @Test
    public void revealedNumberTileToStringTest() {
        Tile tile = new EmptyTile();
        tile.setTileNumber(2);
        tile.revealTile();

        assertEquals("2", tile.toString());
    }

    @Test
    public void unrevealedBombTileToStringTest() {
        Tile tile = new BombTile();

        assertEquals(".", tile.toString());
    }

    @Test
    public void revealedBombTileToStringTest() {
        Tile tile = new BombTile();
        tile.revealTile();

        assertEquals("B", tile.toString());
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

}
