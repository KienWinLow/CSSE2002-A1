package game.exceptions;

/**
 * An exception that is thrown when an object's position exceeds the game boundaries.
 */
public class BoundaryExceededException extends RuntimeException {
    /**
     * Constructs a new BoundaryExceededException with the specified detail message.
     *
     * @param message the detail message.
     */
    public BoundaryExceededException(String message) {
        super(message);
    }
}
