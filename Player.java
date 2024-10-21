import java.util.List;

import javax.smartcardio.Card;

/**
 * The {@code Player} class represents a player in a card game.
 * It contains information about the player's name, hand, score, and current status 
 * (e.g, standing, busted).
 */
public class Player {

    private String name; // Player's name
    private CardDeck.Card[] hand; // Player's hand
    private boolean isBusted; // Indicates if player is busted
    private boolean isStanding; // Indicates if player is standing
    private int score; // Player's score

    // Constructor to initialize player
    private Player(String name) {
        this.name = name;
        this.hand = new CardDeck.Card[6];
        this.isBusted = false;
        this.isStanding = false;
        this.score = setScore();
    }

    private int setScore() {
        if (calculateHandValue() < 0) {
            throw new IllegalArgumentException("Score cannot be negative");
        }
        return calculateHandValue();
    }

    /* Setters for player name, hand, score */

    public String getName() {
        return this.name;
    }

    public List<CardDeck.Card> getHand() {
        return this.hand;
    }

    public boolean getIsBusted() {
        return this.isBusted;
    }

    public boolean getIsStanding() {
        return this.isStanding;
    }

    public int getScore() {
        return this.score;
    }


    /**
     * Adds a card to the player's hand array.
     * 
     * @param card the card to be added to the player's hand, from the {@code CardDeck.Card} class.
     */
    void addCard(CardDeck.Card card) {
        for (int i = 0; i < 6; i++) {
            if (hand[i] == null) {
                hand[i] = card;
                break;
            }
        }
    }

    /**
     * Calculates the value of the player's hand based on the cards held.
     * This method will return the calculated hand value.
     * 
     * @return the total value of the player's hand.
     */
    int calculateHandValue() {
        int value = 0;
        for (int i = 0; i < 6; i++) {
            if (hand[i] == null) {
                break;
            } else {
                value += hand[i].getRank().getValue();
            }
        }
        return value;
    }

    /**
     * Allows the player to hit (draw a card) from the deck.
     * 
     * @param deck the {@code CardDeck} from which the player will draw a card.
     */
    void hit(CardDeck deck) {
        CardDeck.Card newCard = deck.dealCard();
        addCard(newCard);
        if (score > 21)
    }

    /**
     * Sets the player's status to standing, indicating they do not wish to draw more cards.
     */
    void stand() {
        isStanding = true;  // Implement standing logic
    }

    /**
     * Setter that resets the player's hand by setting all card slots in the hand array to 
     * {@code null}.
     */
    void resetHand() {
        for (int i = 0; i < 6; i++) {
            hand[i] = null;
        }
    }

    /**
     * Returns the string representation of the player's status.
     * 
     * @return the player's status as a string.
     */
    @Override
    public String toString() {
        return name + " has a score of " + score;  // Customize this string representation
    }
}
