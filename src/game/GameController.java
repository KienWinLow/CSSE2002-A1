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
        //getModel().addObject(new Bullet(2, 14));
        // END STAGE 0 only

        // FOR STAGE 1 only, uncomment or remove after
        //getModel().addObject(new Enemy(6, 0));
        //getModel().addObject(new Enemy(2, 0));
        //getModel().addObject(new Enemy(9, 0));
        //getModel().addObject(new Asteroid(8,0));
        //getModel().addObject(new HealthPowerUp(7,8));
        //getModel().addObject(new ShieldPowerUp(7,10));
        //getModel().addObject(new Enemy(4, 0));
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
        getModel().updateGame(tick); // Update GameObjects
        getModel().checkCollisions(); // Check for Collisions
        getModel().spawnObjects(); // Handles new spawns
        getModel().levelUp(); // Level up when score threshold is met
    }

    /**
     * Renders the current state of the game on the UI.
     *
     * This method retrieves the game model, adds the ship to the list of objects to be rendered,
     * updates the UI statistics for score, health, level, and time survived, and then
     * calls {@link UI#render(java.util.List)} to draw all the space objects.
     */
    public void renderGame() {
        GameModel gameModel = getModel();
        gameModel.addObject(gameModel.getShip());
        ui.setStat("Score: ", String.valueOf(gameModel.getShip().getScore()));
        ui.setStat("Health: ", String.valueOf(gameModel.getShip().getHealth()));

        ui.setStat("Level: ", String.valueOf((gameModel.getLevel())));
        ui.setStat("Time Survived: ", (System.currentTimeMillis() - startTime) / 1000 + " seconds");

        List<SpaceObject> spaceObjects = gameModel.getSpaceObjects();
        ui.render(spaceObjects);
    }

    /**
     * Handles player input received from the UI.
     *
     * This method converts the input to uppercase and then performs actions based on the input:
     * - "W": Moves the ship {@link Direction#UP}.
     * - "A": Moves the ship {@link Direction#LEFT}.
     * - "S": Moves the ship {@link Direction#DOWN}.
     * - "D": Moves the ship {@link Direction#RIGHT}.
     * - "F": Calls {@link GameModel#fireBullet()} to fire a bullet.
     * - "P": Calls {@link #pauseGame()} to pause the game.
     * - Any other input: Logs an "Invalid input" message to the UI.
     *
     * If a {@link BoundaryExceededException} is caught during ship movement, its message is logged to the UI.
     *
     * @param input the player's input string
     */
    public void handlePlayerInput(String input) {
        String upperInput = input.toUpperCase();
        try {
            GameModel gameModel = getModel();
            Ship ship = gameModel.getShip();
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
                    gameModel.fireBullet();
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

    /**
     * Pauses the game.
     *
     * This method calls {@link UI#pause()} to pause the UI and logs a "Game paused." message to the UI.
     */
    public void pauseGame() {
        ui.pause();
        ui.log("Game paused.");
    }

    /**
     * Returns the game model.
     * @return the game model instance
     */
    public GameModel getModel() {
        return model;
    }
}