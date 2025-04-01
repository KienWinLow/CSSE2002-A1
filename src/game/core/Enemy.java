package game.core;

import game.ui.ObjectGraphic;

/**
 * Represents a enemy in the game. Enemies are a type of {@link DescendingEnemy}
 * and will move downwards on the screen. This class defines the basic rendering
 * for a standard enemy.
 */
public class Enemy extends DescendingEnemy {

    /**
     * Constructs a new Enemy object at the specified coordinates.
     *
     * @param x The initial x-coordinate of the enemy.
     * @param y The initial y-coordinate of the enemy.
     */
    public Enemy(int x, int y) {
        super(x, y);
    }

    @Override
    public ObjectGraphic render() {
        return new ObjectGraphic("👾", "assets/enemy.png");
    }
}
