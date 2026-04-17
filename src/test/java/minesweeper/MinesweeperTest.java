package minesweeper;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class MinesweeperTest {
//    @Test
//    public void gameWin(){
//        //game detects win and exits when all non-bomb tiles are revealed
//        Map map = Map.getBuilder(new TileFactory())
//                .useAdjacencyPattern("Knight")
//                .create3x3Grid()
//                .placeBomb(0,0)
//                .placeBomb(0,2)
//                .placeBomb(2,0)
//                .build();
//
//        Minesweeper minesweeper = new Minesweeper(map);
//        minesweeper.playGame();
//        assertTrue(minesweeper.isOver());
//    }

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

        Minesweeper minesweeper = new Minesweeper(map);
        minesweeper.playGame();

        assertTrue(minesweeper.isWon());
        assertFalse(minesweeper.isOver());
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

        Minesweeper minesweeper = new Minesweeper(map);
        minesweeper.playGame();

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

        Minesweeper minesweeper = new Minesweeper(map);
        minesweeper.playGame();

        assertFalse(minesweeper.isWon());
        assertFalse(minesweeper.isOver());
    }
}
