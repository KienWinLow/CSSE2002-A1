package game;

import game.core.*;
import game.GameModel;
import game.exceptions.BoundaryExceededException;
import game.ui.UI;
import game.utility.Direction;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * The Controller handling the game flow and interactions.
 *
 * Holds references to the UI and the Model, so it can pass information and references back and forth as necessary.
 * Manages changes to the game, which are stored in the Model, and displayed by the UI.
 */
public class GameController {
    private long startTime;
    private UI ui;
    private GameModel model;

    /**
     * Initializes the game controller with the given UI and Model.
     * Stores the ui, model and start time.
     * The start time System.currentTimeMillis() should be stored as a long.
     *
     * @param ui the UI used to draw the Game
     * @param model the model used to maintain game information
     * @provided
     */
    public GameController(UI ui, GameModel model) {
        this.ui = ui;
        this.model = model;
        this.startTime = System.currentTimeMillis(); // Start the timer
    }

    /**
     * Initializes the game controller with the given UI and a new GameModel (taking ui::log as the logger).
     * This constructor should call the other constructor using the "this()" keyword.
     *
     * @param ui the UI used to draw the Game
     * @provided
     */
    public GameController(UI ui) {
        this(ui, new GameModel(ui::log));
    }


    /**
     * Starts the main game loop.
     *
     * Passes onTick and handlePlayerInput to ui.onStep and ui.onKey respectively.
     * @provided
     */
    public void startGame() {
        // FOR STAGE 0 only, uncomment or remove after
        //model.addObject(new Bullet(2, 14));
        // END STAGE 0 only

        // FOR STAGE 1 only, uncomment or remove after
//        model.addObject(new Enemy(2, 0));
//        model.addObject(new Enemy(4, 0));
//        model.addObject(new Enemy(6, 0));
//        model.addObject(new Enemy(9, 0));
//        model.addObject(new Asteroid(8,0));
//        model.addObject(new HealthPowerUp(7,8));
//        model.addObject(new ShieldPowerUp(7,10));
        // END STAGE 1 only

        ui.onStep(this::onTick);
        // Uncomment in stage 2
        ui.onKey(this::handlePlayerInput); // Pass Callback to UI
    }

    /**
     * Uses the provided tick to call and advance the following:
     *      - A call to renderGame() to draw the current state of the game.
     *      - A call to model.updateGame(tick) to advance the game by the given tick.
     *      - A call to model.checkCollisions() to handle game interactions.
     *      - A call to model.spawnObjects() to handle object creation.
     *      - A call to model.levelUp() to check and handle leveling.
     *
     * @param tick the provided tick
     * @provided
     */
    public void onTick(int tick) {
        renderGame(); // Update Visual
        model.updateGame(tick); // Update GameObjects
        model.checkCollisions(); // Check for Collisions
        model.spawnObjects(); // Handles new spawns
        model.levelUp(); // Level up when score threshold is met
    }

    public void renderGame(){
        model.addObject(model.getShip());
        ui.setStat("Score: ", String.valueOf(model.getShip().getScore()));
        ui.setStat("Health: ", String.valueOf(model.getShip().getHealth()));

        ui.setStat("Level: ", String.valueOf((model.getLevel())));
        ui.setStat("Time Survived: ", (System.currentTimeMillis() - startTime) / 1000 + " seconds");

        List<SpaceObject> spaceObjects = model.getSpaceObjects();
        ui.render(spaceObjects);
    }

    public void handlePlayerInput(String input) {
        String upperInput = input.toUpperCase();
        try {
            Ship ship = model.getShip();
            switch (upperInput) {
                case "W":
                    ship.move(Direction.UP);
                    ui.log("Core.Ship moved to (" + ship.getX() + ", " + ship.getY() + ")");
                    break;
                case "A":
                    ship.move(Direction.LEFT);
                    ui.log("Core.Ship moved to (" + ship.getX() + ", " + ship.getY() + ")");
                    break;
                case "S":
                    ship.move(Direction.DOWN);
                    ui.log("Core.Ship moved to (" + ship.getX() + ", " + ship.getY() + ")");
                    break;
                case "D":
                    ship.move(Direction.RIGHT);
                    ui.log("Core.Ship moved to (" + ship.getX() + ", " + ship.getY() + ")");
                    break;
                case "F":
                    model.fireBullet();
                    break;
                case "P":
                    pauseGame();
                    break;
                default:
                    ui.log("Invalid input. Use W, A, S, D, F, or P.");
                    break;
            }
        } catch (BoundaryExceededException e) {
            ui.log(e.getMessage());
        }
    }

    public void pauseGame() {
        ui.pause();
        ui.log("Game paused.");
    }

}

