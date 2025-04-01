package game.core;

/**
 * An abstract class representing power-up items in the game.
 * Power-ups are game objects with a position that provide
 * effects to the player's ship when collected. This class extends
 * {@link ObjectWithPosition} and implements the {@link PowerUpEffect}
 * interface, providing a base for different types of power-ups.
 * By default, power-ups do not have any behavior that changes based on game ticks.
 */
public abstract class PowerUp extends ObjectWithPosition implements PowerUpEffect {

    /**
     * Constructs a new PowerUp object at the specified coordinates.
     *
     * @param x The initial x-coordinate of the power-up.
     * @param y The initial y-coordinate of the power-up.
     */
    public PowerUp(int x, int y) {
        super(x, y);
    }

    @Override
    public void tick(int tick) {
        // PowerUps have no tick-dependent behaviour
    }
}
