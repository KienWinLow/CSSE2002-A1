package game.core;

import game.ui.ObjectGraphic;

/**
 * Represents a shield power-up in the game. When collected by a {@link Ship},
 * it increases the player's score.
 * This class extends the {@link PowerUp} abstract class and implements the
 * specific effect of increasing score..
 */
public class ShieldPowerUp extends PowerUp {

    /**
     * Constructs a new ShieldPowerUp object at the specified coordinates.
     *
     * @param x The initial x-coordinate of the shield power-up.
     * @param y The initial y-coordinate of the shield power-up.
     */
    public ShieldPowerUp(int x, int y) {
        super(x, y);
    }

    @Override
    public void applyEffect(Ship ship) {
        ship.addScore(50);
        System.out.println("Shield activated! Score increased by 50.");
    }

    @Override
    public ObjectGraphic render() {
        return new ObjectGraphic("💠", "assets/shield.png");
    }
}
