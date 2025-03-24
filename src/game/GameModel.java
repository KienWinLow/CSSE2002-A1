package game;


import game.core.*;
import game.utility.Logger;
import game.core.SpaceObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Represents the game information and state. Stores and manipulates the game state.
 */
public class GameModel {
    public static final int GAME_HEIGHT = 20;
    public static final int GAME_WIDTH = 10;
    public static final int START_SPAWN_RATE = 2; // spawn rate (percentage chance per tick)
    public static final int SPAWN_RATE_INCREASE = 5; // Increase spawn rate by 5% per level
    public static final int START_LEVEL = 1; // Starting level value
    public static final int SCORE_THRESHOLD = 100; // Score threshold for leveling
    public static final int ASTEROID_DAMAGE = 10; // The amount of damage an asteroid deals
    public static final int ENEMY_DAMAGE = 20; // The amount of damage an enemy deals
    public static final double ENEMY_SPAWN_RATE = 0.5; // Percentage of asteroid spawn chance
    public static final double POWER_UP_SPAWN_RATE = 0.25; // Percentage of asteroid spawn chance

    private final Random random = new Random(); // ONLY USED IN this.spawnObjects()
    private final List<SpaceObject> spaceObjects;
    private final Logger logger;
    private int level;
    private final Ship ship;
    private int spawnrate;

    /**
     * Models a game, storing and modifying data relevant to the game.
     * Logger argument should be a method reference to a .log method such as the UI.log method.
     * Example: Model gameModel = new GameModel(ui::log)
     * - Instantiates an empty list for storing all SpaceObjects the model needs to track.
     * - Instantiates the game level with the starting level value.
     * - Instantiates the game spawn rate with the starting spawn rate.
     * - Instantiates a new ship.
     * - Stores reference to the given logger.
     *
     * @param logger a functional interface for passing information between classes.
     */
    public GameModel(Logger logger) {
        this.spaceObjects = new ArrayList<>();
        this.level = START_LEVEL;
        this.spawnrate = START_SPAWN_RATE;
        this.ship = new Ship();
        this.logger = logger;
    }

    public GameModel getModel() {
        return this;
    }

    //stage 0
    /**
     * Returns the list of SpaceObjects currently in the game.
     *
     * @return A list of SpaceObject instances.
     */
    public List<SpaceObject> getSpaceObjects() {
        return spaceObjects;
    }

    /**
     * Adds a SpaceObject to the game's list of objects.
     *
     * @param object The SpaceObject to add.
     */
    public void addObject(SpaceObject object) {
        spaceObjects.add(object);
    }

    //stage 1
    public void updateGame(int tick) {
        // Move all objects
        for (SpaceObject object : spaceObjects) {
            object.tick(tick);
        }

        // Remove off-screen objects
        for (int i = spaceObjects.size() - 1; i >= 0; i-- ) {
            SpaceObject object = spaceObjects.get(i);
            if(object.getY() > GAME_HEIGHT){
                spaceObjects.remove(i);
            }
        }
    }

    public void checkCollisions() {
        if (ship != null) {
            for (int i = spaceObjects.size() - 1; i >= 0; i--) {
                SpaceObject object = spaceObjects.get(i);
                if (object.getX() == ship.getX() && object.getY() == ship.getY()) {
                    if (object instanceof PowerUp) {
                        ((PowerUp) object).applyEffect(ship);
                        logger.log("Power-up collected: " + object.render().toString());
                        spaceObjects.remove(i);
                    } else if (object instanceof Asteroid) {
                        ship.takeDamage(ASTEROID_DAMAGE);
                        logger.log("Hit by asteroid! Health reduced by " + ASTEROID_DAMAGE + ".");
                        spaceObjects.remove(i);
                    } else if (object instanceof Enemy) {
                        ship.takeDamage(ENEMY_DAMAGE);
                        logger.log("Hit by enemy! Health reduced by " + ENEMY_DAMAGE + ".");
                        spaceObjects.remove(i);
                    }
                }
            }
        }

        List<SpaceObject> objectsToRemove = new ArrayList<>();

        // Check bullet collisions
        for (SpaceObject obj1 : spaceObjects) {
            if (obj1 instanceof Bullet) {
                for (SpaceObject obj2 : spaceObjects) {
                    if (obj2 instanceof Enemy &&
                            obj1.getX() == obj2.getX() &&
                            obj1.getY() == obj2.getY()) {
                        objectsToRemove.add(obj1);
                        objectsToRemove.add(obj2);
                    }
                }
            }
        }
        spaceObjects.removeAll(objectsToRemove);
    }

    //stage 2

    public Ship getShip(){
        return ship;
    }

    public void fireBullet(){
        Bullet bullet = new Bullet(ship.getX(), ship.getY());
        spaceObjects.add(bullet);
        logger.log("Core.Bullet fired!");
    }

    //stage 3

    public int getLevel() {
        return level;
    }

    public void levelUp() {
        int scoreThreshold = level * SCORE_THRESHOLD;
        if (ship.getScore() >= scoreThreshold) {
            level++;
            spawnrate = START_SPAWN_RATE + (level - 1) * SPAWN_RATE_INCREASE;
            logger.log("Level Up! Welcome to Level " + level + ". Spawn rate increased to " + spawnrate + "%.");
        }
    }

    public void spawnObjects() {
        // check if asteroid should spawn
        if (random.nextInt(100) < spawnrate) {
            // set asteroid at random x
            int asteroidX = random.nextInt(GAME_WIDTH);
            if (ship.getX() != asteroidX) {
                Asteroid asteroid = new Asteroid(asteroidX, 0);
                spaceObjects.add(asteroid);
            }
        }
        // check if enemy should spawn
        if (random.nextInt(100) < spawnrate * ENEMY_SPAWN_RATE) {
            //set enemy at random x
            int enemyX = random.nextInt(GAME_WIDTH);
            if (ship.getX() != enemyX) {
                Enemy enemy = new Enemy(enemyX, 0);
                spaceObjects.add(enemy);
            }
        }
        // check if powerUp should spawn
        if (random.nextInt(100) < spawnrate * POWER_UP_SPAWN_RATE) {
            //set powerUp at random x
            int powerX = random.nextInt(GAME_WIDTH);
            if (ship.getX() != powerX) {
                // Use Boolean to determine which power up to spawn
                if (random.nextBoolean()) {
                    ShieldPowerUp shieldPowerUp = new ShieldPowerUp(powerX, 0);
                    spaceObjects.add(shieldPowerUp);
                } else {
                    HealthPowerUp healthPowerUp = new HealthPowerUp(powerX, 0);
                    spaceObjects.add(healthPowerUp);
                }
            }
        }
    }

    /**
     * Sets the seed of the Random instance created in the constructor using .setSeed().
     *
     * This method should NEVER be called.
     *
     * @param seed to be set for the Random instance
     * @provided
     */
    public void setRandomSeed(int seed) {
        this.random.setSeed(seed);
    }
}
