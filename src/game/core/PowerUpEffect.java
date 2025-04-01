package game.core;

/**
 * An interface that defines the contract for applying an effect from a power-up
 * to a {@link Ship} object. Classes that implement this interface represent
 * specific types of power-ups and will define how they affect the ship
 * when their effect is applied.
 */
public interface PowerUpEffect {

    /**
     * Applies the specific effect of the power-up to the given {@link Ship}.
     * This method will modify the ship's attributes or state according to the
     * type of power-up being implemented.
     *
     * @param ship The {@link Ship} object that is receiving the effect of the power-up.
     */
    void applyEffect(Ship ship);
}
