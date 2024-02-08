package Lybrinth;

import javax.swing.JPanel;
import java.awt.Graphics;

/**
 * Represents the rendering component of the game, responsible for drawing the game elements.
 * This class extends {@link JPanel} and overrides the {@code paintComponent} method to render
 * the game's hero and other elements contained within the dungeon.
 */
public class GameRender extends JPanel {
    private final Hero hero; // The hero of the game
    private final Dungeon dungeon; // The dungeon containing the game elements
    
    /**
     * Constructs a Lybrinth.GameRender panel with a specified hero and dungeon.
     *
     * @param hero The hero to be rendered in the game.
     * @param dungeon The dungeon containing elements to be rendered.
     */
    public GameRender(Hero hero, Dungeon dungeon) {
        this.hero = hero;
        this.dungeon = dungeon;
    }
    
    /**
     * Overrides the {@code paintComponent} method to render the game elements (each thing in the dungeon + hero).
     * This method is called automatically when the panel needs to be redrawn.
     *
     * @param g The {@link Graphics} context used for drawing.
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        for (Things thing : dungeon.getRenderList()) {
            if (thing instanceof AnimatedThings) {
                ((AnimatedThings) thing).updateFrame();
            }
            thing.draw(g);
        }
        
        hero.draw(g);
    }
}
