package game.core;

import game.ui.ObjectGraphic;

public class HealthPowerUp extends PowerUp{
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
        return new ObjectGraphic("❤️","CSSE2002_A1/assets/health.png");
    }
}
