public class GameClass {

    private static Player dealer;
    private static Player player;

    /**
     * Constructor to create a new Game.
     *
     * @param name the name of the player
     */
    private GameClass() {
        dealer = new Player("dealer");
        /**
         * Needs to subsequently visually offer a box using setPlayer() that dissapears 
         * after 1 instance of this.player is configured.
         */
    }

    public void setPlayer(String name) {
        player = new Player(name);
    }

    public static Player getDealer() {
        return dealer;
    }

    public static Player getPlayer() {
        return player;
    }

    public void startGame() {
        
    }

    public void startNewRound(int i) {
   
    }

    public void restart() {
        player = null;
        dealer = new Player("dealer");
        /**
         * Needs to subsequently visually offer a box using setPlayer() that dissapears 
         * after 1 instance of this.player is configured.
         */
    }
}