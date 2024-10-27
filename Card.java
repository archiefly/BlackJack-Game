/**
 * The {@code Card} class represents a single playing card with a specific suit and rank.
 * It provides methods to access the suit and rank of the card, as well as a string representation.
 */
public class Card {
    
    private final Suit suit;
    private final Rank rank;

    /**
     * Constructs a new {@code Card} with the specified suit and rank.
     *
     * @param suit the suit of the card
     * @param rank the rank of the card
     */
    public Card(Rank rank , Suit suit) {
        this.suit = suit;
        this.rank = rank;
    }

    /**
     * Returns the suit of this card.
     *
     * @return the suit of this card
     */
    public Suit getSuit() {
        return suit;
    }

    /**
     * Returns the rank of this card.
     *
     * @return the rank of this card
     */
    public Rank getRank() {
        return rank;
    }

    /**
     * Returns a string representation of the card, in the format "{rank} of {suit}".
     *
     * @return a string representation of the card
     */
    @Override
    public String toString() {
        return rank + " of " + suit;
    }
}
