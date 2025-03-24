package game.core;

import game.ui.ObjectGraphic;

public class Asteroid extends DescendingEnemy{

    public Asteroid (int x, int y){
        super(x, y);
    }

    @Override
    public ObjectGraphic render() {
        return new ObjectGraphic("🌑","CSSE2002_A1/assets/asteroid.png");
    }
}
