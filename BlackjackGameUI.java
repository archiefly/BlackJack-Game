import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class BlackjackGameUI extends JFrame {
    private JPanel playerPanel;
    private JPanel dealerPanel;
    private JPanel buttonPanel;
    private JPanel statusPanel;
    private JLabel statusLabel;
    private JButton hitButton;
    private JButton standButton;
    private JButton newGameButton;
    private CardDeck cardDeck; // Assuming cardDeck is an instance of CardDeck

    public BlackjackGameUI() {
        // Initialize panels and buttons
        playerPanel = new JPanel();
        dealerPanel = new JPanel();
        buttonPanel = new JPanel();
        statusPanel = new JPanel();
        statusLabel = new JLabel("Game Status: ");
        hitButton = new JButton("Hit");
        standButton = new JButton("Stand");
        newGameButton = new JButton("New Game");

        // Add buttons to button panel
        buttonPanel.add(hitButton);
        buttonPanel.add(standButton);
        buttonPanel.add(newGameButton);

        // Add panels to the frame
        add(playerPanel, BorderLayout.WEST);
        add(dealerPanel, BorderLayout.EAST);
        add(buttonPanel, BorderLayout.SOUTH);
        add(statusPanel, BorderLayout.NORTH);

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

        // Initialize the card deck
        cardDeck = new CardDeck();

        // Automatically deal a card to the player when the UI initializes
        dealCardToPlayer();
    }

    public boolean dealCardToPlayer() {
        Card card = cardDeck.dealCard(); // Assuming cardDeck is an instance of CardDeck
        if (card != null) {
            ImageIcon cardImage = loadCardImage(card);
            JLabel cardLabel = new JLabel(cardImage);
            playerPanel.add(cardLabel);
            playerPanel.revalidate();
            playerPanel.repaint();
        }
        return true;
    }

    private ImageIcon loadCardImage(Card card) {
        String cardName = card.getRank().name().toLowerCase() + "_" + card.getSuit().name().toLowerCase() + ".png";
        String imagePath = "Images/" + cardName;
        return new ImageIcon(getClass().getResource(imagePath));
    }

    public boolean handleStand() {
        // Logic for standing
        return true; // Placeholder return value
    }

    private void startNewGame() {
        // Logic for starting a new game
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
