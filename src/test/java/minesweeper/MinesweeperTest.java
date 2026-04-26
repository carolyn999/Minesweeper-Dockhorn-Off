package minesweeper;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class MinesweeperTest {
    @Test
    public void gameWinTest() {
        //all non-bomb tiles revealed = gameWon
        Map map = Map.getBuilder(new TileFactory())
                .useAdjacencyPattern("Normal")
                .create3x3Grid()
                .placeBomb(0, 0)
                .build();

        //reveal all non-bomb tiles manually
        map.revealTile(0, 1); map.revealTile(0, 2);
        map.revealTile(1, 0); map.revealTile(1, 1); map.revealTile(1, 2);
        map.revealTile(2, 0); map.revealTile(2, 1); map.revealTile(2, 2);

        Minesweeper minesweeper = new Minesweeper(map, new DoesNothingTestPlayer());

        assertTrue(minesweeper.isOver());
        assertTrue(minesweeper.isWon());
    }

    @Test
    public void bombLoseTest() {
        //revealing bomb = gameOver
        Map map = Map.getBuilder(new TileFactory())
                .useAdjacencyPattern("Normal")
                .create3x3Grid()
                .placeBomb(1, 1)
                .build();

        map.revealTile(1, 1); //reveal bomb

        Minesweeper minesweeper = new Minesweeper(map, new DoesNothingTestPlayer());

        assertTrue(minesweeper.isOver());
        assertFalse(minesweeper.isWon());
    }

    @Test
    public void gameNotOverTest() {
        //no bombs revealed, safe tiles remain so game is not won or over
        Map map = Map.getBuilder(new TileFactory())
                .useAdjacencyPattern("Normal")
                .create3x3Grid()
                .placeBomb(0, 0)
                .build();

        map.revealTile(1, 1); //reveal one safe tile, others still hidden

        Minesweeper minesweeper = new Minesweeper(map,new DoesNothingTestPlayer());

        assertFalse(minesweeper.isWon());
        assertFalse(minesweeper.isOver());
    }
}
