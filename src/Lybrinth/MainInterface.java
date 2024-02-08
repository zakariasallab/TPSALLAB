package Lybrinth;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Main interface for the game, providing the primary window for gameplay.
 * This class handles the rendering of the game, processing of user input, and updating game elements.
 */
public class MainInterface extends JFrame implements KeyListener {
    TileManager tileManager = new TileManager(32,32,"assets/images/tileSet.png");
    Dungeon dungeon;
    Hero hero = Hero.getInstance(0,0,48,52);
    GameRender gameRender;
    private final JProgressBar healthBar;
    private final Timer gameTimer;
    private int timeRemaining;
    private JLabel timerLabel;
    
    /**
     * Constructs the main interface, setting up the game environment and user interface components.
     * Initializes the game elements such as the dungeon, hero, and UI components like health bar and timer.
     * @throws HeadlessException if Graphics Environment is not present.
     */
    public MainInterface() throws HeadlessException {
        super();
        hero.x = 0;
        hero.y = 0;
        hero.getHitBox().setX(0);
        hero.getHitBox().setY(0);
        hero.setHasSucceeded(false);
        this.dungeon = new Dungeon("assets/levels/level" + hero.getLevel() + ".txt", tileManager);
        gameRender = new GameRender(hero, dungeon);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        healthBar = new CustomProgressBar(0, 100, Color.GRAY, Color.RED);
        healthBar.setValue(hero.getHealth());
        healthBar.setStringPainted(true);
        
        timerLabel = new JLabel("Time: 00:00", JLabel.CENTER);
        this.add(timerLabel, BorderLayout.SOUTH);
        timeRemaining = 60;
        gameTimer = new Timer(1000, e -> updateTime());
        timerLabel = new JLabel("Time: 00:00", JLabel.CENTER);
        timerLabel.setFont(new Font("Verdana", Font.BOLD, 20));
        timerLabel.setForeground(Color.GREEN);
        timerLabel.setOpaque(true);
        timerLabel.setBackground(Color.BLACK);
        timerLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        timerLabel.setPreferredSize(new Dimension(200, 50));
        gameTimer.start();
        
        this.setLayout(new BorderLayout());
        this.add(healthBar, BorderLayout.NORTH);
        this.add(gameRender, BorderLayout.CENTER);
        this.add(timerLabel, BorderLayout.SOUTH);
        this.getContentPane().add(gameRender);
        addKeyListener(this);
        this.pack();
        this.setVisible(true);
        this.setSize(new Dimension(dungeon.getWidth() * tileManager.getWidth(), dungeon.getHeight() * tileManager.getHeight() + 90));
        
        Timer timer = getTimer();
        timer.start();
    }
    
    /**
     * Creates and returns a timer that updates the game state and rendering.
     * @return A new Timer for game state updates and rendering.
     */
    private Timer getTimer() {
        ActionListener animationTimer = e -> {
            gameRender.repaint();
            final int speed = 10;
            if (hero.isWalking()) {
                switch (hero.getOrientation()) {
                    case LEFT:  hero.moveIfPossible(- speed, 0, dungeon); break;
                    case RIGHT: hero.moveIfPossible(speed, 0, dungeon); break;
                    case UP:    hero.moveIfPossible(0, - speed, dungeon); break;
                    case DOWN:  hero.moveIfPossible(0, speed, dungeon); break;
                }
            }
            if (hero.hasReachedExit(dungeon)) {
                onSuccessfulExit();
            }
            updateHealthBar();
        };
        return new Timer(50, animationTimer);
    }
    
    /**
     * Method from KeyListener, not used in this implementation but required for interface compliance.
     * @param e The KeyEvent associated with the key typed.
     */
    public void keyTyped(KeyEvent e) {
        // Pas nécessaire ici, mais doit être présente pour implémenter KeyListener.
    }
    
    /**
     * Handles key press events for controlling the hero's movement and orientation.
     * @param e The KeyEvent associated with the key pressed.
     */
    @Override
    public void keyPressed(KeyEvent e) {
        hero.setWalking(true);
        switch (e.getKeyCode()) {
            case KeyEvent.VK_LEFT:  hero.setOrientation(Orientation.LEFT); break;
            case KeyEvent.VK_RIGHT: hero.setOrientation(Orientation.RIGHT); break;
            case KeyEvent.VK_UP:    hero.setOrientation(Orientation.UP); break;
            case KeyEvent.VK_DOWN:  hero.setOrientation(Orientation.DOWN); break;
        }
    }
    
    /**
     * Handles key release events to stop the hero's movement.
     * @param e The KeyEvent associated with the key released.
     */
    @Override
    public void keyReleased(KeyEvent e) {
        hero.setWalking(false);
    }
    
    /**
     * Updates the health bar to reflect the current health status of the hero.
     */
    public void updateHealthBar() {
        healthBar.setValue(hero.getHealth());
        healthBar.setString("Health: " + hero.getHealth() + "/" + 100);
    }
    
    /**
     * Updates the timer and handles the scenario when time is up.
     */
    private void updateTime() {
        if (timeRemaining > 0) {
            timeRemaining--;
            int minutes = timeRemaining / 60;
            int seconds = timeRemaining % 60;
            timerLabel.setText(String.format("Time: %02d:%02d", minutes, seconds));
        } else {
            gameTimer.stop();
            handleTimeUp();
        }
    }
    
    /**
     * Handles the situation when the game timer runs out.
     * Triggers the Lybrinth.GameOverScreen.
     */
    private void handleTimeUp() {
        new GameOverScreen().setVisible(true);
        dispose();
    }
    
    /**
     * Handles the successful exit of the hero from the dungeon.
     * Triggers the appropriate action based on the hero's level and success state.
     */
    private void onSuccessfulExit() {
        if (hero.getLevel() == hero.MAX_LEVEL - 1 && !hero.getHasSucceeded()) {
            hero.setHasSucceeded(true);
            new CongratsScreen().setVisible(true);
            gameTimer.stop();
            dispose();
        } else if (hero.x != 0 && hero.y != 0 && !hero.getHasSucceeded()) {
            hero.nextLevel();
            gameTimer.stop();
            dispose();
            new MainInterface().setVisible(true);
        }
    }
}
