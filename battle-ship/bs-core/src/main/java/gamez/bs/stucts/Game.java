package gamez.bs.stucts;

import gamez.bs.enums.GameState;
import gamez.bs.enums.ShipTypes;
import gamez.bs.exceptions.StateException;

/**
 * Created by lovish on 26/1/17.
 *
 * @author lovish
 */
public class Game {

    private Player firstPlayer;
    private Player secondPlayer;

    private GameState state = GameState.RUNNING;

    public Game(Player a, Player b) {
        this.firstPlayer = a;
        this.secondPlayer = b;
    }

    public Player getFirstPlayer() { return firstPlayer; }
    public Player getSecondPlayer() { return secondPlayer; }

    public GameState playFPTurn(GameBoard.Coordinates c) throws StateException {
        return playTurn(firstPlayer, secondPlayer, c);
    }

    public GameState playSPTurn(GameBoard.Coordinates c) throws StateException {
        return playTurn(secondPlayer, firstPlayer, c);
    }

    private GameState playTurn(Player from, Player to, GameBoard.Coordinates c) throws StateException {

        int destroyed = from.consumeMyTurn(to.hisTurn(c));
        if (destroyed == ShipTypes.values().length) {
            state = GameState.WON;
        }

        return state;
    }
}
