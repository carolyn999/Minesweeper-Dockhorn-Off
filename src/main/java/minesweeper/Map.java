package minesweeper;

public class Map
{
    private Map(){}

    public static MapBuilder getBuilder(TileFactory tileFactory)
    {
        return new MapBuilder(tileFactory);
    }

    public static class MapBuilder
    {
        private TileFactory tileFactory;

        public MapBuilder(TileFactory tileFactory)
        {
            this.tileFactory = tileFactory;
        }
    }
}
