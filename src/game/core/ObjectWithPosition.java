package game.core;

/**
 * An abstract class that serves as a base for all game objects that have a position
 * in the game. It implements the {@link SpaceObject} interface and provides
 * basic functionality for storing and accessing the object's x and y coordinates.
 * Subclasses of ObjectWithPosition will represent entities within the game
 * that exist at a specific location.
 */
public abstract class ObjectWithPosition implements SpaceObject {
    protected int x;
    protected int y;

    /**
     * Constructs a new ObjectWithPosition with the specified initial coordinates.
     *
     * @param x The starting x-coordinate of the object.
     * @param y The starting y-coordinate of the object.
     */
    public ObjectWithPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Gets the current x-coordinate of the object.
     *
     * @return The x-coordinate of the object.
     */
    public int getX() {
        return x;
    }

    /**
     * Gets the current y-coordinate of the object.
     *
     * @return The y-coordinate of the object.
     */
    public int getY() {
        return y;
    }

}
