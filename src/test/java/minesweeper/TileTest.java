package minesweeper;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TileTest {
    TileFactory tileFactory = new TileFactory();

    @Test
    public void bombTileIsBomb(){
        Tile tile = tileFactory.createTile(true);
        assertTrue(tile.isBomb());
    }

    @Test
    public void numberTileTest(){
        //when clicking a free tile, a number shows up on neighboring tiles with the adjacent bombs
        Tile baseTile = tileFactory.createTile(false);
        baseTile.setTileNumber(2);
        Tile numberTile = tileFactory.createNumberTile(baseTile);
        assertFalse(numberTile.isBomb());
        assertEquals(2, numberTile.getTileNumber());
    }

    @Test
    public void unrevealedEmptyTileToStringTest() {
        Tile tile = tileFactory.createTile(false);

        assertEquals(".", tile.toString());
    }

    @Test
    public void flaggedTileToStringTest() {
        Tile baseTile = tileFactory.createTile(false);
        Tile tile = tileFactory.createFlagTile(baseTile);

        assertEquals("F", tile.toString());
    }

    @Test
    public void numberTileToStringTest() {
        Tile baseTile = tileFactory.createTile(false);
        baseTile.setTileNumber(2);
        baseTile.revealTile();
        Tile numberTile = tileFactory.createNumberTile(baseTile);

        assertTrue(numberTile.isRevealedTile());
        assertEquals("2", numberTile.toString());
    }

    @Test
    public void unrevealedBombTileToStringTest() {
        Tile tile = tileFactory.createTile(true);

        assertEquals(".", tile.toString());
    }

    @Test
    public void revealedBombTileToStringTest() {
        Tile tile = tileFactory.createTile(true);
        tile.revealTile();

        assertEquals("B", tile.toString());
    }

}
