How to Play:
            Start the Game:(To start the game you need to run BlackjackGameUI.java) When the game starts, both the player and dealer are dealt two cards.

Player Actions:
Hit:                    Draw a card from the deck.
Stand:                  End your turn, allowing the dealer to play.
Dealer's Turn:          The dealer will follow standard Blackjack rules (e.g., hitting until reaching 17 or higher).
Winning:
                        If your hand's total is closer to 21 than the dealer's, you win!
                        If your hand exceeds 21, you "bust" and lose the game.
                        If both hands tie, the result is a "push" (no winner).
                        New Round: Click the "New Round" button to reset and start a new round.
                        Restart Game: Click on the "Restart Game button to restart the game and delete round betting history.


Class Descriptions:
Card:                   It provides methods to access the suit and rank of the card, as well as a string representation.
CardDeck:               It provides methods for initializing the deck, shuffling, and dealing cards.
Player:                 It manages the player's name, hand, current score, money, win points, 
                        and status (e.g., standing or busted). The class includes methods 
                        for adding cards to the player's hand, calculating the hand's total value,
                        and resetting the player's state.
RoundClass:             Represents a single round of the game, managing players' hands, 
                        actions, and determining the round's outcome.
BlackjackGameUI:        Plays the game and makes the UI


Special Feature 1: 
Github: https://github.com/archiefly/Poker-Game.git

Special Feature 2: 
UI experience with betting to make it more interesting. Didn't work unfortunately.
