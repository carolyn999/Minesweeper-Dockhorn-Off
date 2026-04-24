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
        Tile baseTile = new EmptyTile();
        baseTile.setTileNumber(2);
        Tile numberTile = new NumberTile(baseTile);
        assertFalse(numberTile.isBomb());
        assertEquals(2, numberTile.getTileNumber());
    }

    // Moved from MapTest
    @Test
    public void unrevealedEmptyTileToStringTest() {
        Tile tile = new EmptyTile();

        assertEquals(".", tile.toString());
    }

    // Should use flag tile class instead of empty tile
    @Test
    public void flaggedTileToStringTest() {
        Tile baseTile = new EmptyTile();
        Tile tile = new FlagTile(baseTile);
        //tile.toggleFlag();

        assertEquals("F", tile.toString());
    }

    @Test
    public void numberTileToStringTest() {
        Tile baseTile = new EmptyTile();
        baseTile.setTileNumber(2);
        baseTile.revealTile();
        Tile numberTile = new NumberTile(baseTile);

        assertTrue(numberTile.isRevealedTile());
        assertEquals("2", numberTile.toString());
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
