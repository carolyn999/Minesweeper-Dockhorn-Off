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
    public void bombLoseTest() {
        //bomb goes off and player loses
    }

    @Test
    public void numberTileTest(){
        //when clicking a free tile, a number shows up with the adjacent bombs
        NumberTile tile = new NumberTile(2);
        assertFalse(tile.isBomb());
        assertEquals(2, tile.getTileNumber());
    }



}
