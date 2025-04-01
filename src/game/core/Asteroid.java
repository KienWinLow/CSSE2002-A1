package game.core;

import game.ui.ObjectGraphic;

/**
 * Represents an asteroid in the game. Asteroids are descending enemies.
 */
public class Asteroid extends DescendingEnemy {

    /**
     * Constructs a new Asteroid object at the specified coordinates.
     *
     * @param x The initial x-coordinate of the asteroid.
     * @param y The initial y-coordinate of the asteroid.
     */
    public Asteroid(int x, int y) {
        super(x, y);
    }


    @Override
    public ObjectGraphic render() {
        return new ObjectGraphic("🌑", "assets/asteroid.png");
    }
}
