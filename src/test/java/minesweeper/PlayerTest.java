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
        PlayerCommand command = player.getCommand();

        assertFalse(command.isQuit());
        assertEquals("reveal at row 1, col 2", command.toString());
    }

    @Test
    void testFlagCommandInput()
    {
        Scanner scanner = new Scanner("f\n0\n1\n");
        Player player = new Player(scanner);
        PlayerCommand command = player.getCommand();

        assertFalse(command.isQuit());
        assertEquals("flag at row 0, col 1", command.toString());
    }

    @Test
    void testQuitCommandInput()
    {
        Scanner scanner = new Scanner("q\n");
        Player player = new Player(scanner);
        PlayerCommand command = player.getCommand();
        assertTrue(command.isQuit());
    }

    @Test
    void testRevealCommandRevealsTile()
    {
        Map map = Map.getBuilder(new TileFactory()).useAdjacencyPattern("Normal").create3x3Grid().placeBomb(0, 0).build();

        Scanner scanner = new Scanner("r\n1\n1\n");
        Player player = new Player(scanner);

        PlayerCommand command = player.getCommand();
        command.execute(map);

        assertTrue(map.getTile(1, 1).isRevealedTile());
        assertEquals("1", map.getTile(1, 1).toString());
    }

    @Test
    void testFlagCommandFlagsTile()
    {
        Map map = Map.getBuilder(new TileFactory()).useAdjacencyPattern("Normal").create3x3Grid().build();

        Scanner scanner = new Scanner("f\n0\n1\n");
        Player player = new Player(scanner);

        PlayerCommand command = player.getCommand();
        command.execute(map);

        assertTrue(map.getTile(0, 1).isFlaggedTile());
        assertEquals("F", map.getTile(0, 1).toString());
    }
}
