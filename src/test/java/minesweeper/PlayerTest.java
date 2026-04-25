package minesweeper;

import org.junit.jupiter.api.Test;
import java.util.Scanner;
import static org.junit.jupiter.api.Assertions.*;

public class PlayerTest {

    @Test
    void testRevealCommandInput()
    {
        Scanner scanner = new Scanner("r\n1\n2\n");
        Player player = new Player(scanner);
        PlayerAction action = player.getAction();

        int row = player.getRow();
        int col = player.getCol();

        assertEquals(PlayerAction.REVEAL, action);
        assertEquals(1, row);
        assertEquals(2, col);
    }

    @Test
    void testFlagCommandInput()
    {
        Scanner scanner = new Scanner("f\n0\n1\n");
        Player player = new Player(scanner);
        PlayerAction action = player.getAction();

        int row = player.getRow();
        int col = player.getCol();

        assertEquals(PlayerAction.FLAG, action);
        assertEquals(0, row);
        assertEquals(1, col);
    }

    @Test
    void testQuitCommandInput()
    {
        Scanner scanner = new Scanner("q\n");
        Player player = new Player(scanner);
        PlayerAction action = player.getAction();

        assertEquals(PlayerAction.QUIT, action);
    }

    @Test
    void testRevealInputCanRevealTile()
    {
        Map map = Map.getBuilder(new TileFactory()).useAdjacencyPattern("Normal").create3x3Grid().placeBomb(0, 0).build();

        Scanner scanner = new Scanner("r\n1\n1\n");
        Player player = new Player(scanner);
        PlayerAction action = player.getAction();

        int row = player.getRow();
        int col = player.getCol();

        if (action == PlayerAction.REVEAL)
        {
            map.revealTile(row, col);
        }

        assertTrue(map.getTile(1, 1).isRevealedTile());
        assertEquals("1", map.getTile(1, 1).toString());
    }

    @Test
    void testFlagInputCanFlagTile()
    {
        Map map = Map.getBuilder(new TileFactory()).useAdjacencyPattern("Normal").create3x3Grid().build();

        Scanner scanner = new Scanner("f\n0\n1\n");
        Player player = new Player(scanner);
        PlayerAction action = player.getAction();

        int row = player.getRow();
        int col = player.getCol();

        if (action == PlayerAction.FLAG)
        {
            map.flagTile(row, col);
        }

        assertTrue(map.getTile(0, 1).isFlaggedTile());
        assertEquals("F", map.getTile(0, 1).toString());
    }

    @Test
    void testFullWordsAlsoWork()
    {
        Scanner scanner = new Scanner("reveal\n2\n0\n");
        Player player = new Player(scanner);
        PlayerAction action = player.getAction();

        int row = player.getRow();
        int col = player.getCol();

        assertEquals(PlayerAction.REVEAL, action);
        assertEquals(2, row);
        assertEquals(0, col);
    }

    @Test
    void testInvalidActionThenValidAction()
    {
        Scanner scanner = new Scanner("abcdefg\nflag\n0\n2\n");
        Player player = new Player(scanner);
        PlayerAction action = player.getAction();

        int row = player.getRow();
        int col = player.getCol();

        assertEquals(PlayerAction.FLAG, action);
        assertEquals(0, row);
        assertEquals(2, col);

    }
}
