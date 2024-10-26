import java.util.ArrayList;
import java.util.Collections;

/**
 * The {@code CardDeck} class represents a standard deck of playing cards.
 * It provides methods for initializing the deck, shuffling, and dealing cards.
 */
public class CardDeck {
    
    /** 
     * The list of cards in the deck.
     */
    private ArrayList<Card> cards;

    /**
     * Initializes the deck with an empty list of cards.
     * This method is called internally by the constructor to prepare for adding cards.
     */
    public void setDeck() {
        this.cards = new ArrayList<>();
    }

    /**
     * Constructs a new {@code CardDeck} with a full set of cards.
     * Initializes the deck with all combinations of suits and ranks,
     * then shuffles the deck to randomize the order of the cards.
     */
    public CardDeck() {
        setDeck();
        for (Suit suit : Suit.values()) {
            for (Rank rank : Rank.values()) {
                cards.add(new Card(suit, rank));
            }
        }
        shuffle();
    }

    /**
     * Shuffles the deck, randomizing the order of the cards.
     */
    public void shuffle() {
        Collections.shuffle(cards);
    }

    /**
     * Deals a card from the deck by removing the last card.
     * 
     * @return the card removed from the deck, or {@code null} if the deck is empty
     */
    public Card dealCard() {
        return cards.isEmpty() ? null : cards.remove(cards.size() - 1);
    }
}
