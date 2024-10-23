
import java.util.ArrayList;
import java.util.Collections;
import javax.smartcardio.Card;

public class CardDeck {

    public class Card {

        private final Suit suit;
        private final Rank rank;

        public Card(Suit suit, Rank rank) {
            this.suit = suit;
            this.rank = rank;
        }

        public Suit getSuit() {
            return suit;
        }

        public Rank getRank() {
            return rank;
        }

        public String toString() {
            return rank + " of " + suit;
        }
    }

    public enum Suit {
        Hearts,
        Diamonds,
        Clubs,
        Spades
    }

    public enum Rank {
        Two(2),
        Three(3),
        Four(4),
        Five(5),
        Six(6),
        Seven(7),
        Eight(8),
        Nine(9),
        Ten(10),
        Jack(10),
        Queen(10),
        King(10),
        Ace(11);

        private final int value;

        Rank(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }
    public class CardDeck {
        private ArrayList<Card> cards;
    
        public CardDeck() {
            cards = new ArrayList<>();
            for (Suit suit : Suit.values()) {
                for (Rank rank : Rank.values()) {
                    cards.add(new Card(suit, rank));
                }
            }
            shuffle();
        }
    
        public void shuffle() {
            Collections.shuffle(cards);
        }
    
        public Card dealCard() {
            return cards.remove(cards.size() - 1);
        }
    }
    
}
