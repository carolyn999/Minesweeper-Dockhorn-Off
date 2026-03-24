package minesweeper;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class MapTest {
    @Test
    public void createMapTest(){
        //successfully creates a map
        TileFactory tileFactory = new TileFactory();

        Map map = Map.getBuilder(tileFactory).rows(3).cols(3).useAdjacencyPattern("Normal").build();

        assertNotNull(map);
        assertEquals(3, map.getRows());
        assertEquals(3, map.getCols());
        assertNotNull(map.getAdjacencyPattern());

    }

}
