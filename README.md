# Minesweeper-Dockhorn-Off
Carolyn Dockhorn, Kristin Off

Minesweeper project for object-oriented programming. 


# Design Patterns

- Observer Pattern
  - The Observer pattern will be used so the game state/board can notify objects when a tile changes its state. For example, when a tile is revealed, triggers a bomb, or is flagged, observers such as the game/player will be notified and updated.

- Builder Pattern
  - We will use the Builder Pattern to construct complex maps of tiles for the game to be played in. For example, it can create a board step by step by setting the number of rows, columns, adjacency patterns, and bomb locations without generating too many constructors. We're using the builder pattern to prevent having large, confusing constructor.  

- Factory Pattern
  - We will use the Factory pattern to create multiple types of tiles instead of directly instantiating each file type. For example, the factory can decide whether to 

# Foundational Classes

- Minesweeper
- Map
- Tiles 
- Subclasses of Tile:
  - Bomb
  - Flag (Marker placed by the user on a tile to indicate that it is suspected to be a bomb)
  - Number (Number of tiles considered adjacent under the current adjacency pattern)
- Adjacency Patterns (Interface)
  - Modes: Normal, Knight, Fibonacci

Submitting a URL to your repo is REQUIRED for this assignment, and it will be graded on this rubric:

At least 3 design patterns are clearly identified with a written explanation of where and how each will be used in the architecture. -- 3 points
    
Foundational classes and interfaces are designed and implemented, with real logic beginning to take shape — not just empty stubs or boilerplate. -- 3 points
   
Code demonstrates core OO principles: coding to abstractions, at least one example of polymorphism, and dependency injection where applicable. -- 2 points
    
At least 5 meaningful test cases have been designed. -- 2 poin



