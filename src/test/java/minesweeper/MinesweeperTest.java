package minesweeper;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class MinesweeperTest {
    @Test
    public void gameWin(){
        //game detects win and exits when all non-bomb tiles are revealed
        Map map = Map.getBuilder(new TileFactory())
                .useAdjacencyPattern("Knight")
                .create3x3Grid()
                .placeBomb(0,0)
                .placeBomb(0,2)
                .placeBomb(2,0)
                .build();

        Minesweeper minesweeper = new Minesweeper(map);
        minesweeper.playGame();
        assertTrue(minesweeper.isOver());
    }

    @Test
    public void bombLoseTest() {
        //bomb goes off and player loses
    }
}
