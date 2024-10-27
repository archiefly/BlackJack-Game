import java.util.ArrayList;
import java.util.List;

/**
 * Represents a single round of the game, managing players' hands, 
 * actions, and determining the round's outcome.
 */
public class RoundClass {

    // Static fields that represent a shared state across all instances, if any
    private static List<RoundClass> allRounds = new ArrayList<>();

    private int round;
    private CardDeck roundCardDeck;
    private boolean finished;
    private Player player;
    private Player dealer;
    private int bet;

    /**
     * Initializes a new round with the specified round number, 
     * setting up a fresh deck and resetting player and dealer hands.
     *
     * @param round the current round number
     */
    public RoundClass(int round) {
        this.round = round;
        player = BlackjackGameUI.getPlayer();
        dealer = BlackjackGameUI.getDealer();
        this.roundCardDeck = new CardDeck();
        dealer.resetHand();
        dealer.resetStand();
        player.resetHand();
        player.resetStand();
        this.finished = false;
        this.bet = 0;
        allRounds.add(this); // Add this instance to the tracking list
    }

    /**
     * Clears all tracked instances and resets static fields.
     */
    public static void resetAllRounds() {
        for (RoundClass round : allRounds) {
            round.clearRound();  // Calls the clear method we defined earlier
        }
        allRounds.clear();       // Clear the tracking list
    }

    private void clearRound() {
        // Reset fields specific to this instance
        this.round = 0;
        this.roundCardDeck = null;
        this.finished = false;
        this.bet = 0;
        this.player = null;
        this.dealer = null;
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

    private CardDeck getRoundCardDeck() {
        return this.roundCardDeck;
    }

    public void newRound() {
        this.round += 1;
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
     *
    private void setFinished() {
        if (dealer.getIsStanding() && player.getIsStanding()) {
            if (findWinner() != null) {
                findWinner().addWinPoint();
            }
        }
    }
    */

    public Card dealRoundCard() {
        return getRoundCardDeck().dealCard();
    }

    /**
     * Determines the winner based on the values of players' hands.
     *
     * @return the winning player, or {@code null} if there is no winner
     */
    public Player findWinner() {
        if (player.getIsBusted() && dealer.getIsBusted()) {
            return null;
        } else if (!player.getIsBusted() && dealer.getIsBusted()) {
            player.addWinPoint();
            return player;
        } else if (player.getIsBusted() && !dealer.getIsBusted()) {
            dealer.addWinPoint();
            return dealer;
        } else {
            if (player.calculateHandValue() > dealer.calculateHandValue()) {
                player.addWinPoint();
                return player;
            } else if (player.calculateHandValue() < dealer.calculateHandValue()) {
                dealer.addWinPoint();
                return dealer;
            } else {
                return null;
            }
        }
    }

    public String findWinnerToString() {
        
        if (findWinner() == player) {
            return "Game Status: You Win!";
        } else if (findWinner() == dealer) {
            return "Game Status: Dealer Wins!";
        } else {
            return "Game Status: Draw!";
        }
    }

/* 
     * Completes the round by calculating the final scores and awarding bets based on the result.

    public void completeRound() {
        getBet(player chooses bet );
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
*/
}
