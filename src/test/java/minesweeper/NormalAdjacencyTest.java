package minesweeper;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class NormalAdjacencyTest {
    @Test
    public void normalAdjacencyPatternsTest(){
        //creates map with normal adjacency pattern
        Map map = Map.getBuilder(new TileFactory("Normal"))
                .create3x3Grid()
                .placeBomb(0,1)
                .placeBomb(1,2)
                .placeBomb(2,0)
                .build();

        assertEquals(3,map.getTile(1,1).countAdjacentBombs());
    }
}
