package gamez.bs.stucts;

import gamez.bs.enums.ShipTypes;
import gamez.bs.enums.TurnTypes;
import gamez.bs.exceptions.StateException;

/**
 * Created by lovish on 26/1/17.
 *
 * @author lovish
 */
public class Player implements Comparable<Player> {

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

    /**
     * A getter to find the success of this player
     *
     * @return  int             Returns the number of DESTROYED hits achieved
     */
    int myFullHits() { return myFullHits; }

    // Place ships on the board
    void placeShip(ShipTypes type, GameBoard.Placement placement) throws StateException { this.board.place(type, placement); }

    // Get the board ready to play
    void getReady() throws StateException { this.board.initialize(); }

    // Show the board on the user screen
    int[][] seeBoard() { return this.board.getCurrentGridPlacement(); }

    // Play the user's turn
    TurnTypes hisTurn(GameBoard.Coordinates c) throws StateException { return this.board.play(c); }

    /**
     * Evaluate this player's turn and measure his success
     *
     * @param   type            Turn type received on another player's board
     * @return  int             Returns the number of DESTROYED hits achieved
     */
    int consumeMyTurn(TurnTypes type) {
        if (type == TurnTypes.DESTROYED) {
            myFullHits++;
        }

        return myFullHits;
    }

    @Override
    public boolean equals(Object o) {
        return o == this || o instanceof Player && ((Player) o).id().equals(this.id());
    }

    @Override
    public int compareTo(Player player) {
        if (player.equals(this)) return 0;
        return player.id().compareTo(this.id());
    }
}
