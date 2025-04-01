package game.core;

import game.GameModel;
import game.exceptions.BoundaryExceededException;
import game.utility.Direction;

/**
 * An abstract class representing game objects that can be controlled and moved within the game boundaries.
 * It extends {@link ObjectWithPosition} and provides a method for moving the object in a specified direction,
 * while also checking for boundary collisions and throwing a {@link BoundaryExceededException} if a move would
 * take the object outside the allowed game area.
 */
public abstract class Controllable extends ObjectWithPosition {

    /**
     * Constructs a new Controllable object at the specified coordinates.
     *
     * @param x The initial x-coordinate of the controllable object.
     * @param y The initial y-coordinate of the controllable object.
     */
    public Controllable(int x, int y) {
        super(x, y);
    }

    /**
     * Moves the controllable object in the specified direction.
     * This method updates the object's x and y coordinates based on the given {@link Direction}.
     * It also checks if the move would result in the object going outside the game boundaries defined
     * by {@link GameModel#GAME_WIDTH} and {@link GameModel#GAME_HEIGHT}. If a move exceed the
     * boundaries, a {@link BoundaryExceededException} is thrown.
     *
     * @param direction The direction in which to move the object.
     * @throws BoundaryExceededException If the move takes the object outside the game boundaries.
     */
    public void move(Direction direction) throws BoundaryExceededException {
        int newX = x;
        int newY = y;

        switch (direction) {
            case UP:
                newY--;
                if (newY < 0) {
                    throw new BoundaryExceededException("Cannot move up. Out of bounds!");
                }
                break;
            case DOWN:
                newY++;
                if (newY >= GameModel.GAME_HEIGHT) {
                    throw new BoundaryExceededException("Cannot move down. Out of bounds!");
                }
                break;
            case LEFT:
                newX--;
                if (newX < 0) {
                    throw new BoundaryExceededException("Cannot move left. Out of bounds!");
                }
                break;
            case RIGHT:
                newX++;
                if (newX >= GameModel.GAME_WIDTH) {
                    throw new BoundaryExceededException("Cannot move right. Out of bounds!");
                }
                break;
        }

        x = newX;
        y = newY;
    }
}
