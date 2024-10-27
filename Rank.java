/**
 * The {@code Rank} enum represents the rank of a playing card. Each rank has an associated
 * integer value commonly used in card games (e.g., for calculating hand values).
 */
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

    /**
     * Constructs a {@code Rank} with the specified value.
     *
     * @param value the integer value of the rank
     */
    Rank(int value) {
        this.value = value;
    }

    /**
     * Returns the integer value associated with this rank.
     *
     * @return the integer value of this rank
     */
    public int getValue() {
        return value;
    }
}
