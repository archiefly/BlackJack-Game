/**
 * Represents a single round of the game, managing players' hands, 
 * actions, and determining the round's outcome.
 */
public class RoundClass {

    private int round;
    private CardDeck roundCardDeck;
    private boolean finished = setFinished();
    private Player player = GameClass.getPlayer();
    private Player dealer = GameClass.getDealer();
    private int bet;

    /**
     * Initializes a new round with the specified round number, 
     * setting up a fresh deck and resetting player and dealer hands.
     *
     * @param round the current round number
     */
    public RoundClass(int round) {
        this.round = round;
        this.roundCardDeck = new CardDeck();
        dealer.resetHand();
        dealer.resetStand();
        player.resetHand();
        player.resetStand();
        this.finished = false;
        this.bet = 0;
    }

    /**
     * @return the current round number
     */
    public int getRound() {
        return this.round;
    }

    /**
     * @return whether the round is finished
     */
    public boolean getFinished() {
        return this.finished;
    }

    /**
     * @return the current bet amount
     */
    public int getBet() {
        return this.bet;
    }

    /**
     * Sets the bet amount if it falls within the allowed range.
     *
     * @param i the amount to bet
     * @throws IllegalArgumentException if the bet is negative or exceeds the maximum limit
     */
    public void setBet(int i) {
        int maxBet = 100;
        if (i > 0 && i <= maxBet) {
            this.bet = i;
        } else if (i < 0) {
            throw new IllegalArgumentException("Bets cannot be negative");
        } else {
            throw new IllegalArgumentException("Your bet exceeds the maximum allowed bet");
        }
    }

    /**
     * Determines whether the round is finished based on both players' states.
     *
     * @return {@code true} if the round is complete, otherwise {@code false}
     */
    private boolean setFinished() {
        if (dealer.getIsStanding() && player.getIsStanding()) {
            if (findWinner() == null) {
                return false;
            } else {
                findWinner().addWinPoint();
                return true;
            }
        } else {
            return false;
        }
    }

    /**
     * Determines the winner based on the values of players' hands.
     *
     * @return the winning player, or {@code null} if there is no winner
     */
    private Player findWinner() {
        if (player.getIsBusted() && dealer.getIsBusted()) {
            return null;
        } else if (!player.getIsBusted()) {
            if (player.calculateHandValue() > dealer.calculateHandValue()) {
                return player;
            } else if ((player.calculateHandValue() < dealer.calculateHandValue()) 
                && !dealer.getIsBusted()) {
                return dealer;
            } else {
                return null;
            }
        } else {
            return dealer;
        }
    }

    /**
     * Executes the actions for a single round, dealing initial cards and handling 
     * both player and dealer actions based on their hand values.
     */
    private void playRound() {
        player.addCard(roundCardDeck.dealCard());
        dealer.addCard(roundCardDeck.dealCard());
        player.addCard(roundCardDeck.dealCard());
        dealer.addCard(roundCardDeck.dealCard());

        while (!player.getIsBusted() && !player.getIsStanding()) {
            // Prompt for hit or stand
            //Select button for hit or stand
            if (/*hit */) {
                player.hit(roundCardDeck.dealCard());
            } else if (/*stand */) {
                player.setStand();
            }
        }
        while (dealer.calculateHandValue() < 17) {
            dealer.hit(roundCardDeck.dealCard());
        }
        dealer.setStand();
    }

    /**
     * Completes the round by calculating the final scores and awarding bets based on the result.
     */
    public void completeRound() {
        setBet(/*player chooses bet */);
        while (findWinner() == null) {
            playRound();
        }
        if (findWinner() == dealer) {
            dealer.setMoney(getBet());
            player.setMoney(-getBet());
        } else if (findWinner() == player) {
            dealer.setMoney(-1.5 * getBet());
            player.setMoney(1.5 * getBet());
        }
        setFinished();
    }
}
