package gamez.bs.console;

import gamez.bs.controllers.GameRoom;
import gamez.bs.stucts.Game;
import gamez.bs.stucts.Player;

/**
 * Created by lovish on 26/1/17.
 *
 * @author lovish
 */
public class Main {
    public static void main(String[] args) {
        Player p = new Player("buddhuu", "Priyanka");
        Player l = new Player("coder", "Lovish");

        GameRoom room = new GameRoom();
        Game g = room.findGame(p);
        assert g == null;

        g = room.findGame(l);
        assert g != null;

        while (!g.isGameOver()) {

        }
    }
}
