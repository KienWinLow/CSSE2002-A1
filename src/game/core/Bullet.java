package game.core;

import game.ui.ObjectGraphic;

/**
 * Represents a bullet in the game. Bullets are objects that move upwards.
 */
public class Bullet extends ObjectWithPosition {

    /**
     * Constructs a new Bullet object at the specified coordinates.
     *
     * @param x The initial x-coordinate of the bullet.
     * @param y The initial y-coordinate of the bullet.
     */
    public Bullet(int x, int y) {
        super(x, y);
    }

    @Override
    public ObjectGraphic render() {
        return new ObjectGraphic("🔺", "assets/bullet.png");
    }

    @Override
    public void tick(int tick) {
        y--;
    }
}
