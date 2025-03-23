package game.core;

import game.ui.ObjectGraphic;

public class Ship extends Controllable{
    int health;
    int score;

    public Ship() {
        super(5,10);
        this.health = 100;
    }

    public Ship(int x, int y, int health) {
        super(x, y);
        this.health = health;
    }

    public int takeDamage(int damage) {
        return health = Math.max(0,health-damage);
    }

    public int heal(int amount) {
        return health = Math.min(100,health + amount);
    }

    public void addScore (int points) {
        score += points;
    }

    public int getHealth() {
        return health;
    }

    public int getScore() {
        return score;
    }

    @Override
    public ObjectGraphic render() {
        return new ObjectGraphic("🚀","CSSE2002_A1/assets/ship.png");
    }

    @Override
    public void tick(int tick) {
        // no tick-dependent behaviours
    }

}
