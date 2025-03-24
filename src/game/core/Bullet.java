package game.core;

import game.ui.ObjectGraphic;

public class Bullet extends ObjectWithPosition{

    public Bullet(int x, int y) {
        super(x, y);
    }

    @Override
    public ObjectGraphic render() {
        return new ObjectGraphic("🔺","CSSE2002_A1/assets/bullet.png");
    }

    @Override
    public void tick(int tick) {
        y--;
    }
}
