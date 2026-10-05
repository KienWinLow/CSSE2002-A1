# Space Shooter Game (Java)

A tick-driven space shooter written in Java. You pilot a ship around a 10 × 20 grid, shoot enemies, dodge asteroids, and collect power-ups while the game gets harder as your level rises.

Built for **CSSE2002 (Programming in the Large)**, University of Queensland, Semester 1 2025.

## Gameplay

| Key | Action |
|-----|--------|
| `W` `A` `S` `D` | Move up / left / down / right |
| `F` | Fire a bullet |
| `P` | Pause |

- The ship starts near the centre of the grid with **100 health**. Moving off the grid is rejected and a message is logged.
- **Asteroids** deal 10 damage and **enemies** deal 20 damage when they hit the ship.
- **Bullets** travel upward each tick and destroy any enemy they meet.
- **Power-ups** drop from the top: a health pack restores 20 health (capped at 100) and a shield pickup adds 50 to the score.
- Objects fall at a steady pace and are removed once they leave the bottom of the grid.
- **Levelling up**: when the score reaches `level × 100`, the level increases and so does the spawn rate (it starts at 2% per tick and rises 5 points per level). Enemies spawn at half the asteroid rate and power-ups at a quarter of it.
- The UI shows score, health, level and time survived, plus a message log.

## Design

The project follows a **Model–View–Controller** split, with an object-oriented class hierarchy for everything on screen.

```
SpaceObject (interface)
└── ObjectWithPosition (abstract: x, y)
    ├── Controllable (abstract: bounded movement)
    │   └── Ship
    ├── DescendingEnemy (abstract: moves down every 10 ticks)
    │   ├── Enemy
    │   └── Asteroid
    ├── Bullet
    └── PowerUp (abstract, implements PowerUpEffect)
        ├── HealthPowerUp
        └── ShieldPowerUp
```

- **`GameModel`** holds all game state (the list of space objects, ship, level and spawn rate) and implements the rules: updating objects, collision handling, spawning, and levelling.
- **`GameController`** runs the game flow each tick (render, update, check collisions, spawn, level up) and turns key presses into actions.
- **`BoundaryExceededException`** is a custom exception thrown by movement and caught by the controller to report the problem to the player.
- **`Logger`** is a small functional interface, so the model can send messages to the UI without depending on it.
- Collisions use Java's **pattern-matching `switch`**, so each kind of object is handled by type.

## Requirements and what's not included

- **Java 21 or later** (the code uses pattern matching in `switch`).
- This repository contains only the code I wrote: the model, controller, core classes, exception and logger. The **course-provided UI, `Main` class, `Direction` enum and image assets** are not included, so the game won't compile or run from this repo alone.

## AI assistance

AI was used during development.
