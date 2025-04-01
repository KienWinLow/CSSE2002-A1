package game.core;

import game.ui.ObjectGraphic;

/**
 * Represents a health power-up in the game. When collected by a {@link Ship},
 * it restores a certain amount of the ship's health. This class extends the
 * {@link PowerUp} abstract class and implements the specific effect of healing.
 */
public class HealthPowerUp extends PowerUp {

    /**
     * Constructs a new HealthPowerUp object at the specified coordinates.
     *
     * @param x The initial x-coordinate of the health power-up.
     * @param y The initial y-coordinate of the health power-up.
     */
    public HealthPowerUp(int x, int y) {
        super(x, y);
    }

    @Override
    public void applyEffect(Ship ship) {
        ship.heal(20);
        System.out.println("Health restored by 20");
    }

    @Override
    public ObjectGraphic render() {
        return new ObjectGraphic("❤️", "assets/health.png");
    }
}
