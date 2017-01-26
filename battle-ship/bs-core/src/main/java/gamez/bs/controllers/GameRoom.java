package gamez.bs.controllers;

import gamez.bs.exceptions.StateException;
import gamez.bs.stucts.Game;
import gamez.bs.stucts.Player;

import java.util.Optional;
import java.util.concurrent.ConcurrentSkipListSet;

/**
 * Created by lovish on 26/1/17.
 *
 * @author lovish
 */
public class GameRoom {

    private ConcurrentSkipListSet<Player> lounge = new ConcurrentSkipListSet<>();
    private ConcurrentSkipListSet<Game> runningGames = new ConcurrentSkipListSet<>();

    /**
     * Finds a player in the lounge to begin the game
     * if not present adds the player in the lounge
     *
     * @param   player          Player who's trying to find a game
     * @return  Game            Returns game if found, or returns empty
     */
    public Game findGame(Player player) {

        // Make sure the player is not playing himself
        Optional<Player> optionalPlayer = lounge.stream().filter(p -> p.id().equals(player.id())).findFirst();
        optionalPlayer.ifPresent(p -> lounge.remove(p));

        // Return game, if the player is already part of a game
        Optional<Game> optionalGame = runningGames.stream().filter(g -> g.isPresent(player)).findFirst();
        if (optionalGame.isPresent()) {
            return optionalGame.get();
        }

        // If lounge is not empty start a game
        if (!lounge.isEmpty()) {
            Game game = new Game(player, lounge.pollFirst());
            runningGames.add(game);

            return game;
        } else {
            if (!optionalPlayer.isPresent()) lounge.add(player);
            return null;
        }
    }

    /**
     * Remove the game from lounge if the game is over
     *
     * @param   game            Game to be finished
     */
    public void finishGame(Game game) { runningGames.remove(game); }

    /**
     * Remove the player from the game room
     *
     * @param   player          Player to be removed
     * @return  Game            Game the player was part of
     * @throws  StateException  Exception encountered while operating the method
     */
    public Game removePlayer(Player player) throws StateException {
        Optional<Game> game = runningGames.stream().filter(g -> g.isPresent(player)).findFirst();
        if (game.isPresent()) {
            Game g = game.get();
            runningGames.remove(g);
            g.quitGame(player);
            return game.get();
        }

        lounge.removeIf(p -> p.equals(player));
        return null;
    }
}
