package Lybrinth;

import javax.swing.*;
import java.awt.*;

/**
 * Represents the title screen of the game.
 * This screen is the initial interface presented to the player, featuring a 'Play' button to start the game.
 */
public class TitleScreen extends JFrame {
    
    /**
     * Constructs the title screen with layout and a play button.
     * Initializes and arranges the 'Play' button in the center of the window.
     */
    public TitleScreen() {
        setTitle("Game Title Screen");
        setSize(400, 200);
        setLocationRelativeTo(null); // Center on screen
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        
        // Button to start the game
        JButton playButton = new JButton("Play");
        add(playButton, BorderLayout.CENTER);
        
        playButton.addActionListener(e -> onPlayClicked());
    }
    
    /**
     * Handles the action performed when the 'Play' button is clicked.
     * It starts the main game interface and disposes of the title screen.
     */
    private void onPlayClicked() {
        new MainInterface().setVisible(true);
        dispose(); // Close the title screen
    }
    
    /**
     * Main method to execute the title screen.
     * Ensures the title screen is created and displayed on the Event Dispatch Thread.
     *
     * @param args Command line arguments (not used in this application).
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new TitleScreen().setVisible(true));
    }
}
