How to Play
Start the Game:(To start the game you need to run BlackjackGameUI.java) When the game starts, both the player and dealer are dealt two cards.
Player Actions:
Hit: Draw a card from the deck.
Stand: End your turn, allowing the dealer to play.
Dealer's Turn: The dealer will follow standard Blackjack rules (e.g., hitting until reaching 17 or higher).
Winning:
If your hand's total is closer to 21 than the dealer's, you win!
If your hand exceeds 21, you "bust" and lose the game.
If both hands tie, the result is a "push" (no winner).
New Game: Click the "New Game" button to reset and start a new round.
Classes Overview
Card.java: Represents an individual playing card, defined by a Rank (e.g., Ace, King) and a Suit (e.g., Hearts, Spades).

CardDeck.java: Manages a deck of cards, with functionality to shuffle and deal cards randomly.

Player.java: Manages the player's hand of cards and calculates the score based on card values.

Rank.java (Enum): Defines the ranks of cards (Ace, Two, King, etc.) and their associated values used in Blackjack scoring.

Suit.java (Enum): Defines the four card suits (Hearts, Diamonds, Clubs, Spades).

BlackjackGameUI.java: The graphical user interface for the game, providing buttons and panels for interacting with the game. It handles user input and updates the game state.