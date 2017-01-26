package gamez.bs.stucts;

import gamez.bs.enums.PositionTypes;
import gamez.bs.enums.ShipTypes;
import gamez.bs.enums.TurnTypes;
import gamez.bs.exceptions.StateException;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * Created by lovish on 26/1/17.
 *
 * @author lovish
 */
public class GameBoard {

    // Define the range for x, y coordinates
    public static final int MAX_X_SIZE = 10;
    public static final int MAX_Y_SIZE = 10;

    // Game grid on which the game would be played
    private int[][] gameGrid = new int[MAX_X_SIZE][MAX_Y_SIZE];

    // Ships placement, with their corresponding types
    private Map<Ship, Placement> ships = new HashMap<>();

    // Flag to freeze the placement, on game initialization
    private boolean initialized;

    // Default constructor to disable non-package access
    GameBoard() {}

    /**
     * Game placement for the type of ship
     *
     * @param shipType      ship type
     * @param placement     placement from and to coordinates
     */
    void place (ShipTypes shipType, Placement placement) throws StateException {

        if (initialized)
            throw new StateException("Initialized game cannot be modified");

        // A grid to test the placement in
        int[][] placementGrid = new int[MAX_X_SIZE][MAX_X_SIZE];

        // Fill the grid with already placed ships
        placementGrid = fillGrid(placementGrid);

        // Fill the grid with provided new placement, and initialized ship
        placementGrid = fillPlacementInGrid(placementGrid, shipType, placement);

        // Populate the map with ship and it's placement on non error
        Ship ship = new Ship(shipType);
        ships.put(ship, placement);
    }

    /**
     * Fills the grid with the map of ship placement already defined
     *
     * @param   grid        grid which needs to be filled in
     * @return  int[][]     the provided grid with filled in values
     */
    private int[][] fillGrid (int[][] grid) throws StateException {

        // Initialize the grid with empty
        for (int i = 0; i < MAX_X_SIZE; i++ )
            for (int j = 0; j < MAX_Y_SIZE; ++j)
                grid[i][j] = PositionTypes.EMPTY.type();

        // Place the ships in the grid, using the placement map
        for (Map.Entry<Ship, Placement> entry : ships.entrySet()) {
            grid = fillPlacementInGrid(grid, entry.getKey().type(), entry.getValue());
        }

        // Return the filled in grid with occupied values
        return grid;
    }

    /**
     * Fills in the grid provided, with the placement passed in
     *
     * @param   grid        grid to be filled in with the placement
     * @param   placement   the placement to be used to fill the grid in
     * @return  int[][]     the provided grid with filled in values
     */
    private int[][] fillPlacementInGrid (int[][] grid, ShipTypes ship, Placement placement) throws StateException {

        // Place the ship horizontally
        if (placement.alignment() == Placement.Alignment.HORIZONTAL) {
            int constAxis = placement.from().y();
            for (int x = placement.from().x(); x < placement.to().x(); x++) {
                if (grid[x][constAxis] != PositionTypes.EMPTY.type()) {
                    throw new StateException("Position already occupied by another ship");
                }
                grid[x][constAxis] = ship.id();
            }

            return grid;
        }

        // Place the ship vertically
        if (placement.alignment() == Placement.Alignment.VERTICAL) {
            int constAxis = placement.from().x();
            for (int y = placement.from().y(); y < placement.to().y(); y++) {
                if (grid[constAxis][y] == PositionTypes.EMPTY.type()) {
                    throw new StateException("Position already occupied by another ship");
                }
                grid[constAxis][y] = ship.id();
            }
        }

        return grid;
    }

    /**
     * Initializes the game with all the provided placements
     *
     * @return  int[][]         Copy of the game grid, on which game is being played
     * @throws  StateException  Exception encountered while initialization
     */
    int[][] initialize() throws StateException {

        // If initialized return a copy of the grid
        if (initialized) gameGrid.clone();

        // Verify if all the ships are present in the placement map
        for (ShipTypes type: ShipTypes.values()) {
            Optional<Ship> obj = ships.keySet().stream().filter(s -> s.type() == type).findFirst();
            if(!obj.isPresent())
                throw new StateException("Placement is incomplete, ship [" + type.name() + "] not placed");
        }

        // Fill the grid positions
        this.gameGrid = fillGrid(gameGrid);

        // Initialize game if everything goes ok
        initialized = true;

        return this.gameGrid.clone();
    }

    /**
     * Play the turn with the specified coordinates
     *
     * @param   c               Coordinates to be played by the user
     * @return  FeedBack        Feedback of the turn played
     */
    FeedBack play(Coordinates c) throws StateException {

        if (!initialized) initialize();

        int state = gameGrid[c.x()][c.y()];
        if (state == PositionTypes.PLAYED.type() || state == PositionTypes.SUNK.type())
            throw new StateException("Coordinates " + c + " have already been played");

        // Find the entry which has provided coordinates
        Optional<Map.Entry<Ship, Placement>> obj = ships.entrySet().stream().filter(
                e-> e.getValue().isInScope(c)
        ).findFirst();

        // Mark the state in the game grid
        if (!obj.isPresent()) {
            gameGrid[c.x()][c.y()] = PositionTypes.PLAYED.type();

            return new FeedBack(TurnTypes.MISS, "Ooh, near miss!");
        } else {
            gameGrid[c.x()][c.y()] = PositionTypes.SUNK.type();

            Ship ship = obj.get().getKey();
            return (ship.hit() == 0)
                    ? new FeedBack(TurnTypes.DESTROYED, "Congratulations, you just destroyed the " + ship.type().name())
                    : new FeedBack(TurnTypes.HIT, "Well done, you just got a cool HIT");
        }
    }

    /**
     * Returns the current game grid for display purposes
     *
     * @return  int[][]         Game grid which is currently in play
     */
    public int[][] getCurrentGridPlacement() { return this.gameGrid.clone(); }

    /**
     * Coordinates class to define (x,y) axis.
     * The x coordinates lies between (0, MAX_X_SIZE)
     * The y coordinates lies between (0, MAX_Y_SIZE)
     */
    public static class Coordinates {

        private int x;
        private int y;

        public Coordinates(int x, int y) {
            this.x = x;
            this.y = y;

            if (x < 0 || x >= MAX_X_SIZE)
                throw new ExceptionInInitializerError("X coordinates should remain between (0, " + MAX_X_SIZE + ")");
            if (y < 0 || y >= MAX_Y_SIZE)
                throw new ExceptionInInitializerError("Y coordinates should remain between (0, " + MAX_Y_SIZE + ")");
        }

        private int x() { return x; }
        private int y() { return y; }

        @Override
        public String toString() { return "(" + x + ", " + y + ")"; }
    }

    /**
     * Placement class to define a ship's placement
     * uses from and to coordinates, and defines HORIZONTAL/VERTICAL positioning
     */
    public static class Placement {
        private Coordinates from;
        private Coordinates to;

        private Alignment alignment;

        public Placement(Coordinates from, Coordinates to) {
            this.from = from;
            this.to = to;

            if (this.from.x() == this.to.x()) {
                alignment = Alignment.HORIZONTAL;
            } else if (this.from.y() == this.to.y()) {
                alignment = Alignment.VERTICAL;
            } else {
                // Very rare error, should not come in well driven program
                throw new ExceptionInInitializerError("Placement provided in not optimal");
            }
        }

        /**
         * Checks if the coordinates are in scope of the turret guns
         *
         * @param   c           Coordinates to be located
         * @return  boolean     Returns true if coordinates are in range
         */
        private boolean isInScope(Coordinates c) {
            if (alignment == Alignment.HORIZONTAL) {
                return ((c.y() == from.y()) && (c.x() >= from.x()) && (c.x() <= to.x()));
            } else {
                return ((c.x() == from.x()) && (c.y() >= from.y()) && (c.y() <= to.y()));
            }
        }

        // Getters for the private fields
        private Coordinates from() { return this.from; }
        private Coordinates to() { return this.to; }
        private Alignment alignment() { return this.alignment; }

        public enum Alignment {
            HORIZONTAL, VERTICAL
        }
    }
}

