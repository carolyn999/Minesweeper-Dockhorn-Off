package minesweeper;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TileTest {

    @Test
    public void bombTileIsBomb(){
        Tile tile = new BombTile();
        assertTrue(tile.isBomb());
    }

    @Test
    public void numberTileTest(){
        //when clicking a free tile, a number shows up on neighboring tiles with the adjacent bombs
        NumberTile tile = new NumberTile(2);
        assertFalse(tile.isBomb());
        assertEquals(2, tile.getTileNumber());
    }

    // Moved from MapTest
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

}
