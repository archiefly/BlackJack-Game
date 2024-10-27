import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

public class BlackjackGameUI extends JFrame {
    private JPanel playerPanel;
    private JPanel dealerPanel;
    private JPanel buttonPanel;
    private JPanel statusPanel;
    private JPanel containerPanel; // Container panel to hold playerPanel and buttonPanel
    private JPanel playerStatusContainer; // Container panel to hold playerPanel and statusLabel
    private JPanel playerHandValuePanel; // Panel to center player hand value label
    private JPanel dealerHandValuePanel; // Panel to center dealer hand value label
    private JPanel gameStatusPanel; // Panel to center game status label
    private JLabel statusLabel;
    private JLabel playerHandValueLabel;
    private JLabel dealerHandValueLabel;
    private JButton hitButton;
    private JButton standButton;
    private JButton newGameButton;
    private CardDeck cardDeck; // Assuming cardDeck is an instance of CardDeck
    private int playerHandValue; // Track the player's hand value
    private int dealerHandValue; // Track the dealer's hand value
    private ArrayList<Card> playerHand; // Track the player's hand

    public BlackjackGameUI() {
        // Initialize panels and buttons
        playerPanel = new JPanel();
        dealerPanel = new JPanel();
        buttonPanel = new JPanel();
        statusPanel = new JPanel();
        containerPanel = new JPanel(new BorderLayout()); // Container panel with BorderLayout
        playerStatusContainer = new JPanel(new BorderLayout()); // Container panel with BorderLayout
        playerHandValuePanel = new JPanel(new FlowLayout(FlowLayout.CENTER)); // Panel to center player hand value label
        dealerHandValuePanel = new JPanel(new FlowLayout(FlowLayout.CENTER)); // Panel to center dealer hand value label
        gameStatusPanel = new JPanel(new FlowLayout(FlowLayout.CENTER)); // Panel to center game status label
        statusLabel = new JLabel("Game Status: ");
        playerHandValueLabel = new JLabel("Player Hand Value: 0");
        dealerHandValueLabel = new JLabel("Dealer Hand Value: 0");
        hitButton = new JButton("Hit");
        standButton = new JButton("Stand");
        newGameButton = new JButton("New Game");

        // Set layout for player panel to display cards horizontally and centralize them
        playerPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        // Set layout for dealer panel to centralize the dealer's card
        dealerPanel.setLayout(new FlowLayout(FlowLayout.CENTER));

        // Add player hand value label to playerHandValuePanel
        playerHandValuePanel.add(playerHandValueLabel);
        // Add dealer hand value label to dealerHandValuePanel
        dealerHandValuePanel.add(dealerHandValueLabel);
        // Add game status label to gameStatusPanel
        gameStatusPanel.add(statusLabel);

        // Add player panel and status label to the playerStatusContainer
        playerStatusContainer.add(gameStatusPanel, BorderLayout.NORTH);
        playerStatusContainer.add(playerHandValuePanel, BorderLayout.CENTER);
        playerStatusContainer.add(playerPanel, BorderLayout.SOUTH);

        // Add buttons to button panel
        buttonPanel.add(hitButton);
        buttonPanel.add(standButton);
        buttonPanel.add(newGameButton);

        // Add playerStatusContainer and button panel to the container panel
        containerPanel.add(playerStatusContainer, BorderLayout.NORTH);
        containerPanel.add(buttonPanel, BorderLayout.SOUTH);

        // Add panels to the frame
        add(containerPanel, BorderLayout.SOUTH); // Add container panel to the bottom
        add(dealerHandValuePanel, BorderLayout.NORTH); // Add dealer hand value panel to the top
        add(dealerPanel, BorderLayout.CENTER); // Add dealer panel to the center

        // Add action listeners to buttons
        hitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Call logic to deal a card to the player
                dealCardToPlayer();
            }
        });

        standButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Call logic for standing and ending player's turn
                handleStand();
            }
        });

        newGameButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Reset the game and start a new round
                startNewGame();
            }
        });

        // Initialize the card deck and player's hand value
        cardDeck = new CardDeck();
        playerHandValue = 0;
        dealerHandValue = 0;
        playerHand = new ArrayList<>();

        // Automatically deal a card to the dealer when the UI initializes
        dealCardToDealer();

        // Automatically deal a card to the player when the UI initializes
        dealCardToPlayer();
    }

    public boolean dealCardToPlayer() {
        Card card = cardDeck.dealCard(); // Assuming cardDeck is an instance of CardDeck
        if (card != null) {
            playerHand.add(card); // Add card to player's hand
            ImageIcon cardImage = loadCardImage(card);
            JLabel cardLabel = new JLabel(cardImage);
            playerPanel.add(cardLabel);
            playerPanel.revalidate();
            playerPanel.repaint();

            // Update player's hand value
            playerHandValue += card.getRank().getValue();

            // Adjust for Aces if player is busted
            if (playerHandValue > 21) {
                for (Card c : playerHand) {
                    if (c.getRank() == Rank.Ace && playerHandValue > 21) {
                        playerHandValue -= 10; // Change Ace value from 11 to 1
                    }
                }
            }

            // Update player's hand value label
            playerHandValueLabel.setText("Player Hand Value: " + playerHandValue);

            // Check if player is busted
            if (playerHandValue > 21) {
                statusLabel.setText("Game Status: Busted!");
                hitButton.setEnabled(false);
                standButton.setEnabled(false);
            }
        }
        return true;
    }

    public boolean dealCardToDealer() {
        Card card = cardDeck.dealCard(); // Assuming cardDeck is an instance of CardDeck
        if (card != null) {
            ImageIcon cardImage = loadCardImage(card);
            JLabel cardLabel = new JLabel(cardImage);
            dealerPanel.add(cardLabel);
            dealerPanel.revalidate();
            dealerPanel.repaint();

            // Update dealer's hand value
            dealerHandValue += card.getRank().getValue();

            // Update dealer's hand value label
            dealerHandValueLabel.setText("Dealer Hand Value: " + dealerHandValue);
        }
        return true;
    }

    private ImageIcon loadCardImage(Card card) {
        String cardName;
        if (card.getRank().getValue() >= 2 && card.getRank().getValue() <= 10) {
            cardName = card.getRank().getValue() + "_" + card.getSuit().name().toLowerCase() + ".png";
        } else {
            cardName = card.getRank().name().toLowerCase() + "_" + card.getSuit().name().toLowerCase() + ".png";
        }
        String imagePath = "/images/cards/" + cardName;
        java.net.URL imgURL = getClass().getResource(imagePath);
        if (imgURL != null) {
            ImageIcon originalIcon = new ImageIcon(imgURL);
            Image originalImage = originalIcon.getImage();
            Image resizedImage = originalImage.getScaledInstance(100, 150, Image.SCALE_SMOOTH); // Resize to 100x150
            return new ImageIcon(resizedImage);
        } else {
            System.err.println("Couldn't find file: " + imagePath);
            return null;
        }
    }

    private void handleStand() {
        // Disable hit and stand buttons
        hitButton.setEnabled(false);
        standButton.setEnabled(false);

        // Dealer deals cards to himself until his hand value is equal to or higher than the player's hand value, or until he busts
        Timer timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (dealerHandValue < playerHandValue && dealerHandValue <= 21) {
                    dealCardToDealer();
                } else {
                    ((Timer) e.getSource()).stop();
                    // Check if dealer is busted
                    if (dealerHandValue > 21) {
                        statusLabel.setText("Game Status: Dealer Busted! You Win!");
                    } else if (dealerHandValue >= playerHandValue) {
                        statusLabel.setText("Game Status: Dealer Wins!");
                    }
                }
            }
        });
        timer.start();
    }

    private void startNewGame() {
        // Reset the game and start a new round
        playerPanel.removeAll();
        dealerPanel.removeAll();
        playerPanel.revalidate();
        playerPanel.repaint();
        dealerPanel.revalidate();
        dealerPanel.repaint();
        playerHandValue = 0;
        dealerHandValue = 0;
        playerHand.clear();
        statusLabel.setText("Game Status: ");
        playerHandValueLabel.setText("Player Hand Value: 0");
        dealerHandValueLabel.setText("Dealer Hand Value: 0");
        hitButton.setEnabled(true);
        standButton.setEnabled(true);
        cardDeck = new CardDeck();
        dealCardToDealer();
        dealCardToPlayer();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                BlackjackGameUI gameUI = new BlackjackGameUI();
                gameUI.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                gameUI.setSize(800, 600);
                gameUI.setVisible(true);
            }
        });
    }
}
