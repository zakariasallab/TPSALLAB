package Lybrinth;

import javax.swing.*;
import java.awt.*;

/**
 * Represents the congratulations screen displayed when the player successfully finishes all the three levels.
 * This screen congratulates the player and offers an option to exit the game.
 */
public class CongratsScreen extends JFrame {
    
    /**
     * Constructs the congratulations screen with layout and an exit button.
     * Initializes and arranges a congratulations message and an 'Exit' button.
     */
    public CongratsScreen() {
        setTitle("Congratulations!");
        setSize(300, 200);
        setLocationRelativeTo(null); // Center on screen
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        
        JLabel congratsLabel = new JLabel("Congratulations! You've exited the dungeon!", JLabel.CENTER);
        add(congratsLabel, BorderLayout.CENTER);
        
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());
        
        JButton exitButton = new JButton("Exit");
        buttonPanel.add(exitButton);
        
        add(buttonPanel, BorderLayout.SOUTH);
        exitButton.addActionListener(e -> onExitClicked());
    }
    
    /**
     * Handles the action performed when the 'Exit' button is clicked.
     * Exits the application.
     */
    private void onExitClicked() {
        System.exit(0);
    }
}
