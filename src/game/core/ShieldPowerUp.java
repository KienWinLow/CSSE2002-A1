package game.core;

import game.ui.ObjectGraphic;

public class ShieldPowerUp extends PowerUp{

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
        return new ObjectGraphic("💠","CSSE2002_A1/assets/shield.png");
    }
}
