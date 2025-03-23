package game.core;

import game.GameModel;
import game.exceptions.BoundaryExceededException;
import game.utility.Direction;

public abstract class Controllable extends ObjectWithPosition{
    public Controllable(int x, int y) {
        super(x, y);
    }

    public void move(Direction direction) throws BoundaryExceededException{
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
