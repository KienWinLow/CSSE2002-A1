package game.core;

import game.ui.ObjectGraphic;
import game.ui.Tickable;

/**
 * An interface that defines the basic properties and behaviors of all object
 * that exists within the game's space. All entities within the game world
 * that have a position and a visual representation should implement this interface.
 * It extends the {@link Tickable} interface, meaning all space objects can have
 * their state updated on each game tick.
 */
public interface SpaceObject extends Tickable {

    /**
     * Gets the current x-coordinate of the space object.
     *
     * @return The x-coordinate of the object.
     */
    int getX();

    /**
     * Gets the current y-coordinate of the space object.
     *
     * @return The y-coordinate of the object.
     */
    int getY();

    /**
     * Renders the graphical representation of the space object.
     * This method should return an {@link ObjectGraphic} that describes how
     * the object should be displayed in the game's user interface.
     *
     * @return An {@link ObjectGraphic} object representing the visual appearance of the space object.
     */
    ObjectGraphic render();
}
