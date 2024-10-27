import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
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
    
    private CardDeck cardDeck; // Assuming cardDeck is an instance of CardDeck

    private static Player dealer;
    private static Player player;
    private int roundNumber;
    private RoundClass currentRound;

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
    private JButton restartGameButton;
    private JButton newRoundButton;

    public BlackjackGameUI() {

        dealer = new Player("dealer");
        player = new Player("player");
        roundNumber = 0;
        addRoundNumber();

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
        restartGameButton = new JButton("Restart Game");
        newRoundButton = new JButton("New Round");
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
        buttonPanel.add(newRoundButton);
        buttonPanel.add(restartGameButton);
        /*buttonPanel.add(newGameButton); */

        // Add playerStatusContainer and button panel to the container panel
        containerPanel.add(playerStatusContainer, BorderLayout.NORTH);
        containerPanel.add(buttonPanel, BorderLayout.SOUTH);

        // Add panels to the frame
        BackgroundPanel backgroundPanel = new BackgroundPanel();
        backgroundPanel.setLayout(new BorderLayout());
        backgroundPanel.add(containerPanel, BorderLayout.SOUTH); // Add container panel to the bottom
        backgroundPanel.add(dealerHandValuePanel, BorderLayout.NORTH); // Add dealer hand value panel to the top
        backgroundPanel.add(dealerPanel, BorderLayout.CENTER); // Add dealer panel to the center

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

        newRoundButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Reset the game and start a new round
                newRound();
            }
        });

        restartGameButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Reset the game and start a new round
                restartGame();
            }
        });

        /*        newGameButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Reset the game and start a new round
                startNewGame();
            }
        }) */

        /* Initialize the card deck and player's hand value
        cardDeck = new CardDeck();
        playerHandValue = 0;
        dealerHandValue = 0;
        playerHand = new ArrayList<>();
        

        // Automatically deal a card to the dealer when the UI initializes
        dealCardToDealer();

        // Automatically deal a card to the player when the UI initializes
        dealCardToPlayer();
        */

        // Set the content pane to the background panel
        setContentPane(backgroundPanel);
        
    }

        /**
     * Returns the current round number.
     * 
     * @return the current round number
     */
    public int getRoundNumber() {
        return this.roundNumber;
    }

        /**
     * Returns the dealer Player instance.
     * 
     * @return the dealer player instance
     */
    public static Player getDealer() {
        return dealer;
    }

    /**
     * Returns the player Player instance.
     * 
     * @return the player instance
     */
    public static Player getPlayer() {
        return player;
    }

    private void addRoundNumber() {
        this.roundNumber += 1;
        player.resetHand();
        player.resetStand();
        dealer.resetHand();
        dealer.resetStand();
        currentRound = new RoundClass(getRoundNumber());
    }

    public int setPlayerHandValue() {
        return getPlayer().calculateHandValue();
    }

    public int setDealerHandValue() {
        return getDealer().calculateHandValue();        
    }


    public boolean dealCardToPlayer() {   
        Card card = currentRound.dealRoundCard();  
        if (card != null) {
            player.hit(card);
            playerHandValueLabel.setText("Player Hand Value: " + setPlayerHandValue());
            ImageIcon cardImage = loadCardImage(card);
            JLabel cardLabel = new JLabel(cardImage);
            playerPanel.add(cardLabel);
            playerPanel.revalidate();
            playerPanel.repaint();
            
            if (player.getIsBusted()) {
                statusLabel.setText("Game Status: Busted!");
                hitButton.setEnabled(false);
                standButton.setEnabled(false);
            }
        }
        return true;
    }

    public boolean dealCardToDealer() {
        Card card = currentRound.dealRoundCard();  
        if (card != null) {
            dealer.hit(card);
            dealerHandValueLabel.setText("Dealer Hand Value: " + setDealerHandValue());
            ImageIcon cardImage = loadCardImage(card);
            JLabel cardLabel = new JLabel(cardImage);
            dealerPanel.add(cardLabel);
            dealerPanel.revalidate();
            dealerPanel.repaint();
        }
        return true;
    }

    private ImageIcon loadCardImage(Card card) {
        String cardName = card.getRank().name().toLowerCase() + "_" + card.getSuit().name().toLowerCase() + ".png";
        String imagePath = "/Images/cards/" + cardName;
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
                if (setDealerHandValue() < setPlayerHandValue() && setDealerHandValue() <= 21) {
                    dealCardToDealer();
                } else {
                    ((Timer) e.getSource()).stop();
                    // Check if dealer is busted
                    if (currentRound.findWinner() != null) {
                        statusLabel.setText(currentRound.findWinnerToString());
                    }
                }
            }
        });
        timer.start();
    }

    private void newRound() {
        // Reset the game and start a new round
        playerPanel.removeAll();
        dealerPanel.removeAll();
        playerPanel.revalidate();
        playerPanel.repaint();
        dealerPanel.revalidate();
        dealerPanel.repaint();
        addRoundNumber();
        statusLabel.setText("Game Status: ");
        dealCardToDealer();
        dealCardToPlayer();
        dealCardToPlayer();
        playerHandValueLabel.setText("Player Hand Value: " + setPlayerHandValue());
        dealerHandValueLabel.setText("Dealer Hand Value: " + setDealerHandValue());
        hitButton.setEnabled(true);
        standButton.setEnabled(true);
    }

    private void restartGame() {
        // Reset the game and start a new round
        playerPanel.removeAll();
        dealerPanel.removeAll();
        playerPanel.revalidate();
        playerPanel.repaint();
        dealerPanel.revalidate();
        dealerPanel.repaint();
        RoundClass.resetAllRounds();
        this.roundNumber = 0;
        player = new Player("player");
        dealer = new Player("dealer");
        addRoundNumber();
        statusLabel.setText("Game Status: ");
        dealCardToDealer();
        dealCardToPlayer();
        dealCardToPlayer();
        playerHandValueLabel.setText("Player Hand Value: " + setPlayerHandValue());
        dealerHandValueLabel.setText("Dealer Hand Value: " + setDealerHandValue());
        hitButton.setEnabled(true);
        standButton.setEnabled(true);

    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                BlackjackGameUI gameUI = new BlackjackGameUI();
                gameUI.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

                // Get the screen size and set the frame to full screen
                Toolkit toolkit = Toolkit.getDefaultToolkit();
                int screenWidth = toolkit.getScreenSize().width;
                int screenHeight = toolkit.getScreenSize().height;
                gameUI.setSize(screenWidth, screenHeight);

                gameUI.setVisible(true);
                gameUI.restartGame();
            }
        });
    }
}

class BackgroundPanel extends JPanel {
    private Image backgroundImage;

    public BackgroundPanel() {
        // Load the background image
        backgroundImage = new ImageIcon(getClass().getResource("/Images/cards/background.jpg")).getImage();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Draw the background image
        g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
    }
}
