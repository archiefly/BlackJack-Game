import java.util.List;
import javax.smartcardio.Card;

/**
 * The {@code Player} class represents a player in a card game.
 * It contains information about the player's name, hand, score, and current status 
 * (e.g., standing or busted).
 */
public class Player {

    private String name;
    private Card[] hand;
    private boolean isBusted;
    private boolean isStanding;
    private int score;
    private int winPoint;

    /**
     * Constructor to create a new player with a specified name.
     *
     * @param name the name of the player
     */
    public Player(String name) {
        this.name = name;
        this.hand = new Card[6];
        this.score = setScore();
        this.isBusted = setBust();
        this.isStanding = false;
    }

    /**
     * Sets a new name for the player.
     *
     * @param changeName the new name to assign to the player
     */
    public void setName(String changeName) {
        this.name = changeName;
    }

    /**
     * Adds a card to the player's hand at the specified index.
     *
     * @param i       the index at which to place the card
     * @param newHand the card to add to the player's hand
     */
    private void setHand(int i, Card newHand) {
        this.hand[i] = newHand;
    }

    /**
     * Sets the player's status to standing, indicating they do not wish to draw more cards.
     */
    public void setStand() {
        isStanding = true;
    }

    /**
     * Determines whether the player has busted (score > 21) and sets the bust status accordingly.
     * If the player is busted, it also sets the player's status to standing.
     *
     * @return {@code true} if the player is busted; {@code false} otherwise
     */
    private boolean setBust() {
        if (getScore() > 21) {
            setStand();
            return true;
        } else {
            return false;
        }
    }

    /**
     * Calculates and sets the player's score based on the value of the cards in their hand.
     *
     * @return the player's current score
     * @throws IllegalArgumentException if the calculated score is negative
     */
    private int setScore() {
        if (calculateHandValue() < 0) {
            throw new IllegalArgumentException("Score cannot be negative");
        }
        return calculateHandValue();
    }

    public void addWinPoint() {
        this.winPoint = this.winPoint + 1;
    }

    /**
     * Retrieves the player's name.
     *
     * @return the player's name
     */
    public String getName() {
        return this.name;
    }

    /**
     * Retrieves the card at the specified index in the player's hand.
     *
     * @param i the index of the card in the player's hand
     * @return the card at the specified index
     */
    public Card getHand(int i) {
        return this.hand[i];
    }

    /**
     * Checks whether the player has busted.
     *
     * @return {@code true} if the player is busted; {@code false} otherwise
     */
    public boolean getIsBusted() {
        return this.isBusted;
    }

    /**
     * Checks whether the player is standing, indicating they do not wish to draw more cards.
     *
     * @return {@code true} if the player is standing; {@code false} otherwise
     */
    public boolean getIsStanding() {
        return this.isStanding;
    }

    /**
     * Retrieves the player's current score.
     *
     * @return the player's score
     */
    public int getScore() {
        return this.score;
    }

    public int getWinPoint() {
        return this.winPoint;
    }

    /**
     * Adds a card to the player's hand. It finds the first available position in the hand 
     * (where the slot is {@code null}) and assigns the new card to that position.
     *
     * @param card the card to be added to the player's hand
     */
    private void addCard(Card newCard) {
        for (int i = 0; i < 6; i++) {
            if (getHand(i) == null) {
                setHand(i, newCard);
                break;
            }
        }
    }

    /**
     * Calculates the total value of the player's hand by summing up the values of the cards.
     *
     * @return the total value of the player's hand
     */
    private int calculateHandValue() {
        int value = 0;
        for (int i = 0; i < 6; i++) {
            if (getHand(i) == null) {
                break;
            } else {
                value += getHand(i).getRank().getValue();
            }
        }
        return value;
    }

    /**
     * Draws a card from the deck and adds it to the player's hand.
     * 
     * This method checks if the player is standing; if not, a card is drawn from the deck using 
     * the {@code dealCard()} method and added to the player's hand using {@code addCard()}.
     * If the player's score exceeds 21 after drawing the card, the player is set to be "busted" 
     * using {@code setBust()}.
     * 
     * Preconditions:
     * - The player should not be standing.
     * 
     * Postconditions:
     * - A card is added to the player's hand if they are not standing.
     * - The player's status is updated to "busted" if their score exceeds 21.
     */
    public void hit(Card newCard) {
        if (!getIsStanding()) {
            addCard(newCard);
        }
        if (getScore() > 21) {
            setBust();
        }
    }

    /**
     * Resets the player's hand by setting all card slots in the hand array to {@code null}.
     */
    public void resetHand() {
        for (int i = 0; i < 6; i++) {
            setHand(i, null);
        }
    }

    /**
     * Returns a string representation of the player's status, including their name, score, 
     * and current status (e.g., standing, busted, or still playing).
     *
     * @return a string representation of the player's status
     */
    @Override
    public String toString() {
        String status;
        if (getIsBusted()) {
            status = " is busted";
        } else if (getIsStanding() && !getIsBusted()) {
            status = " has stood";
        } else {
            status = " is still playing the round";
        }
        return getName() + " has a score of " + getScore() + status;
    }
}