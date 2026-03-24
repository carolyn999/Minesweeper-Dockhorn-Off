# Minesweeper-Dockhorn-Off
Carolyn Dockhorn, Kristin Off

Minesweeper project for object-oriented programming. 

# Design Patterns

- Observer Pattern
  - The Observer pattern will be used so the game state/board can notify objects when a tile changes its state. For example, when a tile is revealed, triggers a bomb, or is flagged, observers such as the game/player will be notified and updated. We are creating this in TileObserver, and it will be implemented in the Minesweeper class. Furthermore, observers will be added in the Map class. 

- Builder Pattern
  - We will use the Builder Pattern to construct complex maps of tiles for the game to be played in. For example, it can create a board step by step by setting the number of rows, columns, adjacency patterns, and bomb locations without generating too many constructors. We're using the builder pattern to prevent having large, confusing constructor. We are implementing this in the Map class.

- Factory Pattern
  - We will use the Factory pattern to create multiple types of tiles instead of directly creating each object. Depending on the location and bomb layout, the factory can either return a BombTile or a NumberTile. This keeps object creation in one place and makes the code easier to maintain/expand. We are implementing this in TileFactory.

# Foundational Classes

- Minesweeper
- Map
- Tile
- TileFactory
- TileObserver
- Subclasses of Tile:
  - BombTile
  - FlagTile (Marker placed by the user on a tile to indicate that it is suspected to be a bomb)
  - NumberTile (Number of tiles considered adjacent under the current adjacency pattern)
- Adjacency Patterns 
  - Modes: Normal, Knight, Fibonacci

