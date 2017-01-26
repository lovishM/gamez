package gamez.bs.stucts;

import gamez.bs.enums.ShipTypes;
import gamez.bs.enums.TurnTypes;
import gamez.bs.exceptions.StateException;

/**
 * Created by lovish on 26/1/17.
 *
 * @author lovish
 */
public class Player {

    private String name;
    private String id;

    private int myFullHits;

    private GameBoard board;

    public Player(String name, String id) {
        this.name = name;
        this.id = id;

        this.board = new GameBoard();
    }

    public String name() { return name; }
    public String id() { return id; }
    public int myFullHits() { return myFullHits; }

    // Place ships on the board
    public void placeShip(ShipTypes type, GameBoard.Placement placement) throws StateException { this.board.place(type, placement); }

    // Get the board ready to play
    public void getReady() throws StateException { this.board.initialize(); }

    // Show the board on the user screen
    public int[][] seeBoard() { return this.board.getCurrentGridPlacement(); }

    // Play the user's turn
    public TurnTypes hisTurn(GameBoard.Coordinates c) throws StateException { return this.board.play(c); }

    public int consumeMyTurn(TurnTypes type) {
        if (type == TurnTypes.DESTROYED) {
            myFullHits++;
        }

        return myFullHits;
    }

    @Override
    public boolean equals(Object o) {
        return o == this || o instanceof Player && ((Player) o).id().equals(this.id());
    }
}
