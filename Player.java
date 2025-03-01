/**
 * The {@code Player} class represents a player in a card game.
 * It manages the player's name, hand, current score, money, win points, 
 * and status (e.g., standing or busted). The class includes methods 
 * for adding cards to the player's hand, calculating the hand's total value,
 * and resetting the player's state.
 */
public class Player {

    private String name;
    private Card[] hand;
    private boolean isBusted;
    private boolean isStanding;
    private int winPoint;
    private double money;

    /**
     * Constructs a new {@code Player} with the specified name.
     * Initializes the player's hand and sets default values for status, score, and money.
     *
     * @param name the name of the player
     */
    public Player(String name) {
        this.name = name;
        this.hand = new Card[10];
        this.isStanding = false;
        this.isBusted = false;
        this.money = 50;
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
     * Resets the player's standing status to allow drawing more cards.
     */
    public void resetStand() {
        isStanding = false;
        isBusted = false;
    }

    /**
     * Determines whether the player has busted (score > 21) and sets the bust status.
     * If the player is busted, sets the player's status to standing.
     */
    private void setBust() {
        if (calculateHandValue() > 21) {
            setStand();
            this.isBusted = true;
        }
    }

    /**
     * Increments the player's win points by 1.
     */
    public void addWinPoint() {
        this.winPoint++;
    }

    /**
     * Updates the player's money by adding or subtracting a specified amount.
     *
     * @param i the amount to add to the player's current money
     */
    public void setMoney(double i) {
        this.money = getMoney() + i;
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
     * @return the card at the specified index, or {@code null} if the slot is empty
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
     * Retrieves the player's win points.
     *
     * @return the number of win points the player has accumulated
     */
    public int getWinPoint() {
        return this.winPoint;
    }

    /**
     * Retrieves the player's current money.
     *
     * @return the amount of money the player has
     */
    public double getMoney() {
        return this.money;
    }

    /**
     * Adds a card to the player's hand at the first available position.
     * 
     * @param newCard the card to add to the player's hand
     */
    public void addCard(Card newCard) {
        for (int i = 0; i < 10; i++) {
            if (getHand(i) == null) {
                setHand(i, newCard);
                break;
            }
        }
    }

    /**
     * Calculates the total value of the player's hand by summing the values of each card.
     * Adjusts for Aces to prevent busting if the hand's total exceeds 21.
     *
     * @return the total value of the player's hand
     * @throws IllegalArgumentException if the score is negative 
     *      (should not occur under normal rules)
     */
    public int calculateHandValue() {
        int value = 0;
        int aceCount = 0;

        for (int i = 0; i < 10; i++) {
            if (getHand(i) == null) {
                break;
            } else {
                if (getHand(i).getRank() == Rank.Ace) {
                    aceCount++;
                }
                value += getHand(i).getRank().getValue();
            }
        }

        // Adjust for Aces if value exceeds 21
        for (int i = 0; i < aceCount; i++) {
            if (value > 21) {
                value -= 10;
            } else {
                break;
            }
        }

        if (value < 0) {
            throw new IllegalArgumentException("Score cannot be negative");
        }
        return value;
    }

    /**
     * Adds a card to the player's hand if they are not standing and updates bust status.
     * 
     * @param newCard the card to add to the player's hand
     */
    public void hit(Card newCard) {
        if (!getIsStanding()) {
            addCard(newCard);
            setBust();
        }
    }

    /**
     * Clears all cards from the player's hand by setting each slot to {@code null}.
     */
    public void resetHand() {
        for (int i = 0; i < 10; i++) {
            setHand(i, null);
        }
    }
}
