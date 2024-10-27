Brief Description:
This Java-based Blackjack Game is a single-player card game where you play against a 
virtual dealer. The objective is to get a hand value as close to 21 as possible without 
going over (busting). Each round, you can place bets using a spinner, then decide whether to 
"hit" (draw a card) or "stand" (keep your current hand) based on your hand’s value.

The game interface includes panels displaying your hand, the dealer's hand, and a section for 
betting. If you exceed 21, you lose the round, and if the dealer has a higher hand value without 
busting, the dealer wins. The dealer follows set rules for drawing cards, aiming to match or exceed 
your hand value up to 21. You can restart the game at any point to reset your funds and start fresh

Class Descriptions:
Card:       It provides methods to access the suit and rank of the card, as well as a string representation.
CardDeck:   It provides methods for initializing the deck, shuffling, and dealing cards.
Player:     It manages the player's name, hand, current score, money, win points, 
            and status (e.g., standing or busted). The class includes methods 
            for adding cards to the player's hand, calculating the hand's total value,
            and resetting the player's state.
RoundClass: Represents a single round of the game, managing players' hands, 
            actions, and determining the round's outcome.
BlackjackGameUI:    Plays the game and makes the UI


Special Feature: 
Github: https://github.com/archiefly/Poker-Game.git

Special Feature 2: 
UI experience with betting to make it more interesting. Didn't work unfortunately.