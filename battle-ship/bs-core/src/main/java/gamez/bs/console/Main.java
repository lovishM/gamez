package gamez.bs.console;

import gamez.bs.controllers.GameRoom;
import gamez.bs.enums.ShipTypes;
import gamez.bs.stucts.Game;
import gamez.bs.stucts.Player;

/**
 * Created by lovish on 26/1/17.
 *
 * @author lovish
 */
public class Main {
    public static void main(String[] args) {
        Player p = new Player("Roger", "roger");
        Player l = new Player("Bob", "bob");

        GameRoom room = new GameRoom();
        Game g = room.findGame(p);
        assert g == null;

        g = room.findGame(l);
        assert g != null;

        System.out.println("Set placements for [" + p.id() + "]");
        for(ShipTypes st : ShipTypes.values()) {
        }

        System.out.println("Set placements for [" + l.id() + "]");
        for(ShipTypes st : ShipTypes.values()) {
        }

        System.out.println("Starting up the game");
        while (!g.isGameOver()) {
        }
    }
}
