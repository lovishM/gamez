package gamez.bs.stucts;

import gamez.bs.enums.GameState;
import gamez.bs.enums.ShipTypes;
import gamez.bs.exceptions.StateException;

/**
 * Created by lovish on 26/1/17.
 *
 * @author lovish
 */
public class Game implements Comparable<Game> {

    private String gameId;

    private Player firstPlayer;
    private Player secondPlayer;

    private boolean currentTurn;

    private GameState state = GameState.RUNNING;

    public Game(Player a, Player b) {
        this.firstPlayer = a;
        this.secondPlayer = b;

        this.gameId = a.id() + "-" + b.id();
    }

    /**
     * Begins the game if both players are ready
     * returns false to indicate either player is not ready
     *
     * @return  boolean         returns if the game is ready to begin
     */
    public boolean begin() {
        try {
            firstPlayer.getReady();
            secondPlayer.getReady();

            return true;
        } catch (StateException e) {
            return false;
        }
    }

    /**
     * Watch the board of the player
     *
     * @param   player              Player trying to see his board
     * @return  int[][]             Display the board as a 2-D array
     * @throws  StateException      Exception encountered while operating on the board
     */
    public int[][] seeBoard(Player player) throws StateException {
        if (!isPresent(player))
            throw new StateException("Player trying to see board positioning not found");

        return player.seeBoard();
    }

    /**
     * Returns the player based on the turn
     *
     * @return  Player      Player who's turn is due
     */
    public Player getTurn() {
        return currentTurn ? secondPlayer : firstPlayer;
    }

    /**
     * Plays the turn for the player, after validating his turn is due
     *
     * @param   player              Player who wants to play the turn
     * @param   c                   Coordinates of the game board
     * @return  GameState           Current state of the game
     * @throws  StateException      Exception encountered if any
     */
    public GameState playTurn(Player player) throws StateException {

        if (state != GameState.RUNNING) throw new StateException("Game is already over, cannot play anymore turns");
        if (!player.equals(getTurn()))
            throw new StateException("Player " + player.id() + "'s turn is not due");

        Player other = (currentTurn) ? firstPlayer : secondPlayer;

        int destroyed = player.consumeMyTurn(other.hisTurn(player.getNextTurn()));
        if (destroyed == ShipTypes.values().length) {
            state = GameState.WON;
        }

        // Flip the turn
        currentTurn = !currentTurn;

        return state;
    }

    /**
     * Check if the player provided is part of this game
     *
     * @param   player              Player to be checked with the game
     * @return  boolean             Returns true if player is part of the game
     */
    public boolean isPresent(Player player) { return (player.equals(firstPlayer) || player.equals(secondPlayer)); }

    // TODO Improve the logic, I'm not happy yet
    public GameState quitGame(Player p) throws StateException {
        if (!isPresent(p)) throw new StateException("Player trying to quit the game is not found");

        if (p.equals(firstPlayer)) firstPlayer = null;
        if (p.equals(secondPlayer)) secondPlayer = null;

        if (p.myFullHits() == ShipTypes.values().length) return GameState.WON;
        else return GameState.LOST;
    }

    public String getGameId() { return this.gameId; }

    @Override
    public int compareTo(Game game) {
        if (this.equals(game)) return 0;
        return game.getGameId().compareTo(this.getGameId());
    }

    @Override
    public boolean equals(Object o) {
        return o == this || o instanceof Game && ((Game) o).getGameId().equals(this.getGameId());
    }

    public boolean isGameOver() { return (state != GameState.RUNNING); }
}
