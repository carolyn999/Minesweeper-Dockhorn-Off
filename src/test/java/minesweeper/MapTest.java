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


}
