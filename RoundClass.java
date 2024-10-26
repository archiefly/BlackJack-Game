public class RoundClass {

    private int round;
    private CardDeck roundCardDeck;
    private boolean finished = setFinished();
    private Player player = GameClass.getPlayer();
    private Player dealer = GameClass.getDealer();
    //* private int bet;

    
    private RoundClass(int round) {
        this.round = round;
        this.roundCardDeck = new CardDeck();
        dealer.resetHand();
        dealer.resetStand();
        player.resetHand();
        player.resetStand();
        this.finished = setFinished();
    }

    public int getRound() {
        return this.round;
    }

    private CardDeck getRoundCardDeck() {
        return this.roundCardDeck;
    }

    public boolean getFinished() {
        return this.finished;
    }

    private boolean setFinished() {
        if (dealer.getIsStanding() && player.getIsStanding()) {
            if (findWinner() == null) {
//* tie thing */
                return false;
            } else {
                return true;
            }
        } else {
            return false;
        }
    }

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

    private void playTieBreaker() {

    }

    private void playRound() {
        //* player choose bet */
        player.addCard(roundCardDeck.dealCard());
        dealer.addCard(roundCardDeck.dealCard());
        player.addCard(roundCardDeck.dealCard());
        dealer.addCard(roundCardDeck.dealCard());
    }
}