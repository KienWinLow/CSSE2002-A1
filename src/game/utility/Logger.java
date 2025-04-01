package game.utility;

/**
 * An interface defining a contract for logging messages.
 * Implementations of this interface will provide a way to record
 * information, warnings, or updates that occur within the application.
 */
public interface Logger {

    /**
     * Logs the given text message.
     *
     * @param text The string message to be logged.
     */
    void log(String text);
}
