package Lybrinth;

import javax.swing.*;
import java.awt.*;

/**
 * Represents the game over screen displayed when the player loses the game (Time's up or health down to 0).
 * This screen provides options to either restart the game or exit.
 */
public class GameOverScreen extends JFrame {
    
    /**
     * Constructs a Lybrinth.GameOverScreen with layout and buttons.
     * Initializes and arranges UI components including 'Restart' and 'Exit' buttons.
     */
    public GameOverScreen() {
        setTitle("Game Over");
        setSize(300, 200);
        setLocationRelativeTo(null); // Center on screen
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        
        JLabel gameOverLabel = new JLabel("Game Over !", JLabel.CENTER);
        add(gameOverLabel, BorderLayout.CENTER);
        
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());
        
        // Button to restart the game
        JButton restartButton = new JButton("Restart");
        buttonPanel.add(restartButton);
        
        // Button to exit the game
        JButton exitButton = new JButton("Exit");
        buttonPanel.add(exitButton);
        
        add(buttonPanel, BorderLayout.SOUTH);
        
        restartButton.addActionListener(e -> onRestartClicked());
        
        exitButton.addActionListener(e -> onExitClicked());
    }
    
    /**
     * Handles the action performed when the 'Restart' button is clicked.
     * It restarts the game by initializing a new Lybrinth.MainInterface and disposing the current screen.
     */
    private void onRestartClicked() {
        new MainInterface().setVisible(true);
        dispose();
    }
    
    /**
     * Handles the action performed when the 'Exit' button is clicked.
     * Exits the application.
     */
    private void onExitClicked() {
        System.exit(0);
    }
}
