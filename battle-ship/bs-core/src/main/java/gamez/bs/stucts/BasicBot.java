package gamez.bs.stucts;

import gamez.bs.exceptions.StateException;

/**
 * Created by lovish on 26/1/17.
 *
 * @author lovish
 */
public class BasicBot extends Player {

    public BasicBot() {
        super("BOT_" + System.currentTimeMillis(), System.currentTimeMillis() + "");
    }

    @Override
    protected void getReady() throws StateException {

        // TODO Assign the ships using placeShip

        super.getReady();
    }

    @Override
    public GameBoard.Coordinates getNextTurn() {

        // TODO Create logic for getting the next turn

        return null;
    }
}
