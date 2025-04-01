package game.core;

import game.ui.ObjectGraphic;

/**
 * Represents the player's ship in the game. The ship is a {@link Controllable} object
 * that can move within the game boundaries. It has attributes for health and score,
 * and methods for taking damage, healing, and adding to the score.
 */
public class Ship extends Controllable {
    private int health;
    private int score;

    /**
     * Constructs a new Ship object with default starting position (5, 10)
     * and initial health of 100.
     */
    public Ship() {
        super(5, 10);
        this.health = 100;
    }

    /**
     * Constructs a new Ship object with the specified initial coordinates
     * and health.
     *
     * @param x      The starting x-coordinate of the ship.
     * @param y      The starting y-coordinate of the ship.
     * @param health The initial health of the ship.
     */
    public Ship(int x, int y, int health) {
        super(x, y);
        this.health = health;
    }

    /**
     * Reduces the ship's health by the specified damage amount.
     * The health will not go below zero.
     *
     * @param damage The amount of damage to inflict on the ship.
     */
    public void takeDamage(int damage) {
        health = Math.max(0, health - damage);
    }

    /**
     * Increases the ship's health by the specified amount.
     * The health will not exceed 100.
     *
     * @param amount The amount of health to restore to the ship.
     */
    public void heal(int amount) {
        health = Math.min(100, health + amount);
    }

    /**
     * Adds the specified points to the ship's score.
     *
     * @param points The number of points to add to the score.
     */
    public void addScore(int points) {
        score += points;
    }

    /**
     * Gets the current health of the ship.
     *
     * @return The current health of the ship.
     */
    public int getHealth() {
        return health;
    }

    /**
     * Gets the current score of the ship.
     *
     * @return The current score of the ship.
     */
    public int getScore() {
        return score;
    }

    @Override
    public ObjectGraphic render() {
        return new ObjectGraphic("🚀", "assets/ship.png");
    }

    @Override
    public void tick(int tick) {
        // no tick-dependent behaviours
    }

}
