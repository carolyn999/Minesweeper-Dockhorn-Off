# Minesweeper-Dockhorn-Off
Carolyn Dockhorn, Kristin Off

This project is an object-oriented implementation of the game Minesweeper. The game allows the player to create a custom board, choose an adjacency pattern, and choose the amount of random bombs placed on the map. 

The user is able to play through a terminal-based interface. The implementation uses several object-oriented patterns, including: Factory, Builder, Observer, Singleton, and Decorator.

# How the Game Works

When the program starts, the user is asked a range of questions, allowing them to customize the game. The user is asked to provide the adjacency pattern, size of the grid, and number of bombs.

The player can enter shortened commands, such as:
- 'r' which reveals a chosen tile on the map
- 'f' which flags chosen tile on the map
- 'q' which exits the game

The game checks for invalid input, ensuring the user is playing in a fair environment and not breaking the game. 

### `Main`
The `Main` class is the entry point of the game. It handles the initial game setup by asking the user for the adjacency pattern, grid size, and number of bombs. After collecting these options, it builds the map, creates the player, creates the `Minesweeper` game object, attaches the visual observer, and starts the game.

### `Minesweeper`

The `Minesweeper` class controls the main gameplay loop. It stores the `Map` and `Player`, asks the player for actions, checks whether the selected tile location is valid, and calls the correct map method to reveal or flag a tile.

It also checks the conditions that determines if the game is over:

- The game is lost if a bomb is revealed.
- The game is won if all non-bomb tiles are revealed.

`Minesweeper` also implements `ITilesGame`, which allows observers to attach and detach from the game.

# Design Patterns

- Observer Pattern
  - The Observer pattern is be used so the game state/board can notify objects when a tile changes its state. For example, when a tile is revealed, triggers a bomb, or is flagged, observers such as the game/player will be notified and updated. This has been implemented in `TilesObserver`, the observer interface. Any class that receives game updates implements this interface. 
- Builder Pattern
  - The Builder pattern is implemented in the `Map` class. It allows the map to be constructed in steps by setting the adjacency pattern, grid size, bomb locations and number of random bombs. As a result, the program doesn't need a large constructor with many parameters.  
- Factory Pattern
  - The Factory pattern is implemented in the `TileFactory` class, and is used to create several different types of tiles. It creates number tiles, empty tiles, bomb tiles, and flag tiles. This allows tile creation logic to be organized and placed in one area in the program.
- Singleton Pattern
  - Implemented in `EventBus` to make sure there is only one shared EventBus instance for observer notifications.
- Decorator Pattern
  - Used for tiles in order to change their state at runtime. This allows tiles to change their behavior and is used for FlagTile and NumberTile. These subclasses extend DecoratedTile.
### `Player`
The `Player` class handles user input; it asks the player for an action and to choose a row and column. It converts user-input into a `PlayerAction` enum. This keeps the gameplay loop cleaner since we don't have to pass unnecessary raw strings into the `Minesweeper` class.

### `Map`
The `Map` class represents the game board and stores the 2D array of tiles, number of rows and columns, adjacency pattern, and tile factory.

The map is responsible for:
- Revealing tiles
- Flagging/unflagging tiles
- Returning tiles at specific locations
- Checking if a bomb has been revealed
- Checking if all safe tiles have been revealed
- Checking if a row/column are in bounds
- Displaying current board with toString method.

It also has MapBuilder which is used to build maps.

### `Tile`
This class is the abstract base class for all tile types. `Tile` stores the number of adjacent bombs and whether the tile has been revealed. The default behavior for isBomb(), isFlaggedTile, and toString is also defined here.  

Subclasses of Tile:
- EmptyTile: A safe non-bomb tile
- BombTile: A tile containing a bomb
- FlagTile: A tile that is flagged
- DecoratedTile: Abstract decorator class used to wrap other tiles
- NumberTile: Decorated tile representing revealed number tile

### `AdjacencyPattern`

An abstract class that calculates how many bombs are adjacent to a tile depending on the pattern. Each pattern has different adjacency rules.

Subclasses of AdjacencyPattern:

- `NormalAdjacency` -> Standard Minesweeper rules. Counts bombs in the eight surrounding tiles. 
- `KnightAdjacency` -> Counts bombs using the knight move from Chess. Only tiles that are reachable by a knight move are considered adjacent. 
- `FibonacciAdjacency` -> Counts bombs at Fibonacci-number distances from the current tile.

### `EventBus`
Implemented as a singleton and used to notify observers when game events happen. Stores list of observers and notifies them when game state changes.

### `TilesObserver`
Observer interface
### `VisualMinesweeperObserver`
Prints game updates to terminal. Displays messages, revealed tiles, starting board, and more.

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

