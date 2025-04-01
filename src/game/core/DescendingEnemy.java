package game.core;

/**
 * An abstract class representing enemies that descend vertically down the game screen.
 * It extends {@link ObjectWithPosition} and implements a basic descending movement
 * in its {@link #tick(int)} method. Subclasses of DescendingEnemy will inherit
 * this behavior and can further customize their appearance and other actions.
 */
public abstract class DescendingEnemy extends ObjectWithPosition {

    /**
     * Constructs a new DescendingEnemy object at the specified coordinates.
     *
     * @param x The initial x-coordinate of the descending enemy.
     * @param y The initial y-coordinate of the descending enemy.
     */
    public DescendingEnemy(int x, int y) {
        super(x, y);
    }

    @Override
    public void tick(int tick) {
        if (tick % 10 == 0) {
            y++;
        }
    }
}
