/**
 * Manages the game's state, including players, round number, and game control functions.
 */
public class GameClass {

    private static Player dealer;
    private static Player player;
    private int roundNumber;

    /**
     * Constructor to create a new Game.
     *
     * @param name the name of the player
     */
    private GameClass() {
        dealer = new Player("dealer");
        roundNumber = 0;
        /*
         * Needs to subsequently visually offer a box using setPlayer() that disappears 
         * after 1 instance of this.player is configured.
         */
    }

    /**
     * Returns the current round number.
     * 
     * @return the current round number
     */
    public int getRoundNumber() {
        return this.roundNumber;
    }

    /**
     * Increments the round number by one, representing a new round in the game.
     */
    public void incrementRoundNumber() {
        this.roundNumber += 1;
    }

    /**
     * Sets the player with a specified name.
     *
     * @param name the name of the player
     */
    public void setPlayer(String name) {
        player = new Player(name);
    }

    /**
     * Returns the dealer Player instance.
     * 
     * @return the dealer player instance
     */
    public static Player getDealer() {
        return dealer;
    }

    /**
     * Returns the player Player instance.
     * 
     * @return the player instance
     */
    public static Player getPlayer() {
        return player;
    }

    /**
     * Starts a new round, increments the round number, and completes the round logic.
     */
    public void startNewRound() {
        incrementRoundNumber();
        RoundClass round = new RoundClass(roundNumber);
        round.completeRound();
    }

    /**
     * Resets the game by reinitializing the dealer and clearing the player, 
     * allowing for a new game to be set up.
     */
    public void restart() {
        player = null;
        dealer = new Player("dealer");
        roundNumber = 0;
        /*
         * Needs to subsequently visually offer a box using setPlayer() that disappears 
         * after 1 instance of this.player is configured.
         */
    }
}
